package com.alcity.utility;

import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static void main(String[] args) throws Exception {
        String admin="admin";
        String moslem="moslem";
        String password_123456="123456";
        byte [] hash_Admin = null;
        byte [] hash_moslem = null;
        byte [] hash_123456 = null;
        try {
            hash_Admin = GenerateSHA256.getSHA(admin);
            hash_moslem = GenerateSHA256.getSHA(moslem);
            hash_123456 = GenerateSHA256.getSHA(password_123456);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String hashedMoslem = GenerateSHA256.toHexString(hash_moslem) ;
        String hashedPassword = GenerateSHA256.toHexString(hash_Admin) ;
        String hashed123456 = GenerateSHA256.toHexString(hash_123456) ;
        System.out.println("admin=" + hashedPassword);
        System.out.println("moslem 1 =" + hashedMoslem);
        System.out.println("hashed 123456 =" + hashed123456);
        System.out.println("avina = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("1395")));
        System.out.println("123456 = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("123456")));
        System.out.println("moslem = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("moslem")));
        System.out.println("hesam = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("hesam")));
        System.out.println("admin2 = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("admin2")));
        System.out.println("mahdi = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("mahdi")));
        System.out.println("alireza = "+GenerateSHA256.toHexString(GenerateSHA256.getSHA("alireza")));


    }
}
