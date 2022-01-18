package com.sandpiper.flinkx.admin.service;

import com.sandpiper.flinkx.admin.dto.FlinkXJsonBuildDto;

/**
 * com.sandpiper.flinkx json构建服务层接口
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/1
 */
public interface FlinkxJsonService {

    /**
     * build flinkx json
     *
     * @param dto
     * @return
     */
    String buildJobJson(FlinkXJsonBuildDto dto);
}
