package kr.or.ddit.pmsproject.view;

import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.PMSAttVO;

public class ProjectBoardDownloadView extends AbstractView {

	@Value("#{appInfo['projectBoardAttatchPath']}")
	File saveFolder;
	
	@Resource(name="appInfo")
	Properties props;
	
	@Override
	protected void renderMergedOutputModel(
				Map<String, Object> model, 
				HttpServletRequest request,
				HttpServletResponse response
			) throws Exception {
		PMSAttVO attatch =  (PMSAttVO) model.get("attatch");
		// 여기서 뷰를 해결해서 내보낸다.
		String savename = attatch.getTemp_nm(); // 다운로드 네임 설정 부분
		String filename = attatch.getOri_nm();
		filename = getFilenameForSend(filename, request);
		
		// 특수문자가 포함되어 있을 것 같으면, 바이트 단위로 풀어헤친다.
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);	// 하드코딩 대신 사용
		
		// attatchment : 뷰어가 아니라 다운로드이기 때문에, 첨부되어 있는 파일을 다운받아라, 라는 의미가 된다.
		// \""+filename+"\"" : 중간에 공백이 들어가더라도 파일네임이 처리가 된다.
		response.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		
		// 다운로드 몇 퍼 남았나 기능
		// ContentLength는 att_size 가 Long이기 때문에 바로 사용할 수 없다.
		response.setHeader("Content-Length", attatch.getStu_size()+"");
		File saveFile = new File(saveFolder, savename);
		try(
			OutputStream os = response.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
		}
	}

	private String getFilenameForSend(String filename, HttpServletRequest req) throws UnsupportedEncodingException {
		String userAgent = req.getHeader("User-Agent");
		if( StringUtils.containsIgnoreCase(userAgent, "trident")) { // MS 계열
			filename = URLEncoder.encode(filename, "UTF-8");
		}else if (StringUtils.containsIgnoreCase(userAgent, "chrome")) {	// 비 MS 계열
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		return filename;
	}
}
