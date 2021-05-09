package au.com.deep.grow.service;

import au.com.deep.grow.client.XeroApiClient;
import au.com.deep.grow.client.XeroIdentityClient;
import au.com.deep.grow.model.xero.IdentityTokenRequest;
import au.com.deep.grow.model.xero.IdentityTokenResponse;
import au.com.deep.grow.model.xero.XeroResponse;
import au.com.deep.grow.model.xero.XeroTenant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Value("${xero.client.id}")
    private String xeroClientId;
    @Value("${xero.client.secret}")
    private String xeroClientSecret;
    @Value("${xero.callback.url}")
    private String xeroCallbackUrl;
    @Value("${xero.login.url}")
    private String xeroLoginUrl;

    private final XeroIdentityClient xeroIdentityClient;
    private final XeroApiClient xeroApiClient;

    public UserService(XeroIdentityClient xeroIdentityClient, XeroApiClient xeroApiClient) {
        this.xeroIdentityClient = xeroIdentityClient;
        this.xeroApiClient = xeroApiClient;
    }

    public XeroResponse getXeroRedirectUrl() {
        log.info("Getting Xero login redirect url");
        XeroResponse response = new XeroResponse();
        response.setUrl(xeroLoginUrl + "?response_type=code&client_id= + " + xeroClientId + "&redirect_uri=" + xeroCallbackUrl + "&scope=openid profile email accounting.transactions payroll.employees&state=123");
        return response;
    }

    public IdentityTokenResponse getToken(String code) {
        log.info("Retrieving token from code: {}", code);
        IdentityTokenRequest request = new IdentityTokenRequest();
        request.setGrant_type("authorization_code");
        request.setCode(code);
        request.setRedirect_uri(xeroCallbackUrl);

        String authorizationCode = xeroClientId + ":" + xeroClientSecret;
        String authorization = "Basic " + Base64.getEncoder().encodeToString(authorizationCode.getBytes());

        IdentityTokenResponse response = xeroIdentityClient.getToken(authorization, request);
        log.info("Token response: {}", response);
        return response;
    }

    public List<XeroTenant> getTenants(String accessToken) {
        String authorization = "Bearer " + accessToken;
        log.info("Retrieving tenants from access token {}", accessToken);
        List<XeroTenant> tenants = xeroApiClient.getConnections(authorization);
        log.info("Tenants response: {}", tenants);
        return tenants;
    }
}
