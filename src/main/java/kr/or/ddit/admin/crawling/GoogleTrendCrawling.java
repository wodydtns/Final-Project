package kr.or.ddit.admin.crawling;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class GoogleTrendCrawling extends QuartzJobBean{
	
	@Scheduled(cron="0 * 16 * *")
	public void executeCrawling() throws IOException {
		final List<String> commands = new ArrayList<String>();
		
		commands.add("cmd.exe");
		commands.add("/C");
		commands.add("start;python googleTrendCrawling.py");
		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.directory(new File("D:\\Anaconda"));
		pb.start();
	}

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		try {
			executeCrawling();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
