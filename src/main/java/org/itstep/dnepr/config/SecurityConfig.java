package org.itstep.dnepr.config;


import org.itstep.dnepr.security.AuthProvider;
import org.itstep.dnepr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("org.itstep.dnepr.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // private AuthProvider authProvider;

    // @Autowired
    //public void setAuthProvider(AuthProvider authProvider) {
    //     this.authProvider = authProvider;
    // }


    private UserDetailsService userService;

    @Autowired
    public void setUserService(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/registration").permitAll()
                //.antMatchers("/", "/show").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/", "/show").hasRole("USER")
                .anyRequest().authenticated()
                .and()//.csrf().disable()//Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/hamster/new")
                .loginProcessingUrl("/login/process")
                .failureForwardUrl("/login?error=true")
                .usernameParameter("email")
                .and().logout().permitAll();
    }

/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }*/


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
