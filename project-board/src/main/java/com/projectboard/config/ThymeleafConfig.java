package com.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver( 
    		SpringResourceTemplateResolver defaultTemplateResolver) {
    	
        defaultTemplateResolver.setUseDecoupledLogic(true);

        return defaultTemplateResolver;
    }


//    @RequiredArgsConstructor
//    @Getter
//    @ConfigurationProperties("spring.thymeleaf3")
//    public static class Thymeleaf3Properties {
//        /**
//         * Use Thymeleaf 3 Decoupled Logic
//         */
//        private final boolean decoupledLogic;
//    }

}