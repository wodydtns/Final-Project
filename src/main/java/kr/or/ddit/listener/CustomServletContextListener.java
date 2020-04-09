package kr.or.ddit.listener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;

@WebListener
public class CustomServletContextListener 
			implements ServletContextListener,
						ServletContextAttributeListener,
						HttpSessionListener
						 {
	public static final String CURRENTCOUNT = "currentCount";
	public static final String SAVEDCOUNT = "savedCount";
	public static final String USERLIST = "userList";
	
	private ServletContext application; // 얘는 똑같이 싱글턴이라서 전역변수로 뺀 것.
	
	public void contextInitialized(ServletContextEvent sce)  { 
		System.err.println("Context initialized");
		application = sce.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute(CURRENTCOUNT, new Integer(0));
		application.setAttribute(SAVEDCOUNT, new Integer(0));
		Set<UserDetails> userList = new HashSet<>(); // MemberVO에 대해서 두개 이상의 중복을 허용하지 않기 위해 List대신 Set을 씀. 상태비교메서드가 있어야지 주소 같아서 비교 가능(@EqualsAndHashCode(of= {"mem_id", "mem_regno1", "mem_regno2"}))
		application.setAttribute(USERLIST, userList);
	}

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.err.println("Context destroyed");
//    	ServletContext application = sce.getServletContext();
    	application.removeAttribute("cPath");
    	
    }

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) { // attribute (key, value)
		String attrName = event.getName(); // 속성명
		Object value = event.getValue(); // 속성값
		System.err.printf("application scope [%s] attribute added : [%s]\n", attrName, value);

	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		String attrName = event.getName();
		Object value = event.getValue(); 
		System.err.printf("application scope [%s] attribute removed : [%s]\n", attrName, value);
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) { // 저장되어있는 속성값이 변경되면
		
	}

//	HttpSession session; // application대상으로 만들어지는 것이 아니라 세션을 대상으로 만들어짐. 그래서 전역변수로 관리하면 안됨.
// @WebListener 는 싱글턴으로 관리되기 때문. 
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 새로운 클라이언트가 우리 서버에 접속했다는 뜻. 현재접속자수(현재서버에 만들어진 세션의 개수 이때 count)
		HttpSession session = se.getSession();
		System.err.printf("세션{%s} 생성\n", session.getId());
		Integer currentCount = (Integer) application.getAttribute(CURRENTCOUNT);
		currentCount = currentCount + 1;
		application.setAttribute(CURRENTCOUNT, currentCount);
		Integer savedCount = (Integer) application.getAttribute(SAVEDCOUNT);
		savedCount = savedCount + 1;
		application.setAttribute(SAVEDCOUNT, savedCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 세션이 소멸될때 discount
		HttpSession session = se.getSession();
		System.err.printf("세션{%s} 소멸\n", session.getId());
		Integer currentCount = (Integer) application.getAttribute(CURRENTCOUNT);
		currentCount = currentCount - 1;
		application.setAttribute(CURRENTCOUNT, currentCount);
	}

	
}
