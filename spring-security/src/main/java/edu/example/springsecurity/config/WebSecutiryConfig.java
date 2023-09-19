package edu.example.springsecurity.config;

import edu.example.springsecurity.config.SecurityDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityDatabaseService secDbService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(secDbService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers("/managers").hasRole("MANAGERS")
                .antMatchers("/users").hasAnyRole("MANAGERS", "USERS")
                .anyRequest().authenticated().and().httpBasic();
    }

    /*
    Hardcoded users replaced with DB users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("teste")
                .password("{noop}plaintext")
                .roles("USERS")
                .and()
                .withUser("admin")
                .password("{noop}plaintext")
                .roles("MANAGERS");
    } */

}
