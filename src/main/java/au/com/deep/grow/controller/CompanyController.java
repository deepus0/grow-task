package au.com.deep.grow.controller;

import au.com.deep.grow.model.ui.Contact;
import au.com.deep.grow.model.ui.Employee;
import au.com.deep.grow.service.impl.XeroCompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final XeroCompanyService xeroCompanyService;

    public CompanyController(XeroCompanyService xeroCompanyService) {
        this.xeroCompanyService = xeroCompanyService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return xeroCompanyService.getEmployees();
    }

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return xeroCompanyService.getContacts();
    }
}
