package com.alcity.utility;

import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static void main(String[] args) throws Exception {
        String admin="admin";
        String avina="1395";
        byte [] hash_Admin = null;
        try {
            hash_Admin = GenerateSHA256.getSHA(admin);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String hashedPassword = GenerateSHA256.toHexString(hash_Admin) ;
        System.out.println("admin=" + hashedPassword);
        System.out.println("avina = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("1395")));
        System.out.println("123456 = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("123456")));
        System.out.println("moslem = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("moslem")));
        System.out.println("hesam = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("hesam")));
        System.out.println("admin2 = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("admin2")));


    }
}
