package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect 
{
//	// AOPの実装(Before)
//	@Before("execution(* *..*.*Controller.*(..))")
//	public void startLog(JoinPoint jp) 
//	{
//		System.out.println("メソッド開始：" + jp.getSignature());
//	}
//	
//	// AOPの実装(After)
//	@After("execution(* *..*.*Controller.*(..))")
//	public void endLog(JoinPoint jp)
//	{
//		System.out.println("メソッド開始：" + jp.getSignature());
//	}
	
	// AOPの実装(Around)
	@Around("execution(* *..*.*Controller.*(..))")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable 
	{
		System.out.println("メソッド開始(Around)：" + jp.getSignature());
		
		try {
			// メソッドの実行
			Object result = jp.proceed();
			
			System.out.println("メソッド開始(Around)：" + jp.getSignature());
			
			return result;
		} catch (Exception e) {
			System.out.println("メソッド異常終了(Around)d：" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
