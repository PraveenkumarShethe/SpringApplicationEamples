package com.example.StatelessAuthenticationApplication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Praveenkumar on 5/12/2021.
 */
public class StatelessAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(StatelessAuthenticationSecurityConfig.class);

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public StatelessAuthenticationSecurityConfig() {
        super(true);
    }

    @Bean
    public StatelessLoginFilter statelessTokenBasedLoginFilter() throws AuthenticationException {
        logger.info(" statelessTokenBasedLoginFilter ");
        StatelessLoginFilter statelessTokenBasedLoginFilter;
        try {
            statelessTokenBasedLoginFilter = new StatelessLoginFilter(
                    "/login/authenticate", //TODO: Take this into YAML
                    tokenAuthenticationService,
                    (MyUserDetailService) userDetailsService(),
                    authenticationManager());
        } catch (Exception e) {
            final String msg = "Exception occurred during creation of AuthenticationManager: " + e.toString();
            throw new StatelessUserAuthenticationException(msg, e.getCause());
        }
        return statelessTokenBasedLoginFilter;
    }

    @Bean
    public StatelessAuthenticationFilter statelessTokenBasedAuthenticationFilter() {
        final StatelessAuthenticationFilter statelessTokenBasedAuthenticationFilter =
                new StatelessAuthenticationFilter(tokenAuthenticationService);
        return statelessTokenBasedAuthenticationFilter;
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousFilter() {
        return new AnonymousAuthenticationFilter("anon_auth_unique_key");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info(" Configure method ");
        http
                .addFilterBefore(anonymousFilter(), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login/authenticate").permitAll()
                .antMatchers("/books/**").authenticated()
                .antMatchers(HttpMethod.GET, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .anyRequest().hasRole("USER").and()
                .addFilterBefore(this.statelessTokenBasedLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.statelessTokenBasedAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * This bean method creates an instance of the BCryptPasswordEncoder which is used to encode passwords.
     *
     * @return An instance of BCryptPasswordEncoder
     * @see org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailService();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

}
