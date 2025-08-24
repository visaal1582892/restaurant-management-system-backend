package com.rms.restaurant_management_system_backend.service.implementation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;
<<<<<<< HEAD
=======

    public CustomUserDetailsServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
>>>>>>> 246583a6e4415d9a9f084eb8ed51290f6e3933ef

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT * FROM credentials WHERE username=?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username},
                    (rs, rowNum) -> User.withUsername(rs.getString("username"))
                            .password(rs.getString("password"))
                            .roles(rs.getString("authority").toUpperCase()).build());
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}

