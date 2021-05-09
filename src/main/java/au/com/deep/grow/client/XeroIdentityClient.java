package au.com.deep.grow.client;

import au.com.deep.grow.config.XeroIdentityClientConfig;
import au.com.deep.grow.model.xero.IdentityTokenRequest;
import au.com.deep.grow.model.xero.IdentityTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "xeroIdentityClient", url = "${xero.identity.url}", configuration = XeroIdentityClientConfig.class)
public interface XeroIdentityClient {

    @PostMapping(value = "connect/token", consumes = "application/x-www-form-urlencoded")
    IdentityTokenResponse getToken(@RequestHeader("Authorization") String authorizationHeader, @RequestBody IdentityTokenRequest request);
}
