package kr.or.ddit.pay.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import kr.or.ddit.vo.KakaoPayApprovalVO;
import kr.or.ddit.vo.KakaoPayReadyVO;
import kr.or.ddit.vo.PaymentVO;
import lombok.extern.java.Log;
 
@Log
@Service
public class KakaoPayService {
		 
	   private static final String HOST = "https://kapi.kakao.com";
	    
	    private KakaoPayReadyVO kakaoPayReadyVO;
	    private KakaoPayApprovalVO kakaoPayApprovalVO;
	    
	    //카카오톡 실행전 결제할 클래스 정보띄우기
//	    public String kakaoPayPrev(PaymentVO pay) {
//	    	
//	    	return "redirect:" + kakaoPayReady(pay);
//	    }
	    
	    
	    
//	    public String kakaoPayReady(PaymentVO pay) {
//	 
//	        RestTemplate restTemplate = new RestTemplate();
//	 
//	        // 서버로 요청할 Header
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.add("Authorization", "KakaoAK " + "0e4d3ba9bac3fc419c81dd3cddfd967a");
//	        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
//	        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
//	        
//	        // 서버로 요청할 Body
//	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//	        params.add("cid", "TC0ONETIME");
//	        params.add("partner_order_id", "1001");
//	        params.add("partner_user_id", pay.getMem_email());
//	        params.add("item_name", pay.getPi_nm());
//	        params.add("quantity", "1");
//	        params.add("total_amount", pay.getPay_amt().toString());
//	        params.add("tax_free_amount", "100");
//	        params.add("approval_url", "http://localhost/JinDam/kakaoPaySuccess");
//	        params.add("cancel_url", "http://localhost/JinDam/kakaoPayCancel");
//	        params.add("fail_url", "http://localhost/JinDam/kakaoPaySuccessFail");
//	 
//	         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
//	 
//	        try {
//	            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
//	            
//	            log.info("" + kakaoPayReadyVO);
//	            
//	            return kakaoPayReadyVO.getNext_redirect_pc_url();
//	 
//	        } catch (RestClientException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        } catch (URISyntaxException e) {
//	            // TODO Auto-generated catch block
//	            e.printStackTrace();
//	        }
//	        
//	        return "/pay";
//	        
//	    }
	    
	    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, PaymentVO pay, KakaoPayReadyVO kakaoPayReadyVO) {
	 
	        log.info("KakaoPayInfoVO............................................");
	        log.info("-----------------------------");
	        
	        RestTemplate restTemplate = new RestTemplate();
	 
	        // 서버로 요청할 Header
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + "796f98dd52b6957f259e8cbeeb30f70b");
	        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	 
	        // 서버로 요청할 Body
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//	        params.add("cid", "TC0ONETIME");
//	        params.add("tid", kakaoPayReadyVO.getTid());
//	        params.add("partner_order_id", "1001");
//	        params.add("partner_user_id", "gorany");
//	        params.add("pg_token", pg_token);
//	        params.add("total_amount", "2100");
	        
	        params.add("cid", "TC0ONETIME");
	        params.add("tid", kakaoPayReadyVO.getTid());
	        params.add("partner_order_id", "1001");
	        params.add("partner_user_id", pay.getMem_email());
	        params.add("pg_token", pg_token);
	        params.add("total_amount", pay.getFinal_pay_amt().toString());
	        
	        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	        
	        try {
	            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
	            log.info("" + kakaoPayApprovalVO);
	          
	            return kakaoPayApprovalVO;
	        
	        } catch (RestClientException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        return null;
	    }
	    
	    
	    
	    
	    
	    
	}
