package au.com.deep.grow.service;

import au.com.deep.grow.client.XeroApiClient;
import au.com.deep.grow.mapper.ContactMapper;
import au.com.deep.grow.mapper.EmployeeMapper;
import au.com.deep.grow.model.ui.Contact;
import au.com.deep.grow.model.ui.Employee;
import au.com.deep.grow.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyService {

    private final XeroApiClient xeroApiClient;
    private final EmployeeMapper employeeMapper;
    private final ContactMapper contactMapper;

    public CompanyService(XeroApiClient xeroApiClient, EmployeeMapper employeeMapper, ContactMapper contactMapper) {
        this.xeroApiClient = xeroApiClient;
        this.employeeMapper = employeeMapper;
        this.contactMapper = contactMapper;
    }

    public List<Employee> getEmployees(HttpServletRequest request) {
        String accessToken = WebUtils.getAuthorization(request);
        String tenantId = WebUtils.getTenantId(request);
        List<com.xero.models.payrollau.Employee> allEmployees = xeroApiClient.getEmployees(accessToken, tenantId).getEmployees();
        List<Employee> employees = new ArrayList<>();
        for (com.xero.models.payrollau.Employee employee : allEmployees) {
            try {
                com.xero.models.payrollau.Employee temp = xeroApiClient.getEmployee(accessToken, tenantId, employee.getEmployeeID().toString()).getEmployees().get(0);
                employees.add(employeeMapper.map(temp));
            } catch (Exception e) {
                log.error("Failed to get employee information for employee {}", employee.getEmployeeID(), e);
            }
        }
        return employees;
    }

    public List<Contact> getContacts(HttpServletRequest request) {
        String accessToken = WebUtils.getAuthorization(request);
        String tenantId = WebUtils.getTenantId(request);

        List<com.xero.models.accounting.Contact> allContacts = xeroApiClient.getContacts(accessToken, tenantId).getContacts();

        return allContacts.stream().filter(contact -> contact.getContactStatus().equals(com.xero.models.accounting.Contact.ContactStatusEnum.ACTIVE))
                .filter(contact -> contact.getIsCustomer() || contact.getIsSupplier())
                .map(contactMapper::map)
                .map(contact -> contactMapper.map(xeroApiClient.getInvoices(accessToken, tenantId, contact.getContactId()), contact))
                .collect(Collectors.toList());
    }
}
