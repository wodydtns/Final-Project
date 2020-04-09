package kr.or.ddit.careerController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.vo.CareerAPIVO;
/**
 * @author 작성자명
 * @since 2020. 4. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 4. 1.      작성자명   박재욱                 최초작성 - form, 검색 결과 출력
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/careerapi")
public class CareerAPIController {
	
	@GetMapping("careerSearchForm.do")
	public String form() {
		return "/careerapi/careerApi";
	}
	
	
	@GetMapping("careerSearch.do")
	public void doget(
			CareerAPIVO apivo,
			@RequestParam(required=false,defaultValue = "") String ec,
			@RequestParam(required=false,defaultValue = "") String ck,
			@RequestParam(required=false,defaultValue = "") String sc,
			@RequestParam(name="gubun",required=false, defaultValue = "") String gubun,
			HttpServletResponse resp
			) {

		try {
			
			String uc = "&uc="+URLEncoder.encode(apivo.getUc(), "UTF-8");
			String jc ="&jc="+ URLEncoder.encode(apivo.getJc(), "UTF-8");
			String kw ="&kw="+ URLEncoder.encode(apivo.getKw(), "UTF-8");
			String ac1 ="&ac1="+ URLEncoder.encode(apivo.getAc1(), "UTF-8");
			ec = "&ec="+URLEncoder.encode(ec, "UTF-8");
			ck = "&ck="+URLEncoder.encode(ck, "UTF-8");
			System.out.println(ck);
			sc = "&sc="+URLEncoder.encode(sc, "UTF-8");
			System.out.println(sc);
			gubun = "&gubun="+URLEncoder.encode(gubun, "UTF-8");
			System.out.println(gubun);
			String apiURL = "http://api.career.co.kr/open?id=VjfzNMrb5o8yb/LgS8rsPQ=="+uc+jc+kw+ac1+ec+ck+sc+gubun;
			apiURL = apiURL.trim();
			System.out.println(apiURL);
			// 예시 api
//			http://api.career.co.kr/open?id=VjfzNMrb5o8yb/LgS8rsPQ==&kw=asdf&ac1=1&ec=0&ck=&sc=&gubun=0
			//xml 결과
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/xml");
			
			int responseCode = con.getResponseCode();
			
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			JSONObject json = XML.toJSONObject(response.toString());
			
			resp.setHeader("Content-type", "application/json");
			resp.setCharacterEncoding("UTF-8");
			
			resp.getWriter().print(json);
			resp.getWriter().close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
		
}
}
