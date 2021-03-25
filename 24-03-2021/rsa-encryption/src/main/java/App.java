import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class App {
    public static void main(String[] args) {
        //doGenerateKey();

        try {
            doEncrypt("RSA Cryptography is fun!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doGenerateKey(){
        KeyGenerator gk;
        try {
            gk = new KeyGenerator(1024);
            gk.createKeys();
            gk.writeToFile("KeyPair/publicKey", gk.getPublicKey().getEncoded());
            gk.writeToFile("KeyPair/privateKey", gk.getPrivateKey().getEncoded());
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void doEncrypt(String msg ) throws Exception {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
        PublicKey publicKey = ac.getPublic("KeyPair/publicKey");

        String encrypted_text = ac.encryptText(msg, privateKey);
        String decrypted_text = ac.decryptText(encrypted_text, publicKey);
        System.out.println("Original Message: " + msg +
                "\nEncrypted Message: " + encrypted_text
                + "\nDecrypted Message: " + decrypted_text);

        if (new File("KeyPair/text.txt").exists()) {
            ac.encryptFile(ac.getFileInBytes(new File("KeyPair/text.txt")),
                    new File("KeyPair/text_encrypted.txt"),privateKey);
            ac.decryptFile(ac.getFileInBytes(new File("KeyPair/text_encrypted.txt")),
                    new File("KeyPair/text_decrypted.txt"), publicKey);
        } else {
            System.out.println("Create a file text.txt under folder KeyPair");
        }
    }
}
