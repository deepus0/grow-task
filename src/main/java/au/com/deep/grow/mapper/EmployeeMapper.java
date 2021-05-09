package au.com.deep.grow.mapper;

import au.com.deep.grow.model.ui.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee map(com.xero.models.payrollau.Employee response) {
        Employee employee = new Employee();
        employee.setFirstName(response.getFirstName());
        employee.setLastName(response.getLastName());
        if (!response.getPayTemplate().getEarningsLines().isEmpty()) {
            if (null != response.getPayTemplate().getEarningsLines().get(0).getAnnualSalary()) {
                employee.setSalary(response.getPayTemplate().getEarningsLines().get(0).getAnnualSalary());
            }
        }
        return employee;
    }
}
