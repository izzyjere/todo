package zm.org.zra.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zm.org.zra.todo.security.AuthorizationFailureHandler;

@Configuration
public class WebConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() throws Exception {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthorizationFailureHandler authenticationFailureHandler() throws Exception {
        return  new AuthorizationFailureHandler();
    }
}
