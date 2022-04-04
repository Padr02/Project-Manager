package com.example.application.security;

import com.example.application.data.entity.UserEntity;
import com.example.application.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

    /**
     * Forward the found user in userRepository to our implementation of the UserDetails class that is connected to Spring Security
     *
     * @param userName - provided through the client side
     * @return a user from our database
     *
     * @throws UsernameNotFoundException
     */
   @Transactional
   @Override
    public UserDetails loadUserByUsername(String userName)
           throws UsernameNotFoundException {
       UserEntity user = userRepository.findByUsername(userName);
      return new UserDetailsImpl(user);
   }
}
