package au.com.deep.grow.service.impl;

import au.com.deep.grow.client.XeroAuthApiClient;
import au.com.deep.grow.client.XeroIdentityClient;
import au.com.deep.grow.config.properties.XeroPropertyConfig;
import au.com.deep.grow.model.RedirectResponse;
import au.com.deep.grow.model.xero.IdentityTokenRequest;
import au.com.deep.grow.model.xero.IdentityTokenResponse;
import au.com.deep.grow.model.xero.XeroTenant;
import au.com.deep.grow.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class XeroAuthService implements AuthService {

    private final XeroIdentityClient xeroIdentityClient;
    private final XeroAuthApiClient xeroAuthApiClient;
    private final XeroPropertyConfig xeroProperties;

    public XeroAuthService(XeroIdentityClient xeroIdentityClient, XeroAuthApiClient xeroAuthApiClient, XeroPropertyConfig xeroProperties) {
        this.xeroIdentityClient = xeroIdentityClient;
        this.xeroAuthApiClient = xeroAuthApiClient;
        this.xeroProperties = xeroProperties;
    }

    public RedirectResponse getRedirectUrl() {
        log.info("Getting Xero login redirect url");
        RedirectResponse response = new RedirectResponse();

        List<String> scopeList = new ArrayList<>();
        scopeList.add("openid");
        scopeList.add("profile");
        scopeList.add("email");
        scopeList.add("accounting.contacts.read");
        scopeList.add("payroll.employees.read");
        scopeList.add("payroll.settings.read");
        scopeList.add("accounting.transactions.read");

        try {
            URIBuilder builder = new URIBuilder(xeroProperties.getLoginUrl());
            builder.addParameter("response_type", "code");
            builder.addParameter("client_id", xeroProperties.getClientId());
            builder.addParameter("redirect_uri", xeroProperties.getCallbackUrl());
            builder.addParameter("scope", scopeList.stream().reduce("", (partialString, element) -> partialString + " " + element).stripTrailing());
            builder.addParameter("state", "test");
            response.setUrl(builder.toString());
        } catch (URISyntaxException e) {
            // do nothing
        }
        return response;
    }

    public IdentityTokenResponse getToken(String code) {
        log.info("Retrieving token from code: {}", code);
        IdentityTokenRequest request = new IdentityTokenRequest();
        request.setGrant_type("authorization_code");
        request.setCode(code);
        request.setRedirect_uri(xeroProperties.getCallbackUrl());

        String authorizationCode = xeroProperties.getClientId() + ":" + xeroProperties.getClientSecret();
        String authorization = "Basic " + Base64.getEncoder().encodeToString(authorizationCode.getBytes());

        IdentityTokenResponse response = xeroIdentityClient.getToken(authorization, request);
        log.info("Token response: {}", response);
        return response;
    }

    public List<XeroTenant> getTenants(String accessToken) {
        String authorization = "Bearer " + accessToken;
        log.info("Retrieving tenants from access token {}", accessToken);
        List<XeroTenant> tenants = xeroAuthApiClient.getConnections(authorization);
        log.info("Tenants response: {}", tenants);
        return tenants;
    }
}
