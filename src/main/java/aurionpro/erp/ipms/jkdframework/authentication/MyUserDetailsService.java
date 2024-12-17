package aurionpro.erp.ipms.jkdframework.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import aurionpro.erp.ipms.jkdframework.userlogin.UserLogin;
import aurionpro.erp.ipms.jkdframework.userlogin.UserLoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserLoginRepository udRepo;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<UserLogin> user =udRepo.findById(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not Found User by name - " + username));

        return user.map(MyUserDetails::new).get();
    }

}