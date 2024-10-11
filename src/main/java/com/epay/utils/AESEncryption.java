package com.epay.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryption {

    private static final String AES = "AES";
    private static final String AES_GCM_NO_PADDING = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 128; // 128 bits
    private static final int IV_SIZE = 12;
    private static final int TAG_SIZE = 128;

    private SecretKey key;
    private SecureRandom secureRandom;

    // Constructor to generate secret key and secure random instance
    public AESEncryption() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
        keyGen.init(KEY_SIZE);
        this.key = keyGen.generateKey();
        this.secureRandom = new SecureRandom();
    }

    // Encrypt a string (or an integer converted to string)
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);

        byte[] iv = new byte[IV_SIZE];
        secureRandom.nextBytes(iv); // Generate a random IV

        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmSpec);

        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Combine IV and encrypted data for easy storage or transmission
        byte[] encryptedDataWithIv = new byte[IV_SIZE + encryptedData.length];
        System.arraycopy(iv, 0, encryptedDataWithIv, 0, IV_SIZE);
        System.arraycopy(encryptedData, 0, encryptedDataWithIv, IV_SIZE, encryptedData.length);

        // Encode to Base64 to make it suitable for storage
        return Base64.getEncoder().encodeToString(encryptedDataWithIv);
    }

    // Decrypt the encrypted string
    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_GCM_NO_PADDING);

        byte[] decodedData = Base64.getDecoder().decode(encryptedData);

        // Extract IV and encrypted data
        byte[] iv = new byte[IV_SIZE];
        byte[] encryptedBytes = new byte[decodedData.length - IV_SIZE];
        System.arraycopy(decodedData, 0, iv, 0, IV_SIZE);
        System.arraycopy(decodedData, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmSpec);

        byte[] decryptedData = cipher.doFinal(encryptedBytes);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    // Encrypt an integer by first converting it to string
    public String encrypt(int number) throws Exception {
        return encrypt(String.valueOf(number));
    }

    // Decrypt the string and convert it back to an integer
    public int decryptToInt(String encryptedData) throws Exception {
        String decryptedData = decrypt(encryptedData);
        return Integer.parseInt(decryptedData);
    }

    // Get the secret key for storage if needed (not recommended to expose directly)
    public SecretKey getKey() {
        return this.key;
    }
}

