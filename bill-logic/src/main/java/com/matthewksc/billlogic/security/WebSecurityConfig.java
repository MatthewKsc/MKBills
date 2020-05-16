package com.matthewksc.billlogic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    //todo change antmachters
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //disable for propose of creating api
        http.headers().disable();
        http.authorizeRequests()
                .antMatchers("/mkbills").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user/bills").authenticated()
                .antMatchers("/user/bills/**").authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .passwordParameter("password") //specify parameters from html name=""
                        .usernameParameter("username")
                        .defaultSuccessUrl("/mkbills")
                .and()
                    .rememberMe() //by defualt setting this to 2 weaks
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key("secretTokenHashKey") //todo key read from application.yml
                    .rememberMeParameter("remember-me")
                .and()
                    .logout()
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"); //delete sessionID after logout cookies without this

        //.antMatchers(HttpMethod.OPTIONS).permitAll() //for angular
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
