package by.lupach.patient_accounting_system.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public String accessKey() {
//        return "123";
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/login").permitAll()  // доступ для всех к этим страницам
                        .anyRequest().authenticated()  // все остальные запросы требуют аутентификации
                )
                .formLogin(form -> form
                        .loginPage("/login")  // путь к кастомной странице входа
                        .loginProcessingUrl("/login_processing")  // URL, по которому отправляются данные формы
                        .defaultSuccessUrl("/", true)  // куда перенаправлять при успешном входе
                        .failureUrl("/login?error=true")  // куда перенаправлять при неудачном входе
                        .usernameParameter("username")  // параметр для имени пользователя
                        .passwordParameter("password")  // параметр для пароля
                        .permitAll()  // доступ к странице логина для всех
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                );

        return http.build();
    }

}
