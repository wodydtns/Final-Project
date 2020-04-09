package kr.or.ddit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@AuthenticationPrincipal(expression="member")
@Target(ElementType.PARAMETER) // 메소드의 파라미터로만 사용 가능
@Retention(RetentionPolicy.RUNTIME) // 핸들러어댑터가 인지하고 넣길 원하니깐 실행할때까지 살아있어야함.
public @interface AuthMember {

}
