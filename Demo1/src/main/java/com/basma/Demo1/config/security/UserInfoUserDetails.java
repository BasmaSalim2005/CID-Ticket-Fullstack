package org.dxc.khouna.configuration.security;


import org.dxc.khouna.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {


    private String email;
    private String password;
    private List<GrantedAuthority> authorities;


    public UserInfoUserDetails(User userInfo) {
        email = userInfo.getEmail();
        password = userInfo.getPassword();

        authorities = userInfo.getProfils()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getProfilName().name())) // Ne pas ajouter le préfixe "ROLE_"
                .collect(Collectors.toList());

    }
    public GrantedAuthority convertToGrantedAuthority(String roleName){
        return new SimpleGrantedAuthority(roleName);
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
        return email;
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
