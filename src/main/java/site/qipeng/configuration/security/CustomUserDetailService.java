package site.qipeng.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import site.qipeng.service.UserService;

import java.util.*;


@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        site.qipeng.entity.User userByUsername = userService.getUserByUsername(s);
        if (userByUsername == null) {
            throw new UsernameNotFoundException("Username not found.");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userByUsername.getNickname());
        userInfo.setPassword(userByUsername.getPassword());
        return new User(userInfo.getUsername(), userInfo.getPassword(), getAuth());
    }

    private Collection<? extends GrantedAuthority> getAuth() {
        return new ArrayList<AuthorityInfo>();
    }
}
