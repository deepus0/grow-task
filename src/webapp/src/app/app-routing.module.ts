import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './core/auth/page/login/login.component';
import {RouteConstant} from './core/constant/route.constant';
import {PostloginComponent} from './core/layout/postlogin/postlogin.component';
import {PreloginComponent} from './core/layout/prelogin/prelogin.component';
import {genRouterLink} from './core/util/route.util';
import {AuthGuard} from './core/guard/auth.guard';
import {CallbackComponent} from './core/layout/callback/callback.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: genRouterLink([RouteConstant.POST_LOGIN, RouteConstant.DASHBOARD]),
    pathMatch: 'full'
  },
  {
    path: '',
    component: PreloginComponent,
    children: [
      {
        path: RouteConstant.LOGIN,
        component: LoginComponent,
      },
    ]
  },
  {
    path: RouteConstant.CALLBACK,
    component: CallbackComponent,
  },
  {
    path: RouteConstant.POST_LOGIN,
    component: PostloginComponent,
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        redirectTo: RouteConstant.DASHBOARD,
        pathMatch: 'full'
      },
      {
        path: RouteConstant.DASHBOARD,
        loadChildren: () => import('./feature/dashboard/dashboard.module').then(m => m.DashboardModule)
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
