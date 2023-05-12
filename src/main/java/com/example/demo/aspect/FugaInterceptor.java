package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
// @Aspect
public class FugaInterceptor {
  // Beforeのサンプル
	@Before("execution(* *..*.*Controller.*(..))")
	public void beforeSample(JoinPoint joinPoint) {
		System.out.println("[Before Fuga]メソッド開始:" + joinPoint.getSignature());
	}

  @Around("execution(* *..*.*Controller.*(..))")
	public Object aroundSample(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("[Around Fuga]メソッド開始:" + joinPoint.getSignature());

		try {
			// メソッド実行
			Object result = joinPoint.proceed();
			System.out.println("[Around Fuga]メソッド終了:" + joinPoint.getSignature());
			return result;
		} catch(Exception e) {
			System.out.println("[Around Fuga]メソッド異常終了:" + joinPoint.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
