package com.alcity.utility;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PBEWithMD5AndDESExample {

    public static void main(String[] args) throws Exception {
        String plaintext = "postgres";
        String password = "mysecretpassword";

        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);

        // Number of iterations for key derivation
        int iterationCount = 1000; // A higher number provides more security

        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext, password, salt, iterationCount);
        System.out.println("Encrypted: " + encryptedText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText, password, salt, iterationCount);
        System.out.println("Decrypted: " + decryptedText);
    }

    public static String encrypt(String plaintext, String password, byte[] salt, int iterationCount) throws Exception {
        // Create PBEKeySpec
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

        // Create SecretKeyFactory for PBEWithMD5AndDES
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

        // Generate the secret key
        SecretKey secretKey = keyFactory.generateSecret(pbeKeySpec);

        // Create PBEParameterSpec with salt and iteration count
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, iterationCount);

        // Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParamSpec);

        // Encrypt the data
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

        // Encode the encrypted bytes to Base64 for easier handling
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, String password, byte[] salt, int iterationCount) throws Exception {
        // Decode the Base64 encoded ciphertext
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

        // Create PBEKeySpec
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

        // Create SecretKeyFactory for PBEWithMD5AndDES
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");

        // Generate the secret key
        SecretKey secretKey = keyFactory.generateSecret(pbeKeySpec);

        // Create PBEParameterSpec with salt and iteration count
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, iterationCount);

        // Initialize the cipher for decryption
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParamSpec);

        // Decrypt the data
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Convert decrypted bytes back to a string
        return new String(decryptedBytes);
    }
}