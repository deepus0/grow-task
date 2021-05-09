import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EmployeeModel} from '../../models/payroll/employee.model';
import {ContactModel} from '../../models/contact.model';

@Injectable({
  providedIn: 'root'
})
export class CompanyRestService {

  constructor(private http: HttpClient) {
  }

  getEmployees(): Observable<EmployeeModel[]> {
    return this.http.get<EmployeeModel[]>(`api/company/employees`);
  }

  getContacts(): Observable<ContactModel[]> {
    return this.http.get<ContactModel[]>(`api/company/contacts`);
  }
}
