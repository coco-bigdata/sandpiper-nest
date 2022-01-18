package com.sandpiper.flinkx.core.handler.impl;

import com.sandpiper.flinkx.core.biz.model.ReturnT;
import com.sandpiper.flinkx.core.biz.model.TriggerParam;
import com.sandpiper.flinkx.core.log.JobLogger;
import com.sandpiper.flinkx.core.handler.IJobHandler;

/**
 * glue job handler
 * @author xuxueli 2016-5-19 21:05:45
 */
public class GlueJobHandler extends IJobHandler {

	private long glueUpdatetime;
	private IJobHandler jobHandler;
	public GlueJobHandler(IJobHandler jobHandler, long glueUpdatetime) {
		this.jobHandler = jobHandler;
		this.glueUpdatetime = glueUpdatetime;
	}
	public long getGlueUpdatetime() {
		return glueUpdatetime;
	}

	@Override
	public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
		JobLogger.log("----------- glue.version:"+ glueUpdatetime +" -----------");
		return jobHandler.execute(tgParam);
	}
}
