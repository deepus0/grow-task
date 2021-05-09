package au.com.deep.grow.controller;

import au.com.deep.grow.model.xero.IdentityTokenResponse;
import au.com.deep.grow.model.xero.XeroResponse;
import au.com.deep.grow.model.xero.XeroTenant;
import au.com.deep.grow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/xero/login/redirect")
    public XeroResponse getXeroRedirectUrl() {
        return userService.getXeroRedirectUrl();
    }

    @GetMapping("/token/{code}")
    public IdentityTokenResponse getToken(@PathVariable("code") String code) {
        return userService.getToken(code);
    }

    @GetMapping("/tenants/{accessToken}")
    public List<XeroTenant> getTenants(@PathVariable("accessToken") String accessToken) {
        return userService.getTenants(accessToken);
    }
}
