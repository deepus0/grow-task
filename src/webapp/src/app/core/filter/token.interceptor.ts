import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Store} from '@ngxs/store';
import {Observable} from 'rxjs';
import {AuthState} from '../state/auth/auth.state';
import {TokenModel} from '../models/token.model';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor {

  constructor(private store: Store) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token: TokenModel = this.store.selectSnapshot<TokenModel>(AuthState.getToken);
    const tenantId: string = this.store.selectSnapshot(AuthState.getTenantId);
    if (req.url.indexOf('api/') < 0 || !token || !tenantId) {
      return next.handle(req);
    }
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token.access_token}`,
        'tenant-id': tenantId
      }
    });
    return next.handle(req);
  }
}
