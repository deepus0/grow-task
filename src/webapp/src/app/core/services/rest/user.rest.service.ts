import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserRestService {

  constructor(private http: HttpClient) {
  }

  getTenants(token: string): Observable<any> {
    return this.http.get<any>(`api/tenants/${token}`);
  }
}
