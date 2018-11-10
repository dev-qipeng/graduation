package site.qipeng.configuration;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Druid 配置类
 */
@Configuration
public class DruidConfiguration {

    private static Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

    @Bean
    public ServletRegistrationBean<StatViewServlet> druidServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        servletRegistrationBean.addInitParameter("allow","*");

        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter(){

        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","/assets/*,*.css,*.js,*.gif,*.jpg,*.png,*.ico,*.eot,*.svg,*.ttf,*.woff,*.jsp,*.tpl,/druid/*");
        return filterRegistrationBean;
    }
}
