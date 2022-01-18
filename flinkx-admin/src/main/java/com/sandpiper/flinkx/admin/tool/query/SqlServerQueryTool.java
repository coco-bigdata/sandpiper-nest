package com.sandpiper.flinkx.admin.tool.query;

import com.sandpiper.flinkx.admin.entity.JobDatasource;

import java.sql.SQLException;

/**
 * sql server
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/2
 */
public class SqlServerQueryTool extends BaseQueryTool implements QueryToolInterface {
    public SqlServerQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
