package elte.homework.implementation;

import elte.homework.data.User;
import elte.homework.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userDao;

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        String logLabel = "loadUserByUsername - ";
        logger.info(logLabel + "Authenticating - UserName: " + userName);
        User user = userDao.findById(userName).get();
        logger.info(logLabel + "User data - PasswordLength: " + user.getPassword().length()
            + " UserType: " + user.getType());
        return new UserPrincipal(user);
    }
}
