package com.cfhero.versioning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VersioningMappingHandlerConfig {

    /**
     * Custom MappingHandler that maps all request to the versions specified by the annotation {@link ApiVersionRange}.
     * All version numbers get the prefix "v".
     *
     * @return
     */
    @Bean
    public ApiVersionRequestMappingHandlerMapping customMappingHandlerMapping() {
        // TODO load lastSupportedversion and latestVersion from config
        ApiVersionRequestMappingHandlerMapping handler = new ApiVersionRequestMappingHandlerMapping("v", 1, 1);
        handler.setOrder(-1);
        return handler;
    }

}