package com.ljmtecnologica.mercadolivro.config

import com.ljmtecnologica.mercadolivro.enums.RolesEnum
import com.ljmtecnologica.mercadolivro.security.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val userDetailsSecurityService: UserDetailsSecurityService,
    private val jwtUtil: JwtUtil,
    private val custonAuthenticationEntryPoint: CustonAuthenticationEntryPoint
): WebSecurityConfigurerAdapter() {

    private val PUBLIC_BOOKS_MATCHER = arrayOf(
        "/books/**"
    )

    private val ADMIN_BOOKS_MATCHER = arrayOf(
        "/books/**"
    )

    private val ADMIN_CUSTOMER_MATCHAR = arrayOf(
        "/customers"
    )

    private val PUBLIC_CUSTOMER_MATCHER = arrayOf(
        "/customers"
    )

    private val ADMIN_MATCHER = arrayOf(
        "/admins/**"
    )

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, *PUBLIC_BOOKS_MATCHER).permitAll()
            .antMatchers(*ADMIN_BOOKS_MATCHER).hasAuthority(RolesEnum.ADMIN.description)
            .antMatchers(HttpMethod.GET, *ADMIN_CUSTOMER_MATCHAR).hasAuthority(RolesEnum.ADMIN.description)
            .antMatchers(HttpMethod.POST, *PUBLIC_CUSTOMER_MATCHER).permitAll()
            .antMatchers(*ADMIN_MATCHER).hasAuthority(RolesEnum.ADMIN.description)
            .anyRequest().authenticated()
        http.addFilter(AuthenticationFilter(authenticationManager(), this.jwtUtil))
        http.addFilter(AuthorizationFilter(authenticationManager(), this.jwtUtil, this.userDetailsSecurityService))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.exceptionHandling().authenticationEntryPoint(this.custonAuthenticationEntryPoint)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsSecurityService).passwordEncoder(this.bCryptPasswordEncoder())
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/V2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/**",
            "/swagger-ui.html",
            "/webjars/**"
        )
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun corsConfig(): CorsFilter{
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOriginPattern("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

}