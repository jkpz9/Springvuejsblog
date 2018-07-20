package itus._2014.king.springvuejsblog.configs;

import itus._2014.king.springvuejsblog.entities.Role;
import itus._2014.king.springvuejsblog.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerUserDetail implements UserDetails {

    private Collection<? extends  GrantedAuthority> authorities;

    private  String username;
    private  String password;

    public CustomerUserDetail(User user)
    {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());

    }


    private Collection<? extends  GrantedAuthority> translate(List<Role> roles)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();

        roles.forEach(role -> {
            String _role = role.getName().toUpperCase();
            if(!_role.startsWith("ROLE_")) _role = "ROLE_" + _role;
            authorities.add(new SimpleGrantedAuthority(_role));
        });
        return authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
