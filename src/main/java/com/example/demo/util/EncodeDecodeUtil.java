package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Configuration
@Slf4j
public class EncodeDecodeUtil {
    public static final String HMAC_SHA_256 = "HmacSHA256";
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*public static void main(String[] args) throws Exception {
        log.info("URLEncoded: "+getUTF("VHdwJJk0G2yqE0aBMEG0MPHi+RRHemqxvjHhkDC3gZk="));
        log.info("encodeHmacSha256AndBase64: "+encodeHmacSha256AndBase64("MyPassword123456"));
        log.info("HmacSha256Base64: "+ encodeHmacSha256("MyPassword123456"));
        log.info("---encodeBase64: "+encodeBase64("654321A$"));
        log.info("---decode-encodeBase64: "+decodeBase64(encodeBase64("654321A$")));
        EncodeDecodeUtil encodeDecode = new EncodeDecodeUtil();
        log.info(encodeDecode.encodePassword("654321A$"));
        log.info(encodeDecode.encodePassword("NjU0MzIxQSQ="));
    }*/

    private static String getUTF(String chain) throws UnsupportedEncodingException {
        return URLEncoder.encode(chain, "UTF-8");
    }

    private static String encodeHmacSha256AndBase64(String data) throws Exception {
        //log.info("-encodeHmacSha256.message {} "+data);
        Objects.requireNonNull(data);
        String secret = "MyPassword123456";
        secret = "admin";

        try{
            Mac sha256 = Mac.getInstance(HMAC_SHA_256);
            SecretKeySpec s_key_v = new SecretKeySpec(secret.getBytes(), HMAC_SHA_256);
            sha256.init(s_key_v);
            return  Base64.encodeBase64String(sha256.doFinal(data.getBytes()));
        }catch (NoSuchAlgorithmException | InvalidKeyException ex){
            log.warn(data +" did not encoded to hmac256, reason: "+ex.getMessage());
            throw new Exception(ex);
        }
    }

    private static byte[] encodeHmacSha256(String data) throws Exception {
        //log.info("-encodeHmacSha256.message {} "+data);
        Objects.requireNonNull(data);
        String secret = "MyPassword123456";
        secret = "admin";
        try{
            Mac sha256 = Mac.getInstance(HMAC_SHA_256);
            SecretKeySpec s_key_v = new SecretKeySpec(secret.getBytes(), HMAC_SHA_256);
            sha256.init(s_key_v);
            return  sha256.doFinal(data.getBytes());
        }catch (NoSuchAlgorithmException | InvalidKeyException ex){
            log.warn(data +" did not encoded to hmac256, reason: "+ex.getMessage());
            throw new Exception(ex);
        }
    }

    public static String decodeBase64(String base64String) {
        Objects.requireNonNull(base64String);
        return new String(Base64.decodeBase64(base64String));
    }

    public static String encodeBase64(String stringToBase64) {
        Objects.requireNonNull(stringToBase64);
        return Base64.encodeBase64String(stringToBase64.getBytes());
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder().encode(rawPassword);
        // return this.passwordEncoder.encode(rawPassword);
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

