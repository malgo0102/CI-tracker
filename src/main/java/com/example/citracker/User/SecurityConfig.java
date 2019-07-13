package com.example.citracker.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomAuthentication customAuthentication;

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().antMatchers("/manage/**").authenticated();


    http.authorizeRequests().and().formLogin().loginProcessingUrl("/login").loginPage("/login")
        .defaultSuccessUrl("/").failureUrl("/login?error=true")
        .usernameParameter("username").passwordParameter("password")
        .and()
        .logout().logoutSuccessUrl("/login?logout");

    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/accessdenied");
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(customAuthentication);
  }
}