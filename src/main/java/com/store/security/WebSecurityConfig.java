package com.store.security;

import com.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {"/v3/api-docs/**", "/swagger-ui/**"};

    @Autowired
    CustomerService customerService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //Cors filter to accept incoming requests
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();
        corsConfig.setAllowedMethods(Collections.singletonList("*"));
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Tắt thuật tấn công giả mạo
        http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable();
        // Quyền truy cập OpenAPIDefinition
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
        // Quyền yêu cầu truy cập
        http.authorizeRequests().antMatchers("/", "/auth/login", "/auth/register", "/auth/forgot-password").permitAll();
        http.authorizeRequests().antMatchers("/order/**", "/auth/change-password").authenticated();
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN").anyRequest().permitAll();
//        http.authorizeRequests()
//                .antMatchers("/**", "/admin/**", "/auth/**", "/api/**").permitAll()
//                .anyRequest().authenticated();
        // Đăng nhập
        http.formLogin().loginPage("/auth/login/form").loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/auth/login/success", false).failureUrl("/auth/login/error");
        http.rememberMe().tokenValiditySeconds(86400); // remember me
        // Điều khiển lỗi truy cập không đúng quyền
        http.exceptionHandling().accessDeniedPage("/auth/unauthoried");
        // Đăng xuất
        http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logout/success");
        // OAuth2 - Đăng nhâp từ mang xã hôi
//        http.oauth2Login().loginPage("/auth/login/form").defaultSuccessUrl("/oauth2/login/success", true)
//                .failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
        return http.build();
    }
}