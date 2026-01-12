package cdu.finalproject.petstore.config;

import cdu.finalproject.petstore.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // private PasswordEncoder encoder = new PasswordEncoder() {
    // @Override
    // public String encode(CharSequence rawPassword) {
    //// return MD5Encoder.encode(rawPassword.toString().getBytes());
    // return rawPassword.toString();
    // }
    //
    // @Override
    // public boolean matches(CharSequence rawPassword, String encodedPassword) {
    //// return encodedPassword.equals(MD5Encoder.encode(rawPassword.toString().getBytes()));
    // return encodedPassword.equals(rawPassword.toString());
    // }
    // };
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
    // auth.inMemoryAuthentication()
    // .passwordEncoder(encoder)
    // .withUser("aaa").password("111").authorities("ADMIN")
    // .and()
    // .withUser("bbb").password("222").authorities("ADMIN");
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests().anyRequest().permitAll();
        // http.headers().frameOptions().sameOrigin();

        // http.authorizeRequests().anyRequest().permitAll()
        // .and().headers().frameOptions().sameOrigin();

        http
                .authorizeRequests().antMatchers("/login","/index","/pet/petList","/user/add","/doLogin","/loginError","/roleError","/user/addpost","/error").permitAll()
                .and().formLogin()
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/doLogin")
                    .defaultSuccessUrl("/index",true)
                    .failureUrl("/loginError")
                    .permitAll()
                    .and()
                .logout()
                .permitAll()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/roleError")
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webfonts/**","/fonts/**","/css/**", "/js/**","/img/**", "/bootstrap/**");
    }
}