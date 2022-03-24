package com.example.Echo;

import com.example.Echo.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = EchoApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired // thymeleaf模板引擎
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("1139524373@qq.com", "TEST", "Welcome.");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "sunday");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("yuzhejie2021@sina.com", "HTML", content);
    }

}
