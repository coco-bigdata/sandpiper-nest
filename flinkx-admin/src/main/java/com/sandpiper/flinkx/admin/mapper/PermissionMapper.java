package com.sandpiper.flinkx.admin.mapper;

import com.sandpiper.flinkx.admin.entity.JobPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionMapper {

    List<JobPermission> findAll();

    List<JobPermission> findByAdminUserId(int userId);
}