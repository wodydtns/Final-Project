package saramin;

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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 사람인 채용공고 API 샘플
 */

@Controller
public class searchController {

	@GetMapping(value="/saramin/search.do")
	public void doget(
			@RequestParam(name="query") String param,
			HttpServletResponse resp
			) {
		  String accessKey = "s5iEygnEkFmi6M30COESGl5PkuCpUW38cf6eTfAYEHGV1lrVeMi"; // 발급받은 accessKey"

		try {
			System.out.println(param);
			String text = URLEncoder.encode(param, "UTF-8");
			System.out.println(text);
			String apiURL = "https://oapi.saramin.co.kr/job-search?access-key="+accessKey+"&keywords="+text;
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