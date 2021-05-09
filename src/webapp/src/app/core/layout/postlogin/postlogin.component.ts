import {Component, OnInit} from '@angular/core';
import {Actions, ofActionSuccessful, Store} from '@ngxs/store';
import {Logout} from '../../state/auth/auth.action';
import {Router} from '@angular/router';
import {AuthState} from '../../state/auth/auth.state';
import {RouteConstant} from '../../constant/route.constant';
import {genRouterLink} from '../../util/route.util';

@Component({
  selector: 'app-postlogin',
  templateUrl: './postlogin.component.html'
})
export class PostloginComponent implements OnInit {

  readonly POST_LOGIN_NAV = [
    {
      label: 'Home',
      route: genRouterLink([RouteConstant.POST_LOGIN, RouteConstant.DASHBOARD])
    }
  ]

  constructor(private router: Router, private store: Store, private actions: Actions) {
  }

  ngOnInit(): void {
    this.actions.pipe(ofActionSuccessful(Logout)).subscribe(() => {
      this.router.navigate([RouteConstant.LOGIN]);
    });
  }

  navigate(route: string): void {
    this.router.navigate([route]);
  }

  logout(): void {
    this.store.dispatch(Logout);
  }
}
