package com.rapid7.cbomtest.demo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;

public class ExplicitArgoAESTest {
    public static void main(String[] args) {
        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();

            byte[] iv = new byte[12]; 
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv); 

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            // Encrypt the plaintext
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] plaintext = "Hello, World!".getBytes();
            byte[] ciphertext = cipher.doFinal(plaintext);

            // Decrypt the ciphertext
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] decryptedText = cipher.doFinal(ciphertext);
            String decryptedString = new String(decryptedText);
            System.out.println("Decrypted text: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}