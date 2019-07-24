package com.web.api.rest.token;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;
import org.bouncycastle.util.encoders.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BasicTokenProvider {

    private static final String AUTH_HEADER_KEY = "";



    private static Key generateKeySpec(String key) throws UnsupportedEncodingException {
        if (key.length() != 32)
            throw new UnsupportedEncodingException("Key should be exactly 32 characters");
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] keyBytes = key.getBytes(UTF_8);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        return keySpec;
    }

    public static String encryptAES256(String toEncrypt, String key) throws Exception {
        try {
            Key skeySpec = generateKeySpec(key);
            final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(toEncrypt.getBytes(UTF_8));
            byte[] encryptedValue = Base64.encode(encrypted);
            return new String(encryptedValue, UTF_8);
        } catch (Exception e) {
            throw new Exception("Error while decrypting text", e);
        }
    }

    public  String getBasicToken(String loginName, String password) throws Exception{
        StringBuilder authHeader = new StringBuilder("Basic ");
        String encryptedPassword = encryptAES256(password, AUTH_HEADER_KEY);
        byte[] toEncode = (loginName + ":" + encryptedPassword).getBytes(UTF_8);
        return authHeader.append(java.util.Base64.getEncoder().encodeToString(toEncode)).toString();
    }

    public long getCurrentTime(){
        return System.currentTimeMillis();
    }



}
