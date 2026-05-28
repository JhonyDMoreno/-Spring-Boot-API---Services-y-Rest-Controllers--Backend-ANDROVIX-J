package com.androvixj.api.config;

import com.androvixj.api.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final DaoAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter, DaoAuthenticationProvider authenticationProvider) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // ─── Auth y Swagger - públicos ──────────────────────────────
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()

                // ─── Productos - lectura pública ────────────────────────────
                .requestMatchers(HttpMethod.GET, "/productos/**").permitAll()

                // ─── Productos - escritura solo ADMIN ───────────────────────
                .requestMatchers(HttpMethod.POST, "/productos/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/productos/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/productos/**").hasAuthority("ROLE_ADMIN")

                // ─── Usuarios - solo ADMIN ──────────────────────────────────
                .requestMatchers("/usuarios/**").hasAuthority("ROLE_ADMIN")

                // ─── Pedidos - reglas específicas antes que las generales ───
                .requestMatchers(HttpMethod.GET, "/pedidos/usuario/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENTE")
                .requestMatchers(HttpMethod.GET, "/pedidos/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENTE")
                .requestMatchers(HttpMethod.GET, "/pedidos").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/pedidos").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENTE")
                .requestMatchers(HttpMethod.PUT, "/pedidos/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/pedidos/**").hasAuthority("ROLE_ADMIN")

                // ─── Producto-pedido - ADMIN y CLIENTE ──────────────────────
                .requestMatchers("/producto-pedido/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENTE")

                // ─── Cualquier otro requiere autenticación ──────────────────
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
