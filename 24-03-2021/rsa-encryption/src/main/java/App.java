import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;

public class App {


    static String pubKey = "KeyPair/publicKey";
    static String priKey = "KeyPair/privateKey";


    public static void main(String[] args) {

        doGenerateKey();
        try {
            doEncrypt("RSA Cryptography is fun!");
            //doEncryptWithFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doGenerateKey(){
        KeyGenerator gk;
        try {
            File filePublicKey = new File(pubKey);
            File fileprivateKey = new File(priKey);
            gk = new KeyGenerator(1024);
            gk.createKeys();

            if(!filePublicKey.exists()) {
                gk.writeToFile(pubKey, gk.getPublicKey().getEncoded());
            }
            if(!fileprivateKey.exists()) {
                gk.writeToFile(priKey, gk.getPrivateKey().getEncoded());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public static void doEncrypt(String msg) throws Exception {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        PrivateKey privateKey = ac.getPrivate(priKey);
        PublicKey publicKey = ac.getPublic(pubKey);

        String encrypted_text = ac.encryptText(msg, publicKey);
        String decrypted_text = ac.decryptText(encrypted_text, privateKey);
        System.out.println("Original Message: " + msg +
                "\nEncrypted Message: " + encrypted_text
                + "\nDecrypted Message: " + decrypted_text);
    }

    public static void doEncryptWithFile() throws Exception {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        PrivateKey privateKey = ac.getPrivate(priKey);
        PublicKey publicKey = ac.getPublic(pubKey);

        if (new File("KeyPair/text.txt").exists()) {
            ac.encryptFile(ac.getFileInBytes(new File("KeyPair/text.txt")), new File("KeyPair/text_encrypted.txt"),publicKey);

            ac.decryptFile(ac.getFileInBytes(new File("KeyPair/text_encrypted.txt")), new File("KeyPair/text_decrypted.txt"), privateKey);
        } else {
            System.out.println("Create a file text.txt under folder KeyPair");
        }
    }
}
