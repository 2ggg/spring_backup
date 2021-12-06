package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired JavaMailSender mailSender;
	public void sendMail(String to, String subject, String body) {//받는 사용자, 제목, 내용
		MimeMessage message = mailSender.createMimeMessage(); //받는 사용자 세팅을 위한 값. 아래 세팅을 위해 필요한 것
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8"); //사용자한테 보내기 위한 세팅을 위한 것
			helper.setTo(to);//받는 사람
			helper.setSubject(subject);//제목
			helper.setText(body,true);//메일 내용. true : html형식으로 넘어감
			//helper.setText(body);//메일 내용. 안 적어주면 기본 false로 되며, false일 때는 text로 넘어감
			
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   public void auth(HttpServletRequest request) {
		      HttpSession session = request.getSession(); //세션 넘겨주기
		      String userid = "2ㅎㅎㅎ";
		      String userkey = rand(); //유저 확인용 key(여기선 랜덤한 숫자) 만들기
		      session.setAttribute(userid, userkey); 
		      String body="<h2>안녕하세요 아뱅입니다</h2><hr>"
		            +"<h3>"+userid+" 님</h3>"
		            +"<p>인증하기 버튼을 누르면 로그인 됩니다</p>"
		            +"<a href='http://localhost:8085"
		            +request.getContextPath()+"/auth_check?userid="
		            +userid+"&userkey="+userkey+"'>인증하기</a>";
		      //사용자 이메일로 넘어갈 값
		      sendMail("ljh03922@naver.com","이메일 인증입니다",body);
		   }
		   private String rand() {//특정 유저 확인을 위한 랜덤값 생성 : userkey
		      Random ran = new Random();
		      String str="";
		      int num;
		      while(str.length() != 20) {
		         num = ran.nextInt(75)+48;
		         if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97 && num<=122)) {
		            str+=(char)num;
		         }else {
		            continue;
		         }
		      }
		      return str;
		   }
}
