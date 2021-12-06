package com.care.root;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.reflect.MethodSignature;

@Component
/*
스프링 빈 설정 XML 파일에 <bean id="..." class="..."/>나 자바 @Configuration 클래스에서 @Bean을 붙여 빈을 등록하던 것처럼 빈 클래스에 @Component 애노테이션을 붙여 빈을 등록할 수 있다.

즉 @Component를 사용해서 빈 설정 파일이 아니라 빈 클래스에서 빈을 직접 등록할 수 있다.
 */
@Aspect //aop임을 명시. 공통기능(aop)로 사용할거라는 명시. 여러군데서 사용되는 중복되는 코드(공통으로 사용하는 코드)
@EnableAspectJAutoProxy //aop가 이 클래스로 연결된다는 표시
public class AOPConfig {
	//
	//(..) : 매개변수가 0개 이상인 것
	//매개변수가 0개면 ()만 쓰면 됨
   //@Before("execution(* com.care.root.controller.TestController.buy_form(..))")
	//* : 모든 반환타입을 받아줌 (공백을 안 넣으면 에러가 뜰수있음)
   //반드시 ProceedingJoinPoint가 들어가야함
   @Around("execution(public String buy_form(..))")//특정 메소드가 호출될때 실행 전,후 두 번 동작한다. 
   
   public void commonAop(ProceedingJoinPoint joinpoint) {
      try {
         System.out.println("==== 컨트롤러 공통기능 시작 ===="); //buy_form 실행 전 동작
         joinpoint.proceed(); 
         //여기서 잠깐 일시정지하고 buy_form으로 넘어가는 위치, buy_form이 끝나면 다시 돌아옴
         //순서: buy_form 호출시 > ==== 컨트롤러 공통기능 시작 ==== > buy_form 동작 > ==== 컨트롤러 공통기능 종료 ====
         System.out.println("==== 컨트롤러 공통기능 종료 ===="); //buy_form 실행 후 동작
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }
   @Before("execution(* com.care.root.service.TestServiceImpl.buy(..))")
   //buy 실행 전 동작
   public void commonAop02() {
      System.out.println("==== service 공통 기능(buy) 시작====");
   }
   @After("execution(* com.care.root.service.TestServiceImpl.dbResult(..))")
   //dbResult 실행 후 동작
   public void commonAop03() {
      System.out.println("==== service 공통 기능(db_result) 종료====");
   }
   
   Logger LOG = Logger.getGlobal();
   @Around("execution(* com.care.root.controller.*.*(..))")
   public Object commonAop00(ProceedingJoinPoint joinpoint) {
      MethodSignature methodSignature = (MethodSignature)joinpoint.getSignature();
      Method method = methodSignature.getMethod();
      Object[] objects = joinpoint.getArgs(); //메소드에 들어가는 알규먼트
      for(Object param : objects) {
         LOG.log(Level.INFO, "들어온 파라미터 값 : "+param);
      }
      LOG.log(Level.INFO,"실행된 메소드 : "+method.getName());
      Object obj=null;
      try {
         obj = joinpoint.proceed();
      } catch (Throwable e) {
         e.printStackTrace();
      }
      return obj;
      //꼭 return으로 proceed를 돌려줘야 페이지 이동됨. void면 404에러 뜸
   }
}
