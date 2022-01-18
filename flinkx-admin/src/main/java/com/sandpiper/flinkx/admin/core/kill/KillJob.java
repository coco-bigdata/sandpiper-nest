package com.sandpiper.flinkx.admin.core.kill;

import com.sandpiper.flinkx.core.biz.model.ReturnT;
import com.sandpiper.flinkx.core.biz.model.TriggerParam;
import com.sandpiper.flinkx.core.enums.ExecutorBlockStrategyEnum;
import com.sandpiper.flinkx.core.glue.GlueTypeEnum;
import com.sandpiper.flinkx.admin.core.trigger.JobTrigger;

import java.util.Date;

/**
 * flinkx-job trigger
 * Created by jingwk on 2019/12/15.
 */
public class KillJob {

    /**
     * @param logId
     * @param address
     * @param processId
     */
    public static ReturnT<String> trigger(long logId, Date triggerTime, String address, String processId) {
        ReturnT<String> triggerResult;
        TriggerParam triggerParam = new TriggerParam();
        triggerParam.setJobId(-1);
        triggerParam.setExecutorHandler("killJobHandler");
        triggerParam.setProcessId(processId);
        triggerParam.setLogId(logId);
        triggerParam.setGlueType(GlueTypeEnum.BEAN.getDesc());
        triggerParam.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.SERIAL_EXECUTION.getTitle());
        triggerParam.setLogDateTime(triggerTime.getTime());
        if (address != null) {
            triggerResult = JobTrigger.runExecutor(triggerParam, address);
        } else {
            triggerResult = new ReturnT<>(ReturnT.FAIL_CODE, null);
        }
        return triggerResult;
    }

}
