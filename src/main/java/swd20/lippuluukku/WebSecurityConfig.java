package swd20.lippuluukku;

import swd20.lippuluukku.web.UserDetailServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http // määritelmät eri käyttäjien oikeuksille nähdä ja käyttää sivuja.
		.authorizeRequests().antMatchers("css/**", "lippuluukku").permitAll()
          .and()
          .authorizeRequests().antMatchers("/", "lisaapatahtuma", "tallennatapahtuma", "muokkaatapahtumaa/*", "julkaisetapahtuma/*",
        		  "perutapahtuma/*", "lisaalippuja/*", "tallennalippuja").hasAuthority("TUOTTAJA")
          .and()
          .authorizeRequests().antMatchers("tapahtumalista").authenticated()
          .and()
          .authorizeRequests().antMatchers("/", "omatliput/*", "varaaliput").hasAuthority("ASIAKAS")
          .and()
          .authorizeRequests().antMatchers("/", "tapahtumalista", "lisaapatahtuma", "tallennatapahtuma", "muokkaatapahtumaa/*", "julkaisetapahtuma/*",
        		  "perutapahtuma/*", "lisaalippuja/*", "tallennalippuja", "poistatapahtuma/*").hasAuthority("ADMIN")
          .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/tapahtumalista")
          .permitAll()
          .and()
      .logout()
          .permitAll();
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
