package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//JoinPoint는 결합점(위빙)에 대한 정보를 받음.
@Aspect // advice + pointcut
public class LoggingAdvice {
	private static Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))") // .. 중간에 몇단계와도 상관없단뜻.
	public void dummy() {}
	
	
	// 호출 전 후를 감싸서 위빙, 그럼 위에 두개 필요없어짐. // ProceedingJoinPoint 직진, 상위에 JoinPoint가 있어.
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		String targetName = target.getClass().getSimpleName(); // 로직 명
		Signature signature = joinPoint.getSignature();
		String mtdName = signature.getName(); // 로직 메서드명
		Object[] args = joinPoint.getArgs();
		logger.info("{}.{} 호출 전, 전달 파라미터 : {} \n", targetName, mtdName, Arrays.toString(args));
		long start = System.currentTimeMillis();
		Object retValue = joinPoint.proceed(args); // 어드바이스 안에서 타겟이 직접적으로 호출됨.// target 호출
		long end = System.currentTimeMillis();
		logger.info("{}.{} 호출 종료-{}ms, 반환값 : {} \n", targetName, mtdName, (end-start), retValue);
		return retValue; // 프로시드라는 녀석에 의해 타겟을 직접호출했지만 리턴값은 컨슈머에게 직접 호출(?)
	}
}
