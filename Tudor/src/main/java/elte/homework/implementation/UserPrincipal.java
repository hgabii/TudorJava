package elte.homework.implementation;

import elte.homework.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User user;
    private List<GrantedAuthority> auths = new ArrayList<>(5);

    public UserPrincipal(User user) {
        this.user = user;
    }

    public java.util.Collection<? extends GrantedAuthority> getAuthorities() { return auths; }
    public java.lang.String getUsername() { return user.getUserName(); }
    public java.lang.String getPassword() { return user.getPassword(); }
    public int getUrsulaId() { return user.getUserId(); }

    public boolean isEnabled() { return true; }
    public boolean isCredentialsNonExpired() { return true; }
    public boolean isAccountNonExpired() { return true; }
    public boolean isAccountNonLocked() { return true; }
}
