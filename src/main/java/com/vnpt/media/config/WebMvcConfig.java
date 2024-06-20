/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.config;

/**
 *
 * @author vnpt2
 */
import com.vnpt.media.interceptor.LogInterceptor;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.vnpt.media.*")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    // Cau hinh UTF - 8 cho cac trang

    /**
     * Configure TilesConfigurer.
     *
     * @return
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
        converters.add(stringConverter);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LogInterceptor áp dụng cho mọi URL.
        registry.addInterceptor(new LogInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bootstrap/**").addResourceLocations("/WEB-INF/bootstrap/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/dist/**").addResourceLocations("/WEB-INF/dist/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/plugins/**").addResourceLocations("/WEB-INF/plugins/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/views/**").addResourceLocations("/WEB-INF/views/").setCachePeriod(Integer.MAX_VALUE);
        registry.addResourceHandler("/ckfinder/**").addResourceLocations("/WEB-INF/plugins/ckfinder/");
        registry.addResourceHandler("/public/image/**").addResourceLocations("classpath:/plugins/uploadmedia/");

    }
}
