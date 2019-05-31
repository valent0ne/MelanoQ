package it.univaq.disim.bioinformatics.melanoq.service;

import it.univaq.disim.bioinformatics.melanoq.model.User;
import it.univaq.disim.bioinformatics.melanoq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public User findOneByUsername(String username) throws Exception {
        User user = userRepository.findOneByUsername(username);
        if (user == null){
            throw new Exception("User not found with username: " + username);
        }
        return user;
    }

    public User save(User u){
        //TODO
        String plainPassword = u.getPassword();
        u.setPassword(passwordEncoder.encode(plainPassword));
        u = userRepository.save(u);
        u.setPassword(plainPassword);
        return u;
    }

}
