package com.myproject.security;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSA {

  private PrivateKey privateKey;
  private PublicKey publicKey;

  public static void main(String[] args) {
    RSA rsa = new RSA();
    rsa.init();
    rsa.printKeys();
    String message = "Hello World";
    try {
      String encryptedMessage = rsa.encrypt(message);
      String decryptedMessage = rsa.decrypt(encryptedMessage);

      System.out.println("EncryptedMessage : \n" + encryptedMessage + "\n");
      System.out.println("DecryptedMessage : \n" + decryptedMessage);
    } catch (Exception e) {
    }
  }

  public void init() {
    try {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(1024);
      KeyPair pair = generator.generateKeyPair();
      privateKey = pair.getPrivate();
      publicKey = pair.getPublic();
    } catch (Exception ignored) {
    }
  }

  public void printKeys() {
    String publicKey = encode(this.publicKey.getEncoded());
    System.err.println("PublicKey : \n" + publicKey);
    System.out.println("PublicKey format :" +this.publicKey.getFormat());

    String privateKey = encode(this.privateKey.getEncoded());
    System.err.println("PrivateKey : \n" + privateKey);
    System.out.println("PrivateKey format :" + this.privateKey.getFormat());
  }

  public String encrypt(String message) throws Exception {
    byte[] messageBytes = message.getBytes();
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedBytes = cipher.doFinal(messageBytes);
    return encode(encryptedBytes);
  }

  private String encode(byte[] data) {
    return Base64.getEncoder()
                 .encodeToString(data);
  }

  public String decrypt(String encryptedMessage) throws Exception {
    byte[] encryptedBytes = decode(encryptedMessage);
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decryptMessage = cipher.doFinal(encryptedBytes);
    return new String(decryptMessage, StandardCharsets.UTF_8);
  }

  private byte[] decode(String data) {
    return Base64.getDecoder()
                 .decode(data);
  }
}
