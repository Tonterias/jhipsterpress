# Problem 29: How to allow access to content for visitors in some pages like the home


Let's say that you have a homepage like JHipsterPress that shows the content from the Post entities in the home page or in the detail component of the Post Entity. How can you allow user to see them even without registering at your site.

First, you will have to open your SecurityConfiguration and make some changes:

	
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
            .addFilterBefore(corsFilter, CsrfFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(problemSupport)
            .accessDeniedHandler(problemSupport)
        .and()
            .rememberMe()
            .rememberMeServices(rememberMeServices)
            .rememberMeParameter("remember-me")
            .key(jHipsterProperties.getSecurity().getRememberMe().getKey())
        .and()
            .formLogin()
            .loginProcessingUrl("/api/authentication")
            .successHandler(ajaxAuthenticationSuccessHandler())
            .failureHandler(ajaxAuthenticationFailureHandler())
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(ajaxLogoutSuccessHandler())
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers(HttpMethod.GET, "/api/frontpageconfigs/*/posts").permitAll()
            .antMatchers(HttpMethod.GET, "/api/posts/*").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN);

    }

In this case, authorize request to the frontpageconfig to show the home page and the post/* that shows any post:

            .antMatchers(HttpMethod.GET, "/api/frontpageconfigs/*/posts").permitAll()
            .antMatchers(HttpMethod.GET, "/api/posts/*").permitAll()

Then, in your post.route.ts make sure to comment out the canActivate and delete the user like this:

    {
        path: 'post',
        component: PostComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.post.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'post/:id/view',
        component: PostDetailComponent,
        resolve: {
            post: PostResolve
        },
        data: {
            authorities: [],
            pageTitle: 'jhipsterpressApp.post.home.title'
        },
	//        canActivate: [UserRouteAccessService]
    },
    
See the difference between the path: 'post' and the path: 'post/:id/view', in the authorities array and the canActivate. For more info, check angular documentation (https://angular.io/api/router/CanActivate). 