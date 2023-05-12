package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Order(1)
@Component
// @Aspect
public class HogeAspect {

	@Autowired
	private HttpServletRequest request;

  // Beforeのサンプル
	@Before("execution(* *..*.*Controller.*(..))")
	public void beforeSample(JoinPoint joinPoint) {
		System.out.println("[Before Hoge]メソッド開始:" + joinPoint.getSignature());
	}

  @Around("execution(* *..*.*Controller.*(..))")
	public Object aroundSample(ProceedingJoinPoint joinPoint) throws Throwable {
		// System.out.println("[Around Hoge]メソッド開始:" + joinPoint.getSignature());
		System.out.println("[Around Hoge]メソッド開始:" + joinPoint.getTarget().getClass().getName());
		System.out.println("[Around Hoge]メソッド開始:" + request.getRequestURI());

		try {
			// メソッド実行
			Object result = joinPoint.proceed();
			System.out.println("[Around Hoge]メソッド終了:" + joinPoint.getSignature());
			return result;
		} catch(Exception e) {
			System.out.println("[Around Hoge]メソッド異常終了:" + joinPoint.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
}
