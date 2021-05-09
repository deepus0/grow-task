package au.com.deep.grow.service.impl;

import au.com.deep.grow.client.XeroApiClient;
import au.com.deep.grow.mapper.ContactMapper;
import au.com.deep.grow.mapper.EmployeeMapper;
import au.com.deep.grow.model.ui.Contact;
import au.com.deep.grow.model.ui.Employee;
import au.com.deep.grow.service.CompanyService;
import com.xero.models.accounting.Invoices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class XeroCompanyService implements CompanyService {

    private final XeroApiClient xeroApiClient;
    private final EmployeeMapper employeeMapper;
    private final ContactMapper contactMapper;

    public XeroCompanyService(XeroApiClient xeroApiClient, EmployeeMapper employeeMapper, ContactMapper contactMapper) {
        this.xeroApiClient = xeroApiClient;
        this.employeeMapper = employeeMapper;
        this.contactMapper = contactMapper;
    }

    public List<Employee> getEmployees() {
        List<com.xero.models.payrollau.Employee> allEmployees = xeroApiClient.getEmployees().getEmployees();
        List<Employee> employees = new ArrayList<>();
        for (com.xero.models.payrollau.Employee employee : allEmployees) {
            try {
                com.xero.models.payrollau.Employee temp = xeroApiClient.getEmployee(employee.getEmployeeID().toString()).getEmployees().get(0);
                employees.add(employeeMapper.map(temp));
            } catch (Exception e) {
                log.error("Failed to get employee information for employee {}", employee.getEmployeeID(), e);
            }
        }
        return employees;
    }

    public List<Contact> getContacts() {
        List<com.xero.models.accounting.Contact> allContacts = xeroApiClient.getContacts().getContacts();
        List<Contact> contacts = allContacts.stream().filter(contact -> contact.getContactStatus().equals(com.xero.models.accounting.Contact.ContactStatusEnum.ACTIVE))
                .filter(contact -> contact.getIsCustomer() || contact.getIsSupplier())
                .map(contactMapper::map)
                .collect(Collectors.toList());

        Invoices invoices = xeroApiClient.getInvoices(contacts.stream()
                .map(Contact::getContactId)
                .reduce("", (partialString, element) -> partialString + "," + element));

        return contacts.stream().map(contact -> contactMapper.map(invoices, contact)).collect(Collectors.toList());
    }
}
