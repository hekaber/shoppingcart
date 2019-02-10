package com.hkb.shoppingcart;

import com.hkb.shoppingcart.frontend.model.FrontendManifest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ShoppingcartApplication.class)
public class TestConfig {

    @Bean
    public FrontendManifest frontendManifest(){
        return new FrontendManifest("foo","bar");
    }

}
