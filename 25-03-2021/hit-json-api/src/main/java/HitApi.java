import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class HitApi {
    private static final String urlPost = "";
    private static final String urlGet = "https://api.covid19api.com/summary";

    //region Get With HttpURLConnection
    public static void getApiWithHttpURLConnection() {
        try {

            URL url = new URL(urlGet);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();
            System.out.println("response code = " + responsecode);

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                //Get the required object from the above created object
                JSONObject obj = (JSONObject) data_obj.get("Global");

                //Get the required data using its key
                System.out.println(obj.get("TotalRecovered"));

                JSONArray arr = (JSONArray) data_obj.get("Countries");

                for (int i = 0; i < arr.size(); i++) {

                    JSONObject new_obj = (JSONObject) arr.get(i);

                    if (new_obj.get("Slug").equals("albania")) {
                        System.out.println(new_obj.get("Country"));
                        System.out.println("Total Recovered: " + new_obj.get("TotalRecovered"));
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion


    //region Post Using HttpURLConnection
    public static void postApiWithHttpURLConnection() {
        try {
            URL url = new URL(urlPost);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);
            conn.connect();

            String jsonInputString = "{\"full_name\":\"FlanahBinti Fulanah\",\"age\":32}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region Post Using HttpClient
    public static void postApiWithHttpClient() throws IOException {
        JSONObject json = new JSONObject();
        json.put("full_name", "Fulan Bin Fulan");
        json.put("age", 23);

        //HttpClient httpClient = new DefaultHttpClient();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            String responseContent = "";
            HttpPost request = new HttpPost(urlPost);
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse httpResponse = httpClient.execute(request);

            StatusLine statusLine = httpResponse.getStatusLine();
            //InputStream body = httpResponse.getEntity().getContent();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                responseContent = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8.name());
            }
            // handle response here...
            System.out.println("response = " + responseContent);
        } catch (Exception ex) {
            // handle exception here
            ex.printStackTrace();
        } finally {
            httpClient.close();
        }
    }
    //endregion
}
