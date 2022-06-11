package com.cade.backend.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "my-tracking-list")
public interface ServerConfig {

    Oauth oauth();

    interface Oauth {

        OauthSetting google();

        interface OauthSetting {

            String serverUrl();

            String clientId();

            String clientSecret();

            String redirectUri();

        }

    }

}
