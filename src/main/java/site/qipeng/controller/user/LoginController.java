package site.qipeng.controller.user;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.qipeng.entity.User;
import site.qipeng.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DruidDataSource dataSource;

    @Autowired
    private UserService userService;

    /**
     * 启动执行
     * @return
     */
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login(String username, String password, HttpServletRequest request){
//        User user = userService.login(username, password);
//        if (user != null) {
//            request.getSession().setAttribute("currentUser", user);
//        }
//        return "index";
//    }

    @RequestMapping(value = "/testInsert", method = RequestMethod.GET)
    public void testInsert() {

        jdbcTemplate.execute("insert into user values ('1','123','123')");

    }

    @ResponseBody
    @RequestMapping(value = "/testQuery", method = RequestMethod.GET)
    public Map<String, Object> testQuery() {

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from user");
        Map<String, Object> resp = new HashMap<String, Object>();
        resp.put("result", 1);
        resp.put("data", maps);
        return resp;
    }

    @ResponseBody
    @RequestMapping(value = "/testDruid", method = RequestMethod.GET)
    public void testDruidDateSource() throws SQLException {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pstat = conn.prepareStatement("insert into user values (?,?,?)");
        pstat.setString(1, "2");
        pstat.setString(2, "admin");
        pstat.setString(3, "admin");
        pstat.executeUpdate();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from user");
        conn.commit();
        while (rs.next()) {
           logger.info("id=" + rs.getInt("id") + ", username=" + rs.getString("username") + ", password=" + rs.getString("password"));
        }
    }

}
