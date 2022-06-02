package practice.login.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

  override fun configure(web: WebSecurity) {
    web
      .debug(false)
      .ignoring()
      .antMatchers("/images/**", "/js/**", "/css/**")
  }

  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests()
      // Permit all
      .mvcMatchers(
        "/",
        /*
        "/sign_up",
        "/is_signed_in",
        "/sign_in",
        "/sign_out"
        */
        "/**"
      ).permitAll()
      .regexMatchers(
        HttpMethod.GET,
        "/user/[\\w|_]+"
      ).permitAll()
      .anyRequest().authenticated()
      .and()
      .csrf().disable()
  }
}