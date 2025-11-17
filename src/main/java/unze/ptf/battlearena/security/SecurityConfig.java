package unze.ptf.battlearena.security;

//svakome dozvoli čitanje liste, ali dodavanje/uređivanje/brisanje samo prijavljenima.
//Koristimo in-memory korisnike (dovoljno za vježbe) i default login stranicu.


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



// Cilj: svakome dozvoli čitanje liste, ali dodavanje/uređivanje/brisanje samo prijavljenima.
// Koristimo in-memory korisnike (za vježbe - inače bismo imali entitet korisnik s rolama i povezali s bazom) i default login stranicu.
// Login ruta se dobije tomatski na /login.
// Sada svi mogu vidjeti listu na /characters, ali za New / Save / Edit / Delete mora se prijaviti.


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/characters", "/api/**", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/characters/new", "/characters/save", "/characters/edit/**", "/characters/delete/**").authenticated()
                        .anyRequest().authenticated()
                )

                .formLogin(Customizer.withDefaults()) // default login page
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/characters")   // gdje će otići nakon logouta
                        .permitAll()
                )
                .csrf(Customizer.withDefaults()); // CSRF ostaje uključen (već imamo hidden token u formama)

        return http.build();
    }


    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("student").password(encoder.encode("student123")).roles("USER").build(),
                User.withUsername("admin").password(encoder.encode("admin123")).roles("ADMIN").build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
