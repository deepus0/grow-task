package au.com.deep.grow.client;

import au.com.deep.grow.model.xero.XeroTenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "xeroAuthApiClient", url = "${xero.api.url}")
public interface XeroAuthApiClient {

    @GetMapping(value = "/connections", consumes = "application/json")
    List<XeroTenant> getConnections(@RequestHeader("Authorization") String authorizationHeader);
}
