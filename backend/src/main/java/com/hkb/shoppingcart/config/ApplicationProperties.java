package com.hkb.shoppingcart.config;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
@Component
public class ApplicationProperties {
    @Value("${shoppingcart.frontend-dist-dir}")
    @NotBlank
    private String frontendDistDir;

    public String getFrontendDistDir() {
        return frontendDistDir;
    }
}
