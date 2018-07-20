package itus._2014.king.springvuejsblog.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends  ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/register", "/login").permitAll()
                .antMatchers("/private/**").authenticated()
                .antMatchers("/post").authenticated()
                .antMatchers("/post/Comment").authenticated()
                .antMatchers(HttpMethod.DELETE, "/post/**").hasAuthority("ROLE_AMDIN");
    }

}
