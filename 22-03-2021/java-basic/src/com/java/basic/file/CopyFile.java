package com.java.basic.file;

import java.io.*;

public class CopyFile {

    static void copyFileWithFileReader() throws IOException {
        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader("src/com/java/basic/file/input.txt");
            out = new FileWriter("src/com/java/basic/file/output.txt");

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    static void copyFileWithStream() throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("src/com/java/basic/file/input.txt");
            out = new FileOutputStream("src/com/java/basic/file/output.txt");

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    static void readConsole() throws IOException {
        InputStreamReader cin = null;

        try {
            cin = new InputStreamReader(System.in);
            System.out.println("Enter characters, 'q' to quit.");
            char c;
            do {
                c = (char) cin.read();
                System.out.print(c);
            } while(c != 'q');
        }finally {
            if (cin != null) {
                cin.close();
            }
        }
    }

    static void fileStream (){
        try {
            byte bWrite [] = {11,21,3,40,5};
            OutputStream os = new FileOutputStream("src/com/java/basic/file/test.txt");
            for(int x = 0; x < bWrite.length ; x++) {
                os.write( bWrite[x] );   // writes the bytes
            }
            os.close();

            InputStream is = new FileInputStream("src/com/java/basic/file/test.txt");
            int size = is.available();

            for(int i = 0; i < size; i++) {
                System.out.print((char)is.read() + "  ");
            }
            is.close();
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }

    static void readDir(){
        File file = null;
        String[] paths;

        try {
            // create new file object
            file = new File("src/com/java/basic/file");

            // array of files and directory
            paths = file.list();

            // for each name in the path array
            for(String path:paths) {
                // prints filename and directory name
                System.out.println(path);
            }
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //copyFileWithFileReader();
        //copyFileWithStream();
        //readConsole();
        //fileStream();
        readDir();
    }
}
