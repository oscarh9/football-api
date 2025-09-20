package com.oscar.football_api.config.security;

import com.oscar.football_api.config.properties.SecurityProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InMemoryAdminUserDetailService implements UserDetailsService {

    private final SecurityProperties securityProperties;
    private final PasswordEncoder passwordEncoder;

    private InMemoryUserDetailsManager delegate;

    @PostConstruct
    public void init() {
        String username = securityProperties.getAdmin().getUsername();
        String password = securityProperties.getAdmin().getPassword();

        if (username == null || password == null) {
            throw new IllegalStateException("Admin credentials not configured. Check ADMIN_USERNAME / ADMIN_PASSWORD env vars.");
        }

        UserDetails admin = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("ADMIN")
                .build();

        delegate = new InMemoryUserDetailsManager(admin);
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return delegate.loadUserByUsername(username);
    }
}