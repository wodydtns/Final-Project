package kr.or.ddit.pay.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import kr.or.ddit.pay.dao.KakaoPayDAO;
import kr.or.ddit.pay.service.KakaoPayService;
import kr.or.ddit.vo.CouponClassVO;
import kr.or.ddit.vo.KakaoPayReadyVO;
import kr.or.ddit.vo.PaymentVO;
import lombok.extern.java.Log;

@Log
@Controller
public class KakaoPayController {

	  @Inject
	  private KakaoPayService service;
	  
	  @Inject   
	  private KakaoPayDAO dao;
	  
	  
	  
	  private static final String HOST = "https://kapi.kakao.com";
	    
	    private KakaoPayReadyVO kakaoPayReadyVO;
	  
	  
	    
	    @GetMapping("/kakaoPay.do")
	    public String kakaoPayGet(
	    		@RequestParam(name="what",required=true) int cl_cd, 
	    		@RequestParam(name="cname",required=true) String pi_nm,
	    		@RequestParam(name="who",required=true) String mem_email,
	    		@RequestParam(name="amt",required=true) int cl_fee,
	    		@RequestParam(name="tmp",required=true) String temp_nm,
	    		Model model
	    		) {
	    	log.info("kakaoPay Get............................................");
	        
	    	//해당클래스 정보담기
	    	PaymentVO pay = new PaymentVO();
	        pay.setCl_cd(cl_cd);
	        pay.setPi_nm(pi_nm);
	        pay.setMem_email(mem_email);
	        pay.setPay_amt(cl_fee);
	        pay.setTemp_nm(temp_nm);
	        
	        //현재 세션이 가지고있는 쿠폰리스트 담기
	        List<CouponClassVO> mycoupon = dao.selectMyCoupon(mem_email);
	        model.addAttribute("pay", pay);
	        model.addAttribute("mycoupon", mycoupon);
	        
	        return "pay/paymain";
	    }
	    
	    @PostMapping("/kakaoPay.do")
	    public String kakaoPayReady(PaymentVO pay, HttpSession session) {
	   	 
	        RestTemplate restTemplate = new RestTemplate();
	 
	        // 서버로 요청할 Header
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + "796f98dd52b6957f259e8cbeeb30f70b");
	        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	        
	        // 서버로 요청할 Body
	        MultiValueMap<String, String> params  = new LinkedMultiValueMap<String, String>();
	        params.add("cid", "TC0ONETIME");
	        params.add("partner_order_id", "1001");
	        params.add("partner_user_id", pay.getMem_email());
	        params.add("item_name", pay.getPi_nm());
	        params.add("quantity", "1");
	        params.add("total_amount", pay.getFinal_pay_amt().toString());
	        params.add("tax_free_amount", "100");
	        params.add("approval_url", "http://localhost/JinDam/kakaoPaySuccess");
	        params.add("cancel_url", "http://localhost/JinDam/kakaoPayCancel");
	        params.add("fail_url", "http://localhost/JinDam/kakaoPaySuccessFail");
	 
	        
	   
	     
	         HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	 
	        try {
	            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
	            
	            log.info("vo정보:" + kakaoPayReadyVO);
	            session.setAttribute("pay", pay); //pay정보넘기기위해 세팅
	            return "redirect:" + kakaoPayReadyVO.getNext_redirect_pc_url();
	 
	        } catch (RestClientException e) {
	        	System.err.println("RestClientException 에러요");
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
	        
	        return "pay/fail";
	        
	    }
	    
	    
	    @GetMapping("/kakaoPaySuccess")
	    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session) {
	        log.info("kakaoPaySuccess get............................................");
	        log.info("kakaoPaySuccess pg_token : " + pg_token);
	        
	        //결제에 insert쳐주기
	        PaymentVO pay = (PaymentVO) session.getAttribute("pay");
	        int cnt = dao.insertPay(pay);
	        if(cnt>0) {
	        	//쿠폰쓴게 있으면 사용완료 update해주기
	        	if(pay.getCp_have_cd()!=null) {
	        		dao.updateCouponHave(pay.getCp_have_cd());
	        	}
	        }else {
	        	System.err.println("에러요");
	        }
	        	
	        model.addAttribute("info", service.kakaoPayInfo(pg_token,pay, kakaoPayReadyVO));
	        return "pay/kakaoPaySuccess";
	    }
}
