import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class DeserializationUtility {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String serializedObj = "rO0ABXNyAAxBcHBsZVByb2R1Y3RjitCNntK2lgIAA0wADWhlYWRwaG9uZVBvcnR0ABJMamF2YS9sYW5nL1N0cmluZztMAA1saWdodG5pbmdQb3J0cQB+AAFMAA90aHVuZGVyYm9sdFBvcnRxAH4AAXhwdAARaGVhZHBob25lUG9ydDIwMjBwdAATdGh1bmRlcmJvbHRQb3J0MjAyMA==";

        System.out.println("Deserializing AppleProduct...");

        AppleProduct deserializedObj = (AppleProduct) deSerializeObjectFromString(serializedObj);

        System.out.println("Headphone port of AppleProduct:"+ deserializedObj.getHeadphonePort());
        System.out.println("Thunderbolt port of AppleProduct:"+ deserializedObj.getThunderboltPort());
    }

    public static Object deSerializeObjectFromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
