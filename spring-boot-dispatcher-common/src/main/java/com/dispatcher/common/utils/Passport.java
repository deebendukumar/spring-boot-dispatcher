/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dispatcher.common.utils;

import org.apache.commons.codec.binary.Hex;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author deebendukumar
 */
public class Passport {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Passport.class);

    private static final String BASE58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";

    private Passport() {
    }

    public static String generate(String input) {
        return generateBitcoinAddress(input);
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


    public static void main(String[] args) {
        System.out.println(Passport.generate("919872177944"));
    }

    private String generateUniqueId() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String digest;
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        digest = Hex.encodeHexString(salt.digest());
        return digest;
    }
}
