/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.config;

import com.vnpt.media.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author vnpt2
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyDBAuthenticationService myDBAuthenticationService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myDBAuthenticationService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    @Order(1)
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/login", "/logout","/api/importExcel" ,"/plugins/ckfinder/core/connector/java/connector.java/**").permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        http.authorizeRequests().antMatchers("/users/list/**").access("hasRole('ROLE_USER_LIST')");
        http.authorizeRequests().antMatchers("/users/detail/**").access("hasRole('ROLE_USER_DETAIL')");
        http.authorizeRequests().antMatchers("/users/update/**").access("hasRole('ROLE_USER_UPDATE')");
        http.authorizeRequests().antMatchers("/users/insert/**").access("hasRole('ROLE_USER_INSERT')");
        http.authorizeRequests().antMatchers("/users/delete/**").access("hasRole('ROLE_USER_DELETE')");
        http.authorizeRequests().antMatchers("/users/delete/**").access("hasRole('ROLE_USER_DELETE')");
        http.authorizeRequests().antMatchers("/users/active/**", "/users/lock/**").access("hasRole('ROLE_USER_ACTIVE')");

        http.authorizeRequests().antMatchers("/api/list/**").access("hasRole('ROLE_API_LIST')");
        http.authorizeRequests().antMatchers("/api/detail/**").access("hasRole('ROLE_API_DETAIL')");
        http.authorizeRequests().antMatchers("/api/update/**").access("hasRole('ROLE_API_UPDATE')");
        http.authorizeRequests().antMatchers("/api/insert/**").access("hasRole('ROLE_API_INSERT')");
        http.authorizeRequests().antMatchers("/api/delete/**").access("hasRole('ROLE_API_DELETE')");
        http.authorizeRequests().antMatchers("/api/active/**", "/api/lock/**").access("hasRole('ROLE_API_ACTIVE')");
        
        http.authorizeRequests().antMatchers("/groups/list/**").access("hasAnyRole('ROLE_ADMIN','ROLE_GROUP_LIST')");
        http.authorizeRequests().antMatchers("/groups/detail/**").access("hasAnyRole('ROLE_ADMIN','ROLE_GROUP_DETAIL')");
        http.authorizeRequests().antMatchers("/groups/update/**").access("hasAnyRole('ROLE_ADMIN','ROLE_GROUP_UPDATE')");
        http.authorizeRequests().antMatchers("/groups/insert/**").access("hasAnyRole('ROLE_ADMIN','ROLE_GROUP_INSERT')");
        http.authorizeRequests().antMatchers("/groups/delete/**").access("hasAnyRole('ROLE_ADMIN','ROLE_GROUP_DELETE')");
        http.authorizeRequests().antMatchers("/groups/listRoles/**", "/groups/updateListRole/**").access("hasAnyRole('ROLE_GROUP_UPDATE','ROLE_GROUP_INSERT')");
        http.authorizeRequests().antMatchers("/groups/listUsers/**", "/groups/updateListUser/**").access("hasAnyRole('ROLE_GROUP_UPDATE','ROLE_GROUP_INSERT')");

        http.authorizeRequests().antMatchers("/banner/list/**").access("hasRole('ROLE_BANNER_LIST')");
        http.authorizeRequests().antMatchers("/banner/detail/**").access("hasRole('ROLE_BANNER_DETAIL')");
        http.authorizeRequests().antMatchers("/banner/update/**").access("hasRole('ROLE_BANNER_UPDATE')");
        http.authorizeRequests().antMatchers("/banner/insert/**").access("hasRole('ROLE_BANNER_INSERT')");
        http.authorizeRequests().antMatchers("/banner/delete/**").access("hasRole('ROLE_BANNER_DELETE')");
        http.authorizeRequests().antMatchers("/banner/active/**").access("hasRole('ROLE_BANNER_ACTIVE')");
        http.authorizeRequests().antMatchers("/banner/lock/**").access("hasRole('ROLE_BANNER_ACTIVE')");

        http.authorizeRequests().antMatchers("/hotDeal/list/**").access("hasRole('ROLE_HOT_DEAL_LIST')");
        http.authorizeRequests().antMatchers("/hotDeal/detail/**").access("hasRole('ROLE_HOT_DEAL_DETAIL')");
        http.authorizeRequests().antMatchers("/hotDeal/update/**", "/hotDeal/edit/**").access("hasRole('ROLE_HOT_DEAL_UPDATE')");
        http.authorizeRequests().antMatchers("/hotDeal/insert/**", "/hotDeal/add/**").access("hasRole('ROLE_HOT_DEAL_INSERT')");
        http.authorizeRequests().antMatchers("/hotDeal/delete/**").access("hasRole('ROLE_HOT_DEAL_DELETE')");
        http.authorizeRequests().antMatchers("/hotDeal/active/**", "/hotDeal/lock/**").access("hasRole('ROLE_HOT_DEAL_ACTIVE')");

        http.authorizeRequests().antMatchers("/supperliers/list/**").access("hasRole('ROLE_SUPPERLIER_LIST')");
        http.authorizeRequests().antMatchers("/supperliers/detail/**").access("hasRole('ROLE_SUPPERLIER_DETAIL')");
        http.authorizeRequests().antMatchers("/supperliers/update/**", "/supperliers/edit/**").access("hasRole('ROLE_SUPPERLIER_UPDATE')");
        http.authorizeRequests().antMatchers("/supperliers/insert/**", "/supperliers/add/**").access("hasRole('ROLE_SUPPERLIER_INSERT')");
        http.authorizeRequests().antMatchers("/supperliers/delete/**").access("hasRole('ROLE_SUPPERLIER_DELETE')");
        http.authorizeRequests().antMatchers("/supperliers/active/**", "/supperliers/lock/**").access("hasRole('ROLE_SUPPERLIER_ACTIVE')");

        http.authorizeRequests().antMatchers("/promotion/list/**").access("hasRole('ROLE_PROMOTION_LIST')");
        http.authorizeRequests().antMatchers("/promotion/detail/**").access("hasRole('ROLE_PROMOTION_DETAIL')");
        http.authorizeRequests().antMatchers("/promotion/update/**", "/promotion/edit/**").access("hasRole('ROLE_PROMOTION_UPDATE')");
        http.authorizeRequests().antMatchers("/promotion/insert/**", "/promotion/add/**").access("hasRole('ROLE_PROMOTION_INSERT')");
        http.authorizeRequests().antMatchers("/promotion/delete/**").access("hasRole('ROLE_PROMOTION_DELETE')");
        http.authorizeRequests().antMatchers("/promotion/active/**", "/promotion/lock/**").access("hasRole('ROLE_PROMOTION_ACTIVE')");

        http.authorizeRequests().antMatchers("/production/list/**",
                "/production/getPackageHot/**", "/production/getPackageDataCombo/**", "/production/getPackageNhuCau/**").access("hasRole('ROLE_PRODUCTION_LIST')");
        http.authorizeRequests().antMatchers("/production/detail/**").access("hasRole('ROLE_PRODUCTION_DETAIL')");
        http.authorizeRequests().antMatchers("/production/update/**", "/production/edit/**",
                "/production/detailConfig/**", "/production/updatePosition/**", "/production/updateConfig/**").access("hasRole('ROLE_PRODUCTION_UPDATE')");
        http.authorizeRequests().antMatchers("/production/insert/**", "/production/add/**",
                "/production/detailConfig/**", "/production/updatePosition/**", "/production/updateConfig/**").access("hasRole('ROLE_PRODUCTION_INSERT')");
        http.authorizeRequests().antMatchers("/production/delete/**").access("hasRole('ROLE_PRODUCTION_DELETE')");
        http.authorizeRequests().antMatchers("/production/active/**", "/production/lock/**").access("hasRole('ROLE_PRODUCTION_ACTIVE')");

        http.authorizeRequests().antMatchers("/news/list/**").access("hasRole('ROLE_NEWS_LIST')");
        http.authorizeRequests().antMatchers("/news/detail/**").access("hasRole('ROLE_NEWS_DETAIL')");
        http.authorizeRequests().antMatchers("/news/update/**", "/news/edit/**").access("hasRole('ROLE_NEWS_UPDATE')");
        http.authorizeRequests().antMatchers("/news/insert/**", "/news/add/**").access("hasRole('ROLE_NEWS_INSERT')");
        http.authorizeRequests().antMatchers("/news/delete/**").access("hasRole('ROLE_NEWS_DELETE')");
        http.authorizeRequests().antMatchers("/news/active/**", "/news/lock/**").access("hasRole('ROLE_NEWS_ACTIVE')");

        http.authorizeRequests().antMatchers("/newsCategory/list/**").access("hasRole('ROLE_NEWS_CATEGORY_LIST')");
        http.authorizeRequests().antMatchers("/newsCategory/detail/**").access("hasRole('ROLE_NEWS_CATEGORY_DETAIL')");
        http.authorizeRequests().antMatchers("/newsCategory/update/**", "/newsCategory/edit/**").access("hasRole('ROLE_NEWS_CATEGORY_UPDATE')");
        http.authorizeRequests().antMatchers("/newsCategory/insert/**", "/newsCategory/add/**").access("hasRole('ROLE_NEWS_CATEGORY_INSERT')");
        http.authorizeRequests().antMatchers("/newsCategory/delete/**").access("hasRole('ROLE_NEWS_CATEGORY_DELETE')");
        http.authorizeRequests().antMatchers("/newsCategory/active/**", "/newsCategory/lock/**").access("hasRole('ROLE_NEWS_CATEGORY_ACTIVE')");

        http.authorizeRequests().antMatchers("/tblMenu/list/**").access("hasRole('ROLE_MENU_LIST')");
        http.authorizeRequests().antMatchers("/tblMenu/detail/**").access("hasRole('ROLE_MENU_DETAIL')");
        http.authorizeRequests().antMatchers("/tblMenu/update/**", "/tblMenu/edit/**").access("hasRole('ROLE_MENU_UPDATE')");
        http.authorizeRequests().antMatchers("/tblMenu/insert/**", "/tblMenu/add/**").access("hasRole('ROLE_MENU_INSERT')");
        http.authorizeRequests().antMatchers("/tblMenu/delete/**").access("hasRole('ROLE_MENU_DELETE')");
        http.authorizeRequests().antMatchers("/tblMenu/active/**", "/tblMenu/lock/**").access("hasRole('ROLE_MENU_ACTIVE')");


        http.authorizeRequests().antMatchers("/tblCategory/list/**").access("hasRole('ROLE_CATEGORY_LIST')");
        http.authorizeRequests().antMatchers("/tblCategory/detail/**").access("hasRole('ROLE_CATEGORY_DETAIL')");
        http.authorizeRequests().antMatchers("/tblCategory/update/**").access("hasRole('ROLE_CATEGORY_UPDATE')");
        http.authorizeRequests().antMatchers("/tblCategory/insert/**").access("hasRole('ROLE_CATEGORY_INSERT')");
        http.authorizeRequests().antMatchers("/tblCategory/delete/**").access("hasRole('ROLE_CATEGORY_DELETE')");
        http.authorizeRequests().antMatchers("/tblCategory/active/**", "/tblCategory/lock/**").access("hasRole('ROLE_CATEGORY_ACTIVE')");

        http.authorizeRequests().antMatchers("/keySpam/list/**").access("hasRole('ROLE_KEY_SPAM_LIST')");
        http.authorizeRequests().antMatchers("/keySpam/detail/**").access("hasRole('ROLE_KEY_SPAM_DETAIL')");
        http.authorizeRequests().antMatchers("/keySpam/update/**").access("hasRole('ROLE_KEY_SPAM_UPDATE')");
        http.authorizeRequests().antMatchers("/keySpam/insert/**").access("hasRole('ROLE_KEY_SPAM_INSERT')");
        http.authorizeRequests().antMatchers("/keySpam/delete/**").access("hasRole('ROLE_KEY_SPAM_DELETE')");
        http.authorizeRequests().antMatchers("/keySpam/active/**", "/keySpam/lock/**").access("hasRole('ROLE_KEY_SPAM_ACTIVE')");

        http.authorizeRequests().antMatchers("/customer/list/**").access("hasRole('ROLE_CUSTOMER_LIST')");
        http.authorizeRequests().antMatchers("/orders/list/**", "/orderDetail/list/**",
                "/stats/ordersNew/**", "/stats/orderDetailSimso/**", "/stats/orderDetailTruyenHinh/**").access("hasRole('ROLE_ORDER_LIST')");

        http.authorizeRequests().antMatchers("/tblEmployee/list/**").access("hasRole('ROLE_EMPLOYEE_LIST')");
        http.authorizeRequests().antMatchers("/tblEmployee/active/**", "/tblEmployee/lock/**").access("hasRole('ROLE_EMPLOYEE_ACTIVE')");
        http.authorizeRequests().antMatchers("/tblLandingPage/list/**").access("hasRole('ROLE_LANDING_PAGE_LIST')");
        http.authorizeRequests().antMatchers("/tblLandingPage/active/**", "/tblLandingPage/lock/**").access("hasRole('ROLE_LANDING_PAGE_ACTIVE')");

        http.authorizeRequests().antMatchers("/stats/reportOrderNotSim/**").access("hasRole('ROLE_STATS_ORDER_NOT_SIM')");
        http.authorizeRequests().antMatchers("/stats/reportOrderSim/**").access("hasRole('ROLE_STATS_ORDER_SIM')");
//        http.authorizeRequests().antMatchers("/stats/statsOrderBRCD/**", "/stats/statsOrderBRCDDetails/**").access("hasRole('ROLE_STATS_BRCD')");
        http.authorizeRequests().antMatchers("/stats/mtInfo/**").access("hasRole('ROLE_STATS_MT')");
        http.authorizeRequests().antMatchers("/actionMtLog/list/**").access("hasRole('ROLE_ACTION_MT_LOG')");

        http.authorizeRequests().antMatchers("/stats/statsMobileDetail/**", "/stats/downloadStatsMobileDetail/**").access("hasRole('ROLE_STATS_MOBILE')");
        http.authorizeRequests().antMatchers("/stats/statsSimDetail/**", "/stats/downloadStatsMobileDetail/**").access("hasRole('ROLE_STATS_SIM')");
        http.authorizeRequests().antMatchers("/stats/statsMyTvMobile/**", "/stats/downloadStatsMyTvMobile").access("hasRole('ROLE_STATS_MYTV')");
        http.authorizeRequests().antMatchers("/stats/statsBrcdDetail/**", "/stats/downloadStatsBrcdSummary/**").access("hasRole('ROLE_STATS_BRCD')");
        http.authorizeRequests().antMatchers("/stats/statsBrcdSummary/**", "/stats/downloadStatsBrcdSummary/**").access("hasRole('ROLE_STATS_BRCD')");

        http.authorizeRequests().antMatchers("/packageSim/list/**").access("hasRole('ROLE_PACKAGE_SIM_LIST')");
        http.authorizeRequests().antMatchers("/packageSim/edit/**", "/packageSim/update/**",
                "/packageSim/editNhuCau/**", "/packageSim/editDataCombo/**",
                "/production/getPackageHot/**", "/production/getPackageDataCombo/**", "/production/getPackageNhuCau/**",
                "/packageSim/editHOT/**").access("hasRole('ROLE_PACKAGE_SIM_UPDATE')");
        http.authorizeRequests().antMatchers("/packageSim/update/**", "/packageSim/addHOT/**",
                "/production/getPackageHot/**", "/production/getPackageDataCombo/**", "/production/getPackageNhuCau/**",
                "/packageSim/addDataCombo/**", "/packageSim/addNhuCau/**").access("hasRole('ROLE_PACKAGE_SIM_INSERT')");
        http.authorizeRequests().antMatchers("/packageSim/delete/**").access("hasRole('ROLE_PACKAGE_SIM_DELETE')");

        http.authorizeRequests().antMatchers("/tblSales/list/**").access("hasRole('ROLE_TBL_SALES_LIST')");
        http.authorizeRequests().antMatchers("/tblSales/detail/**").access("hasRole('ROLE_TBL_SALES_DETAIL')");
        http.authorizeRequests().antMatchers("/tblSales/update/**").access("hasRole('ROLE_TBL_SALES_UPDATE')");
        http.authorizeRequests().antMatchers("/tblSales/insert/**").access("hasRole('ROLE_TBL_SALES_INSERT')");
        http.authorizeRequests().antMatchers("/tblSales/delete/**").access("hasRole('ROLE_TBL_SALES_DELETE')");
        http.authorizeRequests().antMatchers("/tblSales/active/**", "/tblSales/lock/**").access("hasRole('ROLE_TBL_SALES_ACTIVE')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//

                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/users/updatePersonal", true)//
                .failureUrl("/login?error=true")//
                .usernameParameter("userName")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                // (Sau khi logout, chuyển tới trang home)
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }
}
