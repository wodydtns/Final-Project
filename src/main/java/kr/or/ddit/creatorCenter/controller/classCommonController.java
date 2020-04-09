package kr.or.ddit.creatorCenter.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.creatorCenter.dao.IPIDAO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.LODVO;

@ControllerAdvice
public class classCommonController {

	@Inject
	IPIDAO ccDAO;
	
	@ModelAttribute("cateList")
	public List<CategoryVO> selectCateList(){
		return ccDAO.selectCateList();
	}

	@ModelAttribute("levelList")
	public List<LODVO> selectLevelList(){
		return ccDAO.selectLevelList();
	}

	
}
