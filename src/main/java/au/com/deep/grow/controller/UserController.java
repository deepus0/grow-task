package au.com.deep.grow.controller;

import au.com.deep.grow.model.xero.IdentityTokenResponse;
import au.com.deep.grow.model.RedirectResponse;
import au.com.deep.grow.model.xero.XeroTenant;
import au.com.deep.grow.service.AuthService;
import au.com.deep.grow.service.impl.XeroAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Qualifier("xeroAuthService")
    private final AuthService authService;

    public UserController(XeroAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/xero/login/redirect")
    public RedirectResponse getRedirectUrl() {
        return authService.getRedirectUrl();
    }

    @GetMapping("/token/{code}")
    public IdentityTokenResponse getToken(@PathVariable("code") String code) {
        return authService.getToken(code);
    }

    @GetMapping("/tenants/{accessToken}")
    public List<XeroTenant> getTenants(@PathVariable("accessToken") String accessToken) {
        return authService.getTenants(accessToken);
    }
}
