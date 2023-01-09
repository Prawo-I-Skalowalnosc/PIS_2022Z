package cinex;

import cinex.security.TokenFilter;
import cinex.security.UserRoles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "cinex.repository"
        }
)
@EnableWebSecurity
public class Config {
    @Bean
    public TokenFilter getTokenFilter() {
        return new TokenFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().cors().and()
            .authorizeHttpRequests(authorize -> {
                authorize.antMatchers(
                        "/account/**"
                ).permitAll();
                authorize.antMatchers(
                        HttpMethod.POST,
                        "/account/**"
                ).permitAll();
                authorize.antMatchers(
                        "/movies/**"
                ).permitAll();
                authorize.antMatchers(
                        "/people/**"
                ).permitAll();
                authorize.antMatchers(
                        "/movieRatings/**"
                        "/movieRatings/user"
                ).permitAll();
                authorize.antMatchers(
                        HttpMethod.PUT,
                        "/movieRatings/addRating"
                ).permitAll();

                authorize.antMatchers(
                        HttpMethod.POST,
                        "/movies/create"
                ).hasAuthority(UserRoles.MODERATOR.toString());
                authorize.antMatchers(
                        HttpMethod.POST,
                        "/people/create"
                ).hasAuthority(UserRoles.MODERATOR.toString());
                authorize.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults())
            .addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
