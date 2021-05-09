import {NgModule} from '@angular/core';

import {DashboardRoutingModule} from './dashboard-routing.module';
import {SharedModule} from "../../shared/shared.module";
import { LandingComponent } from './page/landing/landing.component';
import { TotalSummaryComponent } from './components/total-summary/total-summary.component';
import { CustomerSummaryComponent } from './components/customer-summary/customer-summary.component';
import { SupplierSummaryComponent } from './components/supplier-summary/supplier-summary.component';


@NgModule({
  declarations: [LandingComponent, TotalSummaryComponent, CustomerSummaryComponent, SupplierSummaryComponent],
  imports: [
    SharedModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
