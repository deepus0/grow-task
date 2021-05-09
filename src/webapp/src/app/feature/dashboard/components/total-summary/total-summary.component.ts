import {Component, OnInit} from '@angular/core';
import {CompanyRestService} from '../../../../core/services/rest/company.rest.service';
import {Store} from '@ngxs/store';

@Component({
  selector: 'app-total-summary',
  templateUrl: './total-summary.component.html',
  styleUrls: ['./total-summary.component.scss']
})
export class TotalSummaryComponent implements OnInit {

  constructor(private companyService: CompanyRestService, private store: Store) {
  }

  numberOfEmployees: number;
  averageAnnualSummary: number;

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.companyService.getEmployees().subscribe(res => {
      this.numberOfEmployees = res.length;
    })
  }
}
