package com.sandpiper.flinkx.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sandpiper.flinkx.admin.mapper.JobRegistryMapper;
import com.sandpiper.flinkx.admin.service.JobRegistryService;
import com.sandpiper.flinkx.admin.entity.JobRegistry;
import org.springframework.stereotype.Service;

/**
 * JobRegistryServiceImpl
 * @author jingwk
 * @since 2019-03-15
 * @version v2.1.1
 */
@Service("jobRegistryService")
public class JobRegistryServiceImpl extends ServiceImpl<JobRegistryMapper, JobRegistry> implements JobRegistryService {

}