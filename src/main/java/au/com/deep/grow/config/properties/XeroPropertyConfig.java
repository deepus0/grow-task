package au.com.deep.grow.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "xero")
public class XeroPropertyConfig {

    private String clientId;
    private String clientSecret;
    private String callbackUrl;
    private String loginUrl;

}
