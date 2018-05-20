package elte.homework.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class TudorWebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                    // UserController
                    .antMatchers(HttpMethod.GET,"/user/self").authenticated()
                    .antMatchers(HttpMethod.GET,"/user/get/*").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/user/password").authenticated()
                    .antMatchers(HttpMethod.POST,"/user/password/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/user/new").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/user/delete/*").hasRole("ADMIN")
                    // AdminController
                    .antMatchers(HttpMethod.GET,"/admin/list").authenticated()
                    .antMatchers(HttpMethod.GET,"/admin/get/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/admin/new").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/admin/modify").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/admin/delete/*").hasRole("ADMIN")
                    // ClientController
                    .antMatchers(HttpMethod.GET,"/client/list").authenticated()
                    .antMatchers(HttpMethod.GET,"/client/get/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/client/new").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/client/modify").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/client/delete/*").hasRole("ADMIN")
                    // TudorController
                    .antMatchers(HttpMethod.GET,"/tudor/list").authenticated()
                    .antMatchers(HttpMethod.GET,"/tudor/get/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/tudor/new").hasRole("ADMIN")
                    .antMatchers(HttpMethod.POST,"/tudor/modify").hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE,"/tudor/delete/*").hasRole("ADMIN")
                    // TudorController
                    .antMatchers(HttpMethod.GET,"/tudor/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/tudor/*").authenticated()
                    // QuestionController
                    .antMatchers(HttpMethod.GET,"/question/list").authenticated()
                    .antMatchers(HttpMethod.GET,"/question/get/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/question/new").hasAnyRole("ADMIN", "CLIENT")
                    .antMatchers(HttpMethod.DELETE,"/question/delete/*").hasAnyRole("ADMIN", "CLIENT")
                    // AnswerController
                    .antMatchers(HttpMethod.GET,"/answer/list").authenticated()
                    .antMatchers(HttpMethod.GET,"/answer/get/*").authenticated()
                    .antMatchers(HttpMethod.POST,"/answer/new").hasAnyRole("ADMIN", "TUDOR")
                    .antMatchers(HttpMethod.DELETE,"/answer/delete/*").hasAnyRole("ADMIN", "TUDOR")
                    .and()
                .csrf()
                    .disable()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/hello")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        /*
        //  SIMPLE USERSERVICE TO BE USED FOR TESTING ONLY
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);*/
        return new UserService();
    }
}
