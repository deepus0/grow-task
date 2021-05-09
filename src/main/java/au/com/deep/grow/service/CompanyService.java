package au.com.deep.grow.service;

import au.com.deep.grow.client.XeroApiClient;
import au.com.deep.grow.utils.WebUtils;
import com.xero.models.payrollau.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyService {

    private final XeroApiClient xeroApiClient;

    public CompanyService(XeroApiClient xeroApiClient) {
        this.xeroApiClient = xeroApiClient;
    }

    public List<Employee> getEmployees(HttpServletRequest request) {
        String accessToken = WebUtils.getAuthorization(request);
        String tenantId = WebUtils.getTenantId(request);

        List<Employee> allEmployees = xeroApiClient.getEmployees(accessToken, tenantId).getEmployees();
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            employees.add(xeroApiClient.getEmployee(accessToken, tenantId, employee.getEmployeeID().toString()));
        }
        return employees;
    }
}
