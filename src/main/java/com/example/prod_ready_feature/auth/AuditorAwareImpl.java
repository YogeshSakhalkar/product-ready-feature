package com.example.prod_ready_feature.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        /**get spring context
         * get authentication
         * get the username
         * get the principle*/
        return Optional.of("Yogesh D Sakhalkar");
    }
}
