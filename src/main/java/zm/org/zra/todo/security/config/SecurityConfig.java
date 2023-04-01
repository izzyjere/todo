package zm.org.zra.todo.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import zm.org.zra.todo.security.AuthorizationFailureHandler;
import zm.org.zra.todo.security.TodoUserDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private TodoUserDetailsService userDetailsService;
    @Autowired
    private AuthorizationFailureHandler authorizationFailureHandler;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and().csrf().disable()
                .authorizeHttpRequests().requestMatchers("/index","/home","/todos").authenticated().and()
                .authorizeHttpRequests().anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/")
                .failureHandler(authorizationFailureHandler)
                .and()
                .logout().logoutSuccessUrl("/")
                .permitAll()
                .and();
        return  httpSecurity.build();
    }
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws  Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
