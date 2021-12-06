package com.care.root.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling	//특정 날짜, 특정 시간, 특정 분에 메소드 실행 가능
public class MyScheduler {
	@Scheduled(cron="*/10 * * * * *")
	//여기 있는 * 위치에는 각 <초 분 시 일 월 년>이 들어감. *는 매번, 모두 포함하는 의미. 10이 맨 앞이므로 10초. cron표현식 검색하기
	//*/10 < 10초마다 매번이란 의미. 10만 적혀있으면 매년 매일 시간 매분 10초 마다 실행
	public void testSc() {//10초에 한 번 씩 
		System.out.println("10초에 한 번 씩 실행");
	}
}
