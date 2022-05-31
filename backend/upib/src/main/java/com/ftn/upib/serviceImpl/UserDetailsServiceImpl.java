package com.ftn.upib.serviceImpl;

import com.ftn.upib.model.User;
import com.ftn.upib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("There is no user with this email");
        }else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String role = "ROLE_" + user.getUserType().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail().trim(),
                    user.getPassword().trim(),
                    grantedAuthorities
            );
        }
    }
}
