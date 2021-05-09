package au.com.deep.grow.service;

import au.com.deep.grow.model.xero.IdentityTokenResponse;
import au.com.deep.grow.model.RedirectResponse;
import au.com.deep.grow.model.xero.XeroTenant;

import java.util.List;

public interface AuthService {

    /**
     * Returns a RedirectResponse.class with a String url for redirect
     * @return A string url for redirect
     */
    RedirectResponse getRedirectUrl();

    /**
     * Returns a token object which has been used to authenticate against another app
     * @param code Authenticated user code
     * @return A token object
     */
    IdentityTokenResponse getToken(String code);

    /**
     * Returns a list of tenants against the access token from authentication
     * @param accessToken Access token retrieved from the token
     * @return A list of tenants from the access token
     */
    List<XeroTenant> getTenants(String accessToken);
}
