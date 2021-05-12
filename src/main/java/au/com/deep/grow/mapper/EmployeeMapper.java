package au.com.deep.grow.mapper;

import au.com.deep.grow.model.ui.Employee;
import com.xero.models.payrollau.CalendarType;
import com.xero.models.payrollau.EarningsLine;
import com.xero.models.payrollau.EarningsRateCalculationType;
import com.xero.models.payrollau.PayrollCalendar;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public Employee map(com.xero.models.payrollau.Employee response, List<PayrollCalendar> payrollCalendars) {
        Employee employee = new Employee();
        employee.setFirstName(response.getFirstName());
        employee.setLastName(response.getLastName());

        PayrollCalendar payrollCalendar = null != response.getPayrollCalendarID() ? getPayrollCalendar(payrollCalendars, response.getPayrollCalendarID().toString()) : null;
        if (!response.getPayTemplate().getEarningsLines().isEmpty() && null!=payrollCalendar) {
            double salary = 0;
            for (EarningsLine earningsLine : response.getPayTemplate().getEarningsLines()) {
                if (EarningsRateCalculationType.ANNUALSALARY.equals(earningsLine.getCalculationType()) && null != earningsLine.getAnnualSalary()) {
                    salary += earningsLine.getAnnualSalary();
                } else if (EarningsRateCalculationType.ENTEREARNINGSRATE.equals(earningsLine.getCalculationType()) && null != earningsLine.getRatePerUnit() && null != earningsLine.getNormalNumberOfUnits()) {
                    if (CalendarType.FORTNIGHTLY.equals(payrollCalendar.getCalendarType())) {
                        salary += (earningsLine.getRatePerUnit() * earningsLine.getNormalNumberOfUnits() * 26);
                    } else if (CalendarType.WEEKLY.equals(payrollCalendar.getCalendarType())) {
                        salary += (earningsLine.getRatePerUnit() * earningsLine.getNormalNumberOfUnits() * 52);
                    }
                    // TODO prefill rest of CalendarTypes
                } else if (EarningsRateCalculationType.USEEARNINGSRATE.equals(earningsLine.getCalculationType())) {

                }
            }
            employee.setSalary(salary);
        }
        return employee;
    }

    private PayrollCalendar getPayrollCalendar(List<PayrollCalendar> payrollCalendars, String payrollCalendarId) {
        for (PayrollCalendar payrollCalendar : payrollCalendars) {
            if (payrollCalendar.getPayrollCalendarID().toString().equals(payrollCalendarId)) {
                return payrollCalendar;
            }
        }
        return null;
    }
}
