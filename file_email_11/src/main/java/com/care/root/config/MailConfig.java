package com.care.root.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Bean
    public static JavaMailSender mailSender() {
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setHost("smtp.gmail.com"); //smtp : 메일 전송할때 약속된 프로토콜 방식. 메일 보낼떈 무조건 smtp여야함. smtp서버 설정
        jms.setPort(587); //구글에서 제공해주는 포트번호. 구글 한정 번호
        jms.setUsername("larunaru0386@gmail.com"); //계정
        jms.setPassword("szdyctfobxtvjhcm"); //비밀번호 
        //일반 비밀번호로 안됨. 구글 앱 비밀번호 생성

        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp"); //smtp : 이메일 프로토콜(통신규약)
        prop.setProperty("mail.smtp.auth", "true"); 
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.debug", "true");
        jms.setJavaMailProperties(prop);

        return jms;
    }
}

