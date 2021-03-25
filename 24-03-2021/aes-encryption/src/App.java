import javax.crypto.SecretKey;

import static java.nio.charset.StandardCharsets.UTF_8;

public class App {
    private static final int IV_LENGTH_BYTE = 12;
    private static final int AES_KEY_BIT = 256;

    public static void main(String[] args) throws Exception {
        String OUTPUT_FORMAT = "%-30s:%s";

        String pText = "Hello World AES-GCM, Welcome to Cryptography!";

        // encrypt and decrypt need the same key.
        // get AES 256 bits (32 bytes) key
        SecretKey secretKey = AesCryptoUtil.getAESKey(AES_KEY_BIT);
        //SecretKey publicKey = AesCryptoUtil.getAESKey(AES_KEY_BIT);

        // encrypt and decrypt need the same IV.
        // AES-GCM needs IV 96-bit (12 bytes)
        byte[] iv = AesCryptoUtil.getRandomNonce(IV_LENGTH_BYTE);

        byte[] encryptedText = AesEncryptorGcm.encryptWithPrefixIV(pText.getBytes(UTF_8), secretKey, iv);

        System.out.println("\n------ AES GCM Encryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", AesCryptoUtil.hex(secretKey.getEncoded())));
        System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", AesCryptoUtil.hex(iv)));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", AesCryptoUtil.hex(encryptedText)));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", AesCryptoUtil.hexWithBlockSize(encryptedText, 16)));

        System.out.println("\n------ AES GCM Decryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex)", AesCryptoUtil.hex(encryptedText)));
        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", AesCryptoUtil.hexWithBlockSize(encryptedText, 16)));
        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", AesCryptoUtil.hex(secretKey.getEncoded())));

        String decryptedText = AesEncryptorGcm.decryptWithPrefixIV(encryptedText, secretKey);

        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));
    }
}
