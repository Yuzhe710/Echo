package com.example.Echo.util;

import com.mysql.cj.util.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CommunityUtil {

    // generate random string
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", ""); //会有横线
    }

    // MD5加密
    public static String md5(String key) {
        if (StringUtils.isEmptyOrWhitespaceOnly(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes(StandardCharsets.UTF_8)); //生成16进制加密密码
    }
}
