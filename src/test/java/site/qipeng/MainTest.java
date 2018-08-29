package site.qipeng;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class MainTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DruidDataSource dataSource;
}
