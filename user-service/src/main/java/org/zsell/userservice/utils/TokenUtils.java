package org.zsell.userservice.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenUtils {
    @SneakyThrows
    public static String encode(String password) {
        if (Objects.isNull(password)) {
            return null;
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_16LE);
        return Base64.getEncoder().encodeToString(md5.digest(passwordBytes));
    }
}