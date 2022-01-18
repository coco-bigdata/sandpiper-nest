package com.sandpiper.flinkx.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.sandpiper.flinkx.admin.dto.FlinkXJsonBuildDto;
import com.sandpiper.flinkx.admin.entity.JobDatasource;
import com.sandpiper.flinkx.admin.service.FlinkxJsonService;
import com.sandpiper.flinkx.admin.service.JobDatasourceService;
import com.sandpiper.flinkx.admin.tool.flinkx.FlinkxJsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @Author: sandpiper-nest
 * @Date: 2020/9/16 11:14
 * @Description:  JSON构建实现类
 **/
@Service
public class FlinkxJsonServiceImpl implements FlinkxJsonService {

    @Autowired
    private JobDatasourceService jobJdbcDatasourceService;

    @Override
    public String buildJobJson(FlinkXJsonBuildDto FlinkXJsonBuildDto) {
        FlinkxJsonHelper flinkxJsonHelper = new FlinkxJsonHelper();
        // reader
        JobDatasource readerDatasource = jobJdbcDatasourceService.getById(FlinkXJsonBuildDto.getReaderDatasourceId());
        flinkxJsonHelper.initReader(FlinkXJsonBuildDto, readerDatasource);
        // writer
        JobDatasource writerDatasource = jobJdbcDatasourceService.getById(FlinkXJsonBuildDto.getWriterDatasourceId());
        flinkxJsonHelper.initWriter(FlinkXJsonBuildDto, writerDatasource);

        return JSON.toJSONString(flinkxJsonHelper.buildJob());
    }
}