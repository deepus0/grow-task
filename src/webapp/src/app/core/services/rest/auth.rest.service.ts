import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenModel} from '../../models/token.model';

@Injectable({
  providedIn: 'root'
})
export class AuthRestService {

  constructor(private http: HttpClient) {
  }

  getXeroRedirectUrl(): Observable<any> {
    return this.http.get<any>('api/xero/login/redirect');
  }

  retrieveToken(code: string): Observable<TokenModel> {
    return this.http.get<TokenModel>(`/api/token/${code}`)
  }
}
