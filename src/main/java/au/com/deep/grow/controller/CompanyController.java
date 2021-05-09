package au.com.deep.grow.controller;

import au.com.deep.grow.service.CompanyService;
import com.xero.models.payrollau.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(HttpServletRequest request) {
        return companyService.getEmployees(request);
    }
}
