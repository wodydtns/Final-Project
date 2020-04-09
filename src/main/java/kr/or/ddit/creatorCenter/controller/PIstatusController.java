package kr.or.ddit.creatorCenter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.creatorCenter.service.IPIService;

@Component("pIstatusController")
public class PIstatusController{

	private static Logger logger = LoggerFactory.getLogger(PIstatusController.class);

	@Inject
	IPIService service;
	
	
	public void piChange() {
		Map<String, Object> pMap = new HashMap<>();
		service.modifyPIStatus(pMap);
		logger.info("pMap: {}", pMap);
		int rowcnt = (int) pMap.get("rowcnt");
		logger.info("{} 사전조사 종료 처리 완료..", rowcnt);
	}

	
	
	
}
