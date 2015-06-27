package com.company;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class Main
{

    public static void main(String[] args)  throws Exception{


        byte [  ]           input = new byte[] { (byte)0xab, (byte)0xcd };
        Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding", "BC");
        SecureRandom random = new SecureRandom();



        // create the keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(256, random);
        KeyPair pair = generator.generateKeyPair();
        Key pubKey = pair.getPublic();
        Key privKey = pair.getPrivate();
        System.out.println("input: " + CryptoUtils.toHex(input));
        cipher.init(Cipher.ENCRYPT_MODE, pubKey, random);



        // encryption step
        byte[] cipherText = cipher.doFinal(input);
        System.out.println("ciphertext: " + CryptoUtils.toHex(cipherText));



        // decryption step
        cipher.init(Cipher.DECRYPT_MODE, privKey, random);
        byte[] plainText = cipher.doFinal(cipherText);
        System.out.println("plaintext: " + CryptoUtils.toHex(plainText));

    }
}
