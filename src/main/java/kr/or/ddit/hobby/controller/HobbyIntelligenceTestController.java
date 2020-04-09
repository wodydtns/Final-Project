package kr.or.ddit.hobby.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.vo.HobbyIntelScoreVO;
import kr.or.ddit.vo.MemberVO;

@Controller
public class HobbyIntelligenceTestController {

	@GetMapping(value="hobby/hobbyIntelligenceForm.do")
	public String intelForm() {
		return "hobby/hobbyIntelligenceTest";
		
	}
	
	@GetMapping(value="hobby/hobbyIntelligenceTest.do")
	public String calculateIntelli(
			HobbyIntelScoreVO score,Model model
			, @RequestParam(name="score1") String score1
			, HttpSession session
			) {
		int seven = score.getScore7();
		System.out.println(seven);
		int nine = score.getScore9();
		if(seven !=0 ) {
			switch (seven) {
			case 1:
				seven = 5;
				break;
			case 2:
				seven = 4;
				break;
			case 4:
				seven = 2;
				break;
			case 5:
				seven = 1;
				break;
			default:
				break;
			}
		}
		if(nine !=0 ) {
			switch (seven) {
			case 1:
				seven = 5;
				break;
			case 2:
				seven = 4;
				break;
			case 4:
				seven = 2;
				break;
			case 5:
				seven = 1;
				break;
			default:
				break;
			}
		}
		
		int extravert = score.getScore1()+score.getScore6();
		int neuro = score.getScore5()+score.getScore10();
		int conscient = score.getScore4()+ nine;
		int agreeable = score.getScore2() + seven+score.getScore12();
		int open = score.getScore3() + score.getScore8()+score.getScore11();
		
		java.util.Map<String, Object> scoreMap = new HashMap<>();
		scoreMap.put("extravert", extravert);
		scoreMap.put("neuro", neuro);
		scoreMap.put("conscient", conscient);
		scoreMap.put("agreeable", agreeable);
		scoreMap.put("open", open);
		MemberVO membervo =  (MemberVO) session.getAttribute("authMember");
		String member = membervo.getMem_email();
		scoreMap.put("member", member);
		model.addAttribute("scoreMap", scoreMap);
		return "hobby/hobbyIntelligenceView";
	}
}
