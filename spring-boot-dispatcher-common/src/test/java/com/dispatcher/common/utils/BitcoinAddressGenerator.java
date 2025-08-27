package com.dispatcher.common.utils;

import java.security.MessageDigest;
import java.util.Arrays;

public class BitcoinAddressGenerator {

    // Base58 characters
    private static final String BASE58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    public static void main(String[] args) {
        String input = "9501111048";
        String address = generateBitcoinAddress(input);
        System.out.println("Bitcoin-like Address: " + address);
    }

    public static String generateBitcoinAddress(String input) {
        try {
            // Step 1: SHA-256 hash
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] sha256Hash = sha256.digest(input.getBytes());

            // Step 2: RIPEMD-160 hash
            byte[] ripemd160Hash = ripemd160(sha256Hash);

            // Step 3: Add version byte (0x00 for mainnet)
            byte[] versionedPayload = new byte[ripemd160Hash.length + 1];
            versionedPayload[0] = 0x00;
            System.arraycopy(ripemd160Hash, 0, versionedPayload, 1, ripemd160Hash.length);

            // Step 4: Checksum (first 4 bytes of double SHA-256)
            byte[] checksum = Arrays.copyOf(sha256.digest(sha256.digest(versionedPayload)), 4);

            // Step 5: Concatenate payload + checksum
            byte[] fullPayload = new byte[versionedPayload.length + 4];
            System.arraycopy(versionedPayload, 0, fullPayload, 0, versionedPayload.length);
            System.arraycopy(checksum, 0, fullPayload, versionedPayload.length, 4);

            // Step 6: Base58 encode
            return base58Encode(fullPayload);

        } catch (Exception e) {
            throw new RuntimeException("Error generating address", e);
        }
    }

    // Simulated RIPEMD-160 using SHA-1 (not secure, for demo only)
    private static byte[] ripemd160(byte[] input) throws Exception {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        return Arrays.copyOf(sha1.digest(input), 20); // RIPEMD-160 is 20 bytes
    }

    // Base58 encoding
    private static String base58Encode(byte[] input) {
        java.math.BigInteger num = new java.math.BigInteger(1, input);
        StringBuilder sb = new StringBuilder();
        while (num.compareTo(java.math.BigInteger.ZERO) > 0) {
            int remainder = num.mod(java.math.BigInteger.valueOf(58)).intValue();
            sb.insert(0, BASE58.charAt(remainder));
            num = num.divide(java.math.BigInteger.valueOf(58));
        }
        // Add '1' for each leading 0 byte
        for (byte b : input) {
            if (b == 0x00) sb.insert(0, '1');
            else break;
        }
        return sb.toString();
    }
}
