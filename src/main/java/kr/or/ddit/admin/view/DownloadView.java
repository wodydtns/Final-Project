package kr.or.ddit.admin.view;

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

import kr.or.ddit.vo.CsCenterAttVO;

public class DownloadView extends AbstractView {

	@Value("#{appInfo['attatchPath']}")
	File saveFolder;
	@Resource(name="appInfo")
	Properties props;
//	@PostConstruct
//	public void init() {
//		System.err.println(saveFolder.getAbsolutePath());
//	}
	
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CsCenterAttVO attatch =  (CsCenterAttVO) model.get("attach");
		
		String savename = attatch.getTemp_nm();
		String filename = attatch.getOri_nm();
		filename = getFilenameForSend(filename, request);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		response.setHeader("Content-Length", attatch.getCs_size()+"");
		File saveFile = new File(saveFolder, savename);
		try(
			OutputStream os = response.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
		}
	}

	private String getFilenameForSend(String filename, HttpServletRequest req) throws UnsupportedEncodingException {
		String userAgent = req.getHeader("User-Agent");
		if(StringUtils.containsIgnoreCase(userAgent, "trident")) {
			filename = URLEncoder.encode(filename, "UTF-8");
		}else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		return filename;
	}

}










