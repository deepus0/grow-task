import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {TenantModel} from '../../models/tenant.model';

@Injectable({
  providedIn: 'root'
})
export class UserRestService {

  constructor(private http: HttpClient) {
  }

  getTenants(token: string): Observable<TenantModel[]> {
    return this.http.get<TenantModel[]>(`api/tenants/${token}`);
  }
}
