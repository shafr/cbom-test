package com.rapid7.cbomtest.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECDSAValidator {
    public static void main(String[] args) {
        try {
            // Read the ECDSA public key from the resources folder
            byte[] publicKeyBytes = Files.readAllBytes(Paths.get("resources", "ecdsa_256.pub"));

            // Decode the Base64-encoded public key
            byte[] decodedPublicKey = Base64.getDecoder().decode(publicKeyBytes);

            // Create a KeyFactory instance for the ECDSA algorithm
            KeyFactory keyFactory = KeyFactory.getInstance("EC");

            // Convert the decoded public key bytes into a PublicKey instance
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedPublicKey);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // Validate the public key
            validatePublicKey(publicKey);

            System.out.println("ECDSA public key is valid.");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            System.err.println("Error validating ECDSA public key: " + e.getMessage());
        }
    }

    private static void validatePublicKey(PublicKey publicKey) throws InvalidKeyException {
        // You can perform additional validation checks on the public key here
        // For example, you can verify the key algorithm, key size, or other properties

        // For this example, we'll just check if the key is an ECDSA key
        if (!"EC".equals(publicKey.getAlgorithm())) {
            throw new InvalidKeyException("Invalid key algorithm. Expected EC.");
        }
    }
}