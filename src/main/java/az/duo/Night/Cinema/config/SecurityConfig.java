package az.duo.Night.Cinema.config;

import az.duo.Night.Cinema.jwt.AuthEntryPoint;
import az.duo.Night.Cinema.jwt.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private static final String AUTH = "/api/auth/**";
    private static final String USER = "/api/user/**";
    private static final String ADMIN = "/api/admin/**";
    private static final String MOVIE = "/api/movie/**";
    private static final String MOVIE_SEARCH = "/api/movie/by_name/**";

    private static final String SWAGGER = "/swagger-ui/**";
    private static final String V3 = "/v3/api-docs/**";
    private static final String SWAGGER_UI = "/swagger-ui.html";
    private static final String SWAGGER_UI_INDEX = "/swagger-ui/index.html";
    private static final String WEBJARS = "/webjars/**";
    private static final String SWAGGER_RESOURCES = "/swagger-resources/**";

    private final AuthEntryPoint authEntryPoint;
    private final AuthenticationProvider authProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // "ROLE_" prefixini silir (user admin yazıram Role_admin ve ya Role_user yazmiram)
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(SWAGGER).permitAll()
                                        .requestMatchers(V3).permitAll()
                                        .requestMatchers(SWAGGER_UI).permitAll()
                                        .requestMatchers(SWAGGER_UI_INDEX).permitAll()
                                        .requestMatchers(WEBJARS).permitAll()
                                        .requestMatchers(SWAGGER_RESOURCES).permitAll()

                                        .requestMatchers(AUTH).permitAll()
                                        .requestMatchers(MOVIE).permitAll()
                                        .requestMatchers(MOVIE_SEARCH).permitAll()

                                        .requestMatchers(USER).hasRole("USER")
                                        .requestMatchers(ADMIN).hasRole("ADMIN")

                                        .anyRequest()
                                        .authenticated()
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authEntryPoint)
                )
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/swagger-ui/**", "/v3/api-docs/**"
        );
    }

}
