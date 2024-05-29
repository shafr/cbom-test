package com.rapid7.cbomtest.demo;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ExplicitArgoHmacSHA256 {
    public static void main(String[] args) {
        try {
            // Sample data to be authenticated
            String data = "Hello, World!";

            // Secret key for HMAC-SHA256 (should be securely generated and stored)
            byte[] key = "my-secret-key".getBytes(StandardCharsets.UTF_8);

            // Create a SecretKeySpec instance with the secret key
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");

            // Get an instance of the HmacSHA256 algorithm
            Mac mac = Mac.getInstance("HmacSHA256");

            // Initialize the MAC with the secret key
            mac.init(secretKeySpec);

            // Compute the HMAC on the data
            byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Convert the HMAC to a Base64-encoded string
            String hmac = Base64.getEncoder().encodeToString(hmacBytes);

            System.out.println("Data: " + data);
            System.out.println("HMAC-SHA256: " + hmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}