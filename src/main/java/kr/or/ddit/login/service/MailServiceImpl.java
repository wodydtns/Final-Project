package kr.or.ddit.login.service;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
/**
 * @author 작성자명
 * @since 2020. 3. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 18.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
public class MailServiceImpl implements MailService {
//메일인증서비스
	 private final JavaMailSender javaMailSender;
	 
	    @Inject
	    public MailServiceImpl(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }
	    
	@Override
	public boolean send(String subject, String text, String from, String to) {
		  MimeMessage message = javaMailSender.createMimeMessage();
		  
	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
	            helper.setSubject(subject);
	            helper.setText(text);
	            helper.setFrom(from);
	            helper.setTo(to);
	 
	            javaMailSender.send(message);
	            return true;
	        }catch (MessagingException e){
	            e.printStackTrace();
	        }
		return false;
	}

}
