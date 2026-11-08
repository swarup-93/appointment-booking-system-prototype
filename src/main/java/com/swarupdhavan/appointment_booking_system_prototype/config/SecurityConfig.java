package com.swarupdhavan.appointment_booking_system_prototype.config;

import com.swarupdhavan.appointment_booking_system_prototype.entity.CustomUserDetails;
import com.swarupdhavan.appointment_booking_system_prototype.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(CustomUserDetailService userDetailService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider provider,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ) {
        http.csrf(csrf -> csrf.disable())
                .authenticationProvider(provider)

                //used when we used to log in for every request (for each login)
                //.formLogin(Customizer.withDefaults())
                //.httpBasic(Customizer.withDefaults())

                .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/auth/register","/auth/login").permitAll().anyRequest().authenticated()
                )

                .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            //we used below for the stateless architecture
                            //SessionCreationPolicy.IF_REQUIRED

                            //will be stateless for jwt token
                            SessionCreationPolicy.STATELESS
                    )
                )
                //for jwt extra
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        jwtAuthenticationConverter
                                )
                        )
                );

        return http.build();
    }

    //all below beans are for jwt
    @Bean
    public SecretKey jwtSecretKey(@Value("${jwt.secret}") String secret) {
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            return new SecretKeySpec(decodedKey,"HmacSHA256");
    }

    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }

    //for not to create JWT's own roles
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        //take authorities from below variable
        authoritiesConverter.setAuthoritiesClaimName("authorities");
        //to not put the custom jwt prefix
        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return authenticationConverter;
    }

    //to sign token
    @Bean
    public JwtEncoder jwtEncoder(SecretKey secretKey) {
        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    //to verify the token
    @Bean
    public JwtDecoder jwtDecoder(SecretKey secretKey, @Value("${jwt.issuer}") String issuer) {
        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        decoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(issuer));
        return decoder;
    }
}
