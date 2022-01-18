package com.sandpiper.flinkx.admin.core.route.strategy;

import com.sandpiper.flinkx.core.biz.model.ReturnT;
import com.sandpiper.flinkx.core.biz.model.TriggerParam;
import com.sandpiper.flinkx.admin.core.route.ExecutorRouter;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteFirst extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList){
        return new ReturnT<String>(addressList.get(0));
    }

}
