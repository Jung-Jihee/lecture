package net.joins.site.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import net.joins.webapp.config.WebSecurityConfig;

@EnableWebSecurity
public class SiteSecurityConfig extends WebSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  for h2 console
        http
            .csrf().disable()
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
            .antMatchers("/h2/**").permitAll();
        
        http.authorizeRequests().anyRequest().permitAll();
    }
}
