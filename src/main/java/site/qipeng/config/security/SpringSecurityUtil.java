package site.qipeng.config.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SpringSecurityUtil {

    public static User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) principal;
    }
    public static String getCurrentUsername(){
        return SpringSecurityUtil.getCurrentUser().getUsername();
    }
}
