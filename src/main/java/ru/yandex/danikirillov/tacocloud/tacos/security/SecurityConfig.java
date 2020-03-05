package ru.yandex.danikirillov.tacocloud.tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        один из способов задания ограничений:
        http.authorizeRequests().antMatchers("/design", "/orders").hasRole("ROLE_USER")//запрещаем доступ к этим страницам незарегистрированным пользователям
                .antMatchers("/", "/**").permitAll();

        ниже - более настраиваемый способ на SpEL(Spring expression language)
         */

        http.authorizeRequests()
                .antMatchers("/design", "/orders", "/orderComplete")
                .access("hasRole('ROLE_USER') && " +
                        "T(java.util.Calendar).getInstance().get(" +
                        "T(java.util.Calendar).DAY_OF_WEEK) != " +
                        "T(java.util.Calendar).MONDAY")
                // по понедельникам заказ тако недоступен:)
                .antMatchers("/", "/**", "/h2-console/**").access("permitAll")
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/");
        http.headers().frameOptions().sameOrigin();
    }
}
