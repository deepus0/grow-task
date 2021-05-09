package au.com.deep.grow.client;

import au.com.deep.grow.model.xero.XeroTenant;
import com.xero.models.accounting.Contacts;
import com.xero.models.accounting.Invoices;
import com.xero.models.payrollau.Employees;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "xeroClient", url = "${xero.api.url}")
public interface XeroApiClient {

    @GetMapping(value = "/connections", consumes = "application/json")
    List<XeroTenant> getConnections(@RequestHeader("Authorization") String authorizationHeader);

    @GetMapping(value = "/payroll.xro/1.0/Employees", consumes = "application/json", produces = "application/json")
    Employees getEmployees(@RequestHeader("Authorization") String authorizationHeader, @RequestHeader("Xero-tenant-id") String tenantHeader);

    @GetMapping(value = "/payroll.xro/1.0/Employees/{employeeId}", consumes = "application/json", produces = "application/json")
    Employees getEmployee(@RequestHeader("Authorization") String authorizationHeader, @RequestHeader("Xero-tenant-id") String tenantHeader, @PathVariable("employeeId") String employeeId);

    @GetMapping(value = "api.xro/2.0/Contacts", consumes = "application/json", produces = "application/json")
    Contacts getContacts(@RequestHeader("Authorization") String authorizationHeader, @RequestHeader("Xero-tenant-id") String tenantHeader);

    @GetMapping(value = "api.xro/2.0/Invoices", consumes = "application/json", produces = "application/json")
    Invoices getInvoices(@RequestHeader("Authorization") String authorizationHeader, @RequestHeader("Xero-tenant-id") String tenantHeader, @RequestParam("ContactIDs") String contactId);
}
