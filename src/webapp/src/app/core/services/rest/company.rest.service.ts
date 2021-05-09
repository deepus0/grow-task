import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EmployeeModel} from '../../models/payroll/employee.model';

@Injectable({
  providedIn: 'root'
})
export class CompanyRestService {

  constructor(private http: HttpClient) {
  }

  getEmployees(): Observable<EmployeeModel[]> {
    return this.http.get<EmployeeModel[]>(`api/company/employees`);
  }
}
