package com.sandpiper.flinkx.executor.service.jobhandler;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.sandpiper.flinkx.executor.service.logparse.LogStatistics;
import com.sandpiper.flinkx.core.biz.model.HandleProcessCallbackParam;
import com.sandpiper.flinkx.core.biz.model.ReturnT;
import com.sandpiper.flinkx.core.biz.model.TriggerParam;
import com.sandpiper.flinkx.core.handler.IJobHandler;
import com.sandpiper.flinkx.core.handler.annotation.JobHandler;
import com.sandpiper.flinkx.core.log.JobLogger;
import com.sandpiper.flinkx.core.thread.ProcessCallbackThread;
import com.sandpiper.flinkx.core.util.ProcessUtil;
import com.sandpiper.flinkx.executor.util.SystemUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.FutureTask;

import static com.sandpiper.flinkx.executor.service.command.BuildCommand.buildFlinkXExecutorCmd;
import static com.sandpiper.flinkx.executor.service.jobhandler.FlinkXConstant.DEFAULT_JSON;
import static com.sandpiper.flinkx.executor.service.logparse.AnalysisStatistics.analysisStatisticsLog;



@JobHandler(value = "executorJobHandler")
@Component
public class ExecutorJobHandler extends IJobHandler {

	@Value("${flinkx.executor.jsonpath}")
	private String jsonPath;

	@Value("${flinkx.shell.path}")
	private String flinkXShPath;

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorJobHandler.class);

	@Override
	public ReturnT<String> execute(TriggerParam trigger) {

		int exitValue = -1;
		Thread errThread = null;
		String tmpFilePath;
		LogStatistics logStatistics = null;
		// Generate JSON temporary file
		tmpFilePath = generateTemJsonFile(trigger.getJobJson());

		try {
			String[] cmdarrayFinal = buildFlinkXExecutorCmd(trigger, tmpFilePath, flinkXShPath);
			String cmdstr = "";
			for (int i =0;i<cmdarrayFinal.length;i++) {
				cmdstr+=cmdarrayFinal[i]+ " ";
			}

			final Process process = Runtime.getRuntime().exec(cmdstr);
			String prcsId = ProcessUtil.getProcessId(process);
			JobLogger.log("------------------FlinkX process id: " + prcsId);
			jobTmpFiles.put(prcsId, tmpFilePath);
			// update flinkx process id
			HandleProcessCallbackParam prcs = new HandleProcessCallbackParam(trigger.getLogId(),
					trigger.getLogDateTime(), prcsId);
			ProcessCallbackThread.pushCallBack(prcs);
			// log-thread
			Thread futureThread = null;
			FutureTask<LogStatistics> futureTask = new FutureTask<>(
					() -> analysisStatisticsLog(new BufferedInputStream(process.getInputStream())));
			futureThread = new Thread(futureTask);
			futureThread.start();

			errThread = new Thread(() -> {
				try {
					analysisStatisticsLog(new BufferedInputStream(process.getErrorStream()));
				} catch (IOException e) {
					JobLogger.log(e);
				}
			});

			logStatistics = futureTask.get();
			errThread.start();
			// process-wait
			exitValue = process.waitFor(); // exit code: 0=success, 1=error
			// log-thread join
			errThread.join();
		} catch (Exception e) {
			JobLogger.log(e);
		} finally {
			if (errThread != null && errThread.isAlive()) {
				errThread.interrupt();
			}
			// ??????????????????
			if (FileUtil.exist(tmpFilePath)) {
				FileUtil.del(new File(tmpFilePath));
			}
		}
		if (exitValue == 0) {
			return new ReturnT<>(200, logStatistics.toString());
		} else {
			return new ReturnT<>(IJobHandler.FAIL.getCode(), "command exit value(" + exitValue + ") is failed");
		}
	}


	private String generateTemJsonFile(String jobJson) {
		String tmpFilePath;
		String FlinkXHomePath = SystemUtils.getFlinkxHomePath();
		if (StringUtils.isNotEmpty(FlinkXHomePath)) {
			jsonPath = FlinkXHomePath + DEFAULT_JSON;
		}
		if (!FileUtil.exist(jsonPath)) {
			FileUtil.mkdir(jsonPath);
		}
		tmpFilePath = jsonPath + "jobTmp-" + IdUtil.simpleUUID() + ".conf";
		// ??????json???????????????????????????
		try (PrintWriter writer = new PrintWriter(tmpFilePath, "UTF-8")) {
			writer.println(jobJson);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			JobLogger.log("JSON ???????????????????????????" + e.getMessage());
		}
		return tmpFilePath;
	}

}
