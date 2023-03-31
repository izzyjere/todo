package zm.org.zra.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zm.org.zra.todo.services.interfaces.IUserService;

import java.util.Collections;

@Service
public class TodoUserDetailsService implements UserDetailsService {
    @Autowired
    IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.getByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User was not found.");
        }
        return new User(user.getUsername(),user.getPassword(), Collections.emptyList());
    }
}
