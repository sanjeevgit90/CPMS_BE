package aurionpro.erp.ipms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import aurionpro.erp.ipms.jkdframework.authentication.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
            //.anyRequest().permitAll()
            .antMatchers("/authenticate","/forgotpassword/*","/resetpassword","/unlockUser/**","/ipms/user","/ipms/department/selectionlist","/ipms/organization/selectionlist","/ipms/userprofile/selectionlist","/email/saveEmail","/ipms/geograpghy/**", "/swagger-ui.html","/webjars/**","/v2/**","/swagger-resources/**","/ipms/officelocation/selectionlist", "/ipms/ticket/getTicketByFilterWithoutBodyRequest",
            		"/cpms/auropayclient","/cpms/client","/cpms/client/login").permitAll()
            //.antMatchers("/ipms/test/**").hasAuthority("USER")
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    
}