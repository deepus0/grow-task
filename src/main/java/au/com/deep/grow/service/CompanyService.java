package au.com.deep.grow.service;

import au.com.deep.grow.model.ui.Contact;
import au.com.deep.grow.model.ui.Employee;

import java.util.List;

public interface CompanyService {

    /**
     * Retrieves a list of employees, using request headers from the HttpServletRequest
     * @return A list of employees
     */
    List<Employee> getEmployees();

    /**
     * Retrieves a list of contacts (i.e. External companies), using request headers from the HttpServletRequest
     * @return A list of contacts
     */
    List<Contact> getContacts();
}
