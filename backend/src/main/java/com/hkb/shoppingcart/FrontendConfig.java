package com.hkb.shoppingcart;

import com.hkb.shoppingcart.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@Configuration
@EnableWebMvc
public class FrontendConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = new File(applicationProperties.getFrontendDistDir()).getAbsolutePath();
        final String location = String.format("file:%s/", absolutePath);

        registry.addResourceHandler("/static/**")
            .addResourceLocations(location);
    }
}
