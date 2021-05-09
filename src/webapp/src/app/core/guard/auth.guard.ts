import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from "@ngxs/store";
import {RouteConstant} from '../constant/route.constant';
import {AuthState} from '../state/auth/auth.state';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivateChild {

  constructor(private store: Store, private router: Router) {
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.store.selectSnapshot(AuthState.getCode)) {
      return true;
    }
    this.router.navigate([RouteConstant.LOGIN], {queryParams: {returnUrl: state.url}});
    return false;
  }

}
