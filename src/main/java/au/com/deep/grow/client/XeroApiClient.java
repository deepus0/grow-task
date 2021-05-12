package au.com.deep.grow.client;

import au.com.deep.grow.config.feign.XeroApiRequestInterceptor;
import com.xero.models.accounting.Contacts;
import com.xero.models.accounting.Invoices;
import com.xero.models.payrollau.Employees;
import com.xero.models.payrollau.PayrollCalendars;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "xeroApiClient", url = "${xero.api.url}", configuration = XeroApiRequestInterceptor.class)
public interface XeroApiClient {

    @GetMapping(value = "/payroll.xro/1.0/Employees", consumes = "application/json", produces = "application/json")
    Employees getEmployees();

    @GetMapping(value = "/payroll.xro/1.0/Employees/{employeeId}", consumes = "application/json", produces = "application/json")
    Employees getEmployee(@PathVariable("employeeId") String employeeId);

    @GetMapping(value = "/api.xro/2.0/Contacts", consumes = "application/json", produces = "application/json")
    Contacts getContacts();

    @GetMapping(value = "/api.xro/2.0/Invoices", consumes = "application/json", produces = "application/json")
    Invoices getInvoices(@RequestParam("ContactIDs") String contactIds);

    @GetMapping(value = "payroll.xro/1.0/PayrollCalendars", consumes = "application/json", produces = "application/json")
    PayrollCalendars getPayrollCalendars();
}
