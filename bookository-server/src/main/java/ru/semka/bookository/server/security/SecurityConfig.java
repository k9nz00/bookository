package ru.semka.bookository.server.security;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig //extends WebSecurityConfigurerAdapter
{

//    private final CustomAuthenticationProvider authenticationProvider;
//
//    @Autowired
//    public SecurityConfig(CustomAuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers(PermitAllUrls.getPermitUrls()).permitAll()
//                .antMatchers("/**").authenticated().and().httpBasic().and().logout()
//                .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)));
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedHeader("*");
//        config.addAllowedOriginPattern("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
