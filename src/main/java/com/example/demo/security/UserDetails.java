package com.example.demo.security;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("Usuario No Existe");

        }
         return  new org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getPassword(),grantedAuthorities(user.get().getRoleList()));

    }

    private Collection<GrantedAuthority> grantedAuthorities(List<Role> roles){
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }


}
