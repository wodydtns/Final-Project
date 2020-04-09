package kr.or.ddit.login.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * @author 작성자명
 * @since 2020. 3. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2020. 3. 11.      김혜정       최초작성
 * Copyright (c) 2020 by DDIT All right reserved
 * </pre>
 */
@Controller
public class naverTokenController {

	@GetMapping(value = "/naver/token.do")
	public String token(HttpServletRequest req) {
		// 세션 얻기
		HttpSession session = req.getSession();

		String token = (String) session.getAttribute("access_token");// 네이버 로그인 접근 토큰;
		String header = "Bearer " + token; // Bearer 다음에 공백 추가
		try {
			//토큰이용해서 프로필 api 호출
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
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

			System.out.println(response.toString());

			JSONParser parser = new JSONParser();

			JSONObject result = (JSONObject) parser.parse(response.toString());

			((JSONObject) result.get("response")).get("email");

			String email = (String) ((JSONObject) result.get("response")).get("email");
			String name = (String) ((JSONObject) result.get("response")).get("name");

			session.setAttribute("email", email);
			session.setAttribute("name", name);

			System.out.println("email" + email);
			System.out.println("name" + name);

			System.out.println(response.toString());
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//로그인 후 출력할 페이지 리턴하기
		return "naver/postCallback";
	}
}
