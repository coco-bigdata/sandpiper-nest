package com.sandpiper.flinkx.admin.tool.query;

import com.sandpiper.flinkx.admin.entity.JobDatasource;

import java.sql.SQLException;

/**
 * hive
 *
 * @author wenkaijing
 * @version 2.0
 * @since 2020/01/05
 */
public class HiveQueryTool extends BaseQueryTool implements QueryToolInterface {
    public HiveQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
