import {NgModule} from '@angular/core';

import {DashboardRoutingModule} from './dashboard-routing.module';
import {SharedModule} from "../../shared/shared.module";
import {LandingComponent} from './page/landing/landing.component';
import {TotalSummaryComponent} from './components/total-summary/total-summary.component';


@NgModule({
  declarations: [
    LandingComponent,
    TotalSummaryComponent],
  imports: [
    SharedModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule {
}
