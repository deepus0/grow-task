package au.com.deep.grow.client;

import au.com.deep.grow.model.xero.XeroTenant;
import com.xero.models.payrollau.Employee;
import com.xero.models.payrollau.Employees;
import com.xero.models.payrollau.PayRun;
import com.xero.models.payrollau.PayRuns;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


@FeignClient(name = "xeroClient", url = "${xero.api.url}")
public interface XeroApiClient {

    @GetMapping(value = "/connections", consumes = "application/json")
    List<XeroTenant> getConnections(@RequestHeader("Authorization") String authorizationHeader);

    @GetMapping(value = "/payroll.xro/1.0/Employees", consumes = "application/json", produces = "application/json")
    Employees getEmployees(@RequestHeader("Authorization") String authorizationHeader, @RequestHeader("Xero-tenant-id") String tenantHeader);

    @GetMapping(value = "/payroll.xro/1.0/Employees/{employeeId}", consumes = "application/json", produces = "application/json")
    Employee getEmployee(@RequestHeader("Authorization") String authorizationHeader, @RequestHeader("Xero-tenant-id") String tenantHeader, @PathVariable("employeeId") String employeeId);
}
