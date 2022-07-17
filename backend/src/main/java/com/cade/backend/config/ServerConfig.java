package com.cade.backend.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "my-tracking-list")
public interface ServerConfig {

    Oauth oauth();

    Client client();

    interface Client {
        TheMovieDbClientSetting theMovieDb();
        ClientSetting rawg();

        interface TheMovieDbClientSetting extends ClientSetting {
            String imageUrl();
        }

        interface ClientSetting {
            String serverUrl();
            String apiKey();
        }
    }

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
