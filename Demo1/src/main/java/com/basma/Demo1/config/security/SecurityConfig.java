package org.dxc.khouna.configuration.security;



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

                .requestMatchers("/**", "/swagger-ui/**","/swagger-ui/**","/socket/info").permitAll()

                .requestMatchers(
                        "/identity-service/user/all","/identity-service/user/delete",
                        "/identity-service/user/changeTeam","/identity-service/user/changeWorkStatus"
                        ,"/identity-service/user/report/**",

                        "/identity-service/team/add", "/identity-service/team/delete/**", "/identity-service/team/assignTechLead", "/identity-service/team/changeTeamName",
                        "/identity-service/team/report","identity-service/team/teamMates/**",

                        "/identity-service/department/add","/identity-service/user/allUsers",
                        "/identity-service/user/searchUsers","/identity-service/user/modifyUser",
                        "/identity-service/user/addNewUser","/identity-service/user/add/**",
                        "/identity-service/user/add","/identity-service/team/delete/**",
                        "/identity-service/team/changeDepartmentName"
                        ).hasRole("MANAGER")
                .requestMatchers(
                        "/identity-service/user/upgradeUser",
                        "/identity-service/user/changePassword1",
                        "/identity-service/user/all/holliday/stats/**",
                        "identity-service/user/hollidayBalance/**",

                        "/identity-service/team/users/**","/identity-service/user/details/**","/identity-service/user/changeProfilImage/**",
                        "/identity-service/user/deleteProfilImage/**",
                        "/identity-service/team/all/**",
                        "/identity-service/team/techLead/**",
                        "/identity-service/team/users/**",
                        "identity-service/team//teamsByTeamAcronymes/**",
                        "/identity-service/department/managers",
                        "/identity-service/department/all/**","/identity-service/user/allUsers/pageUsers","/identity-service/user/allUsers/pageUsers/**","/identity-service/department/changeDepartmentName"
                ).hasAnyRole("MANAGER","TECH_LEAD","SCRUM_MASTER","COLLABORATOR")
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
