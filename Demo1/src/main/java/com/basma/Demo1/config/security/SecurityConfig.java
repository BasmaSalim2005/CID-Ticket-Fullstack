package com.basma.Demo1.config.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthFilter authFilter;
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedOriginPattern("http://localhost:4200/socket/**");
        configuration.addAllowedOriginPattern("/**");

        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
/*
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/socket/", config);
        return new CorsFilter(source);
    }

 */


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers( "/api/demo1/users/authenticate", "/api/demo1/applications/apps","/swagger-ui/**","/swagger-ui/**","/socket/info").permitAll()

                .requestMatchers(
                        // USERS
                        "/api/demo1/users/add","/api/demo1/users/delete/","/api/demo1/users/getall","/api/demo1/users/getbyemail/","/api/demo1/users/getbyfname/",
                        "/api/demo1/users/getbyid/","/api/demo1/users/getdev","/api/demo1/users/update1/","/api/demo1/users/update1/login",
                        // APPLICATIONS
                        "/api/demo1/applications/add","/api/demo1/applications/activate/","/api/demo1/applications/active/byuser/","/api/demo1/applications/all",
                        "/api/demo1/applications/app/by-user","/api/demo1/applications/apps","/api/demo1/applications/countactive","/api/demo1/applications/countdisactive",
                        "/api/demo1/applications/countmaintenance","/api/demo1/applications/counttotal","/api/demo1/applications/counttotalbydev/","/api/demo1/applications/delete/",
                        "/api/demo1/applications/disactivate/","/api/demo1/applications/disactive/","/api/demo1/applications/disactive/byuser","/api/demo1/applications/getbyname/",
                        "/api/demo1/applications/inmaintenance/","/api/demo1/applications/maintenance/","/api/demo1/applications/maintenance/byuser","/api/demo1/applications/update/",
                        "/api/demo1/applications/user/add",
                        // TICKETS
                        "/api/demo1/tickets/add","/api/demo1/tickets/approve/","/api/demo1/tickets/assigned/count/all","/api/demo1/tickets/assignedto/",
                        "/api/demo1/tickets/cancel/","/api/demo1/tickets/close/","/api/demo1/tickets/count/approve","/api/demo1/tickets/count/assigne",
                        "/api/demo1/tickets/count/cancel","/api/demo1/tickets/count/close","/api/demo1/tickets/count/inprogress","/api/demo1/tickets/count/solve",
                        "/api/demo1/tickets/delete/","/api/demo1/tickets/getall","/api/demo1/tickets/getbyemail/","/api/demo1/tickets/getclosed/",
                        "/api/demo1/tickets/inprogress/","/api/demo1/tickets/notapprove/","/api/demo1/tickets/solve/","/api/demo1/tickets/status/detail/",
                        "/api/demo1/tickets/update/",
                        // FEATURES
                        "/api/demo1/features/add","/api/demo1/features/delete/","/api/demo1/features/getall","/api/demo1/features/getbyapp/",
                        "/api/demo1/features/getbyid/","/api/demo1/features/update/",
                        // FEEDBACK
                        "/api/demo1/feedback/add","/api/demo1/feedback/all","/api/demo1/feedback/byapp/",
                        "/api/demo1/feedback/delete/","/api/demo1/feedback/update/","/api/demo1/feedback/"
                ).hasRole("ADMIN")
                .requestMatchers(
                        // Features
                        "/api/demo1/features/delete/","/api/demo1/features/update/",
                        "/api/demo1/features/getall","/api/demo1/features/getbyapp/","/api/demo1/features/getbyid/","/api/demo1/features/add",
                        // Feedback
                        "/api/demo1/feedback/add","/api/demo1/feedback/update/",
                        // Applications
                        "/api/demo1/applications/add","/api/demo1/applications/activate/","/api/demo1/applications/active/byuser/",
                        "/api/demo1/applications/getbyname/","/api/demo1/applications/apps","/api/demo1/applications/countactive",
                        "/api/demo1/applications/countdisactive","/api/demo1/applications/countmaintenance","/api/demo1/applications/disactivate/",
                        "/api/demo1/applications/disactive/byuser","/api/demo1/applications/maintenance/byuser","/api/demo1/applications/maintenance/",
                        "/api/demo1/applications/update/","/api/demo1/applications/app/by-user","/api/demo1/applications/counttotalbydev/",
                        "/api/demo1/applications/byuser/","/api/demo1/applications/app/byuser", "/api/demo1/applications/apps","/api/demo1/applications/apps",
                        // Tickets
                        "/api/demo1/tickets/update/","/api/demo1/tickets/getbyid/","/api/demo1/tickets/getclosed/",
                        "/api/demo1/tickets/getbyemail/","/api/demo1/tickets/add","/api/demo1/tickets/approve/","/api/demo1/tickets/assigned/count/all",
                        "/api/demo1/tickets/assignedto/","/api/demo1/tickets/approve/","/api/demo1/tickets/cancel/","/api/demo1/tickets/close/",
                        "/api/demo1/tickets/solve/","/api/demo1/tickets/count/inprogress","/api/demo1/tickets/count/assigne","/api/demo1/tickets/count/approve",
                        "/api/demo1/tickets/count/cancel","/api/demo1/tickets/count/close","/api/demo1/tickets/count/solve","/api/demo1/tickets/getbyemail/",
                        "/api/demo1/tickets/inprogress/","/api/demo1/tickets/notapprove/"
                        // User endpoints for developer (only login and authentication)
                        ,"/api/demo1/users/login"
                ).hasRole("DEVELOPER")
                .requestMatchers(
                        // Applications
                        "/api/demo1/applications/apps","/api/demo1/applications/all",
                        // Feedback
                        "/api/demo1/feedback/add","/api/demo1/feedback/update/",
                        // Tickets
                        "/api/demo1/applications/all",
                        "/api/demo1/tickets/update/","/api/demo1/tickets/getbyid/","/api/demo1/tickets/getclosed/",
                        "/api/demo1/tickets/getbyemail/","/api/demo1/tickets/add","/api/demo1/tickets/approve/","/api/demo1/tickets/assigned/count/all",
                        "/api/demo1/tickets/assignedto/","/api/demo1/tickets/approve/","/api/demo1/tickets/cancel/","/api/demo1/tickets/close/",
                        "/api/demo1/tickets/solve/","/api/demo1/tickets/count/inprogress","/api/demo1/tickets/count/assigne","/api/demo1/tickets/count/approve",
                        "/api/demo1/tickets/count/cancel","/api/demo1/tickets/count/close","/api/demo1/tickets/count/solve","/api/demo1/tickets/getbyemail/",
                        "/api/demo1/tickets/inprogress/","/api/demo1/tickets/notapprove/"
                        // User endpoints for technician (only login and authentication)
                        ,"/api/demo1/users/login"
                ).hasRole("TECHNICIAN")
                .requestMatchers(
                        // APPLICATIONS (only get all)
                        "/api/demo1/applications/all",
                        "/api/demo1/applications/apps",
                        // TICKETS (user actions and user-specific counts only)
                        "/api/demo1/tickets/add", "/api/demo1/tickets/update/","/api/demo1/tickets/close/",
                        "/api/demo1/tickets/cancel/","/api/demo1/tickets/approve/",
                        "/api/demo1/tickets/notapprove/","/api/demo1/tickets/getbyid/","/api/demo1/tickets/getclosed/",
                        "/api/demo1/tickets/status/detail/","/api/demo1/tickets/","/api/demo1/tickets/getbyemail/",
                        // Only user-specific ticket counts (byuser/byemail)
                        "/api/demo1/tickets/count/approve/byuser","/api/demo1/tickets/count/assigne/byuser",
                        "/api/demo1/tickets/count/cancel/byuser","/api/demo1/tickets/count/close/byuser",
                        "/api/demo1/tickets/count/inprogress/byuser","/api/demo1/tickets/count/solve/byuser",
                        // FEEDBACK (add and update only)
                        "/api/demo1/feedback/add",
                        "/api/demo1/feedback/update/"
                ).hasRole("USER")
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors() // Enable CORS
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
