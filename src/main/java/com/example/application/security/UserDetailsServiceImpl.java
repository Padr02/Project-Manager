package com.example.application.security;

import com.example.application.data.RoleEnum;
import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;
   UserEntity user;

   @Transactional
   @Override
    public UserDetails loadUserByUsername(final String userName)
           throws UsernameNotFoundException {
       UserEntity user = userRepository.findByUsername(userName);
      //List<GrantedAuthority> authorities = buildUserForAuthentication(user.getRole());
      return new UserDetailsImpl(user);
   }

   // Converts user to spring.springframework.security.core.userdetails.User
   /*private UserEntity buildUserForAuthentication(UserEntity, List<GrantedAuthority> authorities) {
      return new UserEntity(user.getUsername(), user.getPasswordSalt(),
              true, true, authorities);
   }*/

   private List<GrantedAuthority> buildUserAuthority(Set<RoleEnum> userRoles) {

      Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

      for(RoleEnum userRole : userRoles) {
         setAuths.add(new SimpleGrantedAuthority(userRole.name()));
      }

      List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

      return result;
   }
}
