package sx.CRUDApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sx.CRUDApp.service.EmployeeService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AuthorizeRequestsSecurityConfiguration {

    @Bean
    public SecurityFilterChain authorizeRequestsFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/acc/reg", "/acc/login").permitAll()
                        .requestMatchers("/work/washer").hasRole("WASHER")
                        .requestMatchers("/work/courier").hasRole("COURIER")
                        .requestMatchers("/work/manager").hasRole("MANAGER")
                        .requestMatchers("/work/admin").hasRole("ADMIN")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/acc/login")
                        .defaultSuccessUrl("/acc")
                        .failureUrl("/acc/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/acc/logout")
                        .logoutSuccessUrl("/acc/login")
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoded(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, EmployeeService employeeService) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(employeeService).passwordEncoder(getPasswordEncoded());
        return auth.build();
    }

    //todo: что то с процессом аутентификации, при входе в аккаунт ничего не происходит, + не отправляется запрос на post метод в контроллере

}