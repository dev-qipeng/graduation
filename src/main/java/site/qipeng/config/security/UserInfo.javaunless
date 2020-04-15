package site.qipeng.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserInfo implements UserDetails {

    private String username;
    private String password;
    private boolean isAccountNonExpired  =true;
    private boolean isAccountNonLocked  =true;
    private boolean isCredentialsNonExpired  =true;
    private boolean isEnabled  =true;
    private Set<AuthorityInfo> authorityInfos = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public Set<AuthorityInfo> getAuthorityInfos() {
        return authorityInfos;
    }

    public void setAuthorityInfos(Set<AuthorityInfo> authorityInfos) {
        this.authorityInfos = authorityInfos;
    }
}
