package com.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers("/**", "/members/**", "/item/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ) // 로그인 하지 않아도 접속 가능한 경로
//                .formLogin(form -> form
//                        .loginPage("/members/login")
//                        .defaultSuccessUrl("/")
//                        .usernameParameter("email")
//                        .permitAll()
//                ) //  로그인 상태가 아닐경우 리디렉트할 경로
                .logout(logout -> logout
                        .logoutSuccessUrl("/").permitAll()
                ) // 로그아웃 후 리디렉트 할 경로
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화, 테스트 단계에서는 보안 검사 비활성화

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
