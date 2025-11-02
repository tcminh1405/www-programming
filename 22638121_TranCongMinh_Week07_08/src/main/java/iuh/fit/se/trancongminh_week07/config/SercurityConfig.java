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
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN")
                .build();
        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("111"))
                .roles("CUSTOMER")
                .build();
        return new InMemoryUserDetailsManager(admin, customer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        // Cho phép không cần đăng nhập
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/test").permitAll()

                        // Trang sản phẩm: CUSTOMER và ADMIN đều xem được
                        .requestMatchers("/product", "/product/detail/**", "/cart/**").hasAnyRole("CUSTOMER", "ADMIN")

                        // Chỉ ADMIN mới được thêm/sửa/xóa sản phẩm
                        .requestMatchers("/product/add", "/product/edit/**", "/product/update/**", "/product/delete/**").hasRole("ADMIN")

                        // Chỉ ADMIN được xem danh sách khách hàng & đơn hàng
                        .requestMatchers("/customer/**", "/order/list", "/order/delete/**").hasRole("ADMIN")

                        // CUSTOMER vẫn có thể xem và tạo đơn hàng của riêng họ
                        .requestMatchers("/order/add", "/cart/**", "/order/checkout").hasRole("CUSTOMER")

                        // CUSTOMER kh xóa sửa comment
                        .requestMatchers("/comment/edit/**", "/comment/delete/**").hasRole("ADMIN")

                        // Các request khác cần đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/login") // xử lý POST từ form
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        return http.build();
    }
}