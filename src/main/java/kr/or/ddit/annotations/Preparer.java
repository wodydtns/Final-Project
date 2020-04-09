package kr.or.ddit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //클래스에서만 이 어노테이션 사용 가능
@Retention(RetentionPolicy.RUNTIME) // 이 어노테이션 언제까지 살린 것인지.
public @interface Preparer {

}
