import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngxs/store';
import * as AuthActions from '../../state/auth/auth.action'
import {RouteConstant} from '../../constant/route.constant';

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.scss']
})
export class CallbackComponent implements OnInit {

  constructor( private router: Router, private store: Store, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      const code = params['code'];
      this.store.dispatch(new AuthActions.Authenticate({code}));
      this.router.navigate([RouteConstant.POST_LOGIN, RouteConstant.DASHBOARD]);
    })
  }
}
