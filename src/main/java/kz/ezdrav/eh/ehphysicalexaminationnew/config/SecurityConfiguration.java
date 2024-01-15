package kz.ezdrav.eh.ehphysicalexaminationnew.config;

import kz.ezdrav.eh.security.common.configuration.EnableEhSecurity;
import kz.ezdrav.eh.security.common.token.EhAuthenticationConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableEhSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        EhAuthenticationConverter converter = new EhAuthenticationConverter();

        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests().antMatchers("/swagger-ui.html/**", "/swagger-ui/**", "/v3/**", "/api/lmk/soap/**", "/actuator/**", "/api/lmk/internal/**", "/**").permitAll()
                .anyRequest().authenticated().and()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(converter);
    }
}
