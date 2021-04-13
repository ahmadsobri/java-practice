import java.io.IOException;
import java.io.Serializable;

public class DefaultSerial implements Serializable {
    private String name;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //serialize before add property 'name'
//        DefaultSerial serialize = new DefaultSerial();
//        System.out.println(SerializationUtility.serializeObjectToString(serialize));

        //deserialize after add property 'name' will be error "..local class incompatible: stream classdesc serialVersionUID=..."
        String digest = "rO0ABXNyAA1EZWZhdWx0U2VyaWFst5ieUky+tBYCAAB4cA==";
        DefaultSerial instance = (DefaultSerial) DeserializationUtility.deSerializeObjectFromString(digest);
    }
}
