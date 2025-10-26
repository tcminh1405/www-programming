package iuh.fit.se.trancongminh_week07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SercurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("123")).roles("ADMIN").build();
        UserDetails customer = User.builder().username("customer").password(passwordEncoder().encode("111")).roles("CUSTOMER").build();
        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
//1. Quy tắc mở
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/test").permitAll()
//2. Phân quyền
                .requestMatchers("/product", "/product/detail/**").hasAnyRole("CUSTOMER", "ADMIN")
//3. Độc quyền
                .requestMatchers("/product/add", "/product/edit/", "/product/update/").hasRole("ADMIN")
//4. phần request còn lại phải chứng thực
                .anyRequest().authenticated()).formLogin(form -> form
// .loginPage("/login")
                .defaultSuccessUrl("/product").permitAll()).logout(logout -> logout.logoutUrl("/logout") // URL người dùng truy cập để đăng xuất
                //(mặc định là POST)
                .logoutSuccessUrl("/login?logout=true") // Chuyển hướng sau khi
                //  đăng xuất thành công
                .invalidateHttpSession(true) // Hủy session
                .deleteCookies("JSESSIONID") // Xóa cookie session
                .permitAll() // Cho phép tất cả mọi người truy cập vào URL logout
        );
        return http.build();
    }
}