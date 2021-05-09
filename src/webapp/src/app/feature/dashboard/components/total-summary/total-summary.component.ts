import {Component, OnInit} from '@angular/core';
import {CompanyRestService} from '../../../../core/services/rest/company.rest.service';
import {ContactModel} from '../../../../core/models/contact.model';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'app-total-summary',
  templateUrl: './total-summary.component.html',
  styleUrls: ['./total-summary.component.scss']
})
export class TotalSummaryComponent implements OnInit {

  constructor(private companyService: CompanyRestService) {
  }

  private setting = {
    element: {
      dynamicDownload: null as HTMLElement
    }
  }

  numberOfEmployees: number;
  averageAnnualSummary: number;

  customers: ContactModel[];
  suppliers: ContactModel[];

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.averageAnnualSummary = 0;
    this.companyService.getEmployees().subscribe(res => {

      this.numberOfEmployees = res.length;
      for (let employee of res) {
        this.averageAnnualSummary += employee.salary ?? 0;
      }
    });
    this.companyService.getContacts().subscribe(res => {
      console.log(res);
      this.customers = [];
      this.suppliers = [];
      for (let contact of res) {
        if (contact.customer) {
          this.customers.push(contact);
        } else if (contact.supplier) {
          this.suppliers.push(contact)
        }
      }
    });
  }

  canDownloadData(): boolean {
    return (this.numberOfEmployees !== undefined && this.suppliers !== undefined && this.customers !== undefined);
  }

  downloadData(): void {
    this.createData().subscribe((res) => {
      this.dynamicDownloadByHtmlTag({
        fileName: 'Report Data',
        text: JSON.stringify(res)
      });
    })
  }

  private createData(): Observable<any> {
    return of({
      numberOfEmployees: this.numberOfEmployees,
      averageAnnualSummary: this.averageAnnualSummary,
      customers: this.customers,
      suppliers: this.suppliers,
    })
  }

  private dynamicDownloadByHtmlTag(arg: {
    fileName: string,
    text: string
  }) {
    if (!this.setting.element.dynamicDownload) {
      this.setting.element.dynamicDownload = document.createElement('a');
    }
    const element = this.setting.element.dynamicDownload;
    const fileType = 'text/plain';
    element.setAttribute('href', `data:${fileType};charset=utf-8,${encodeURIComponent(arg.text)}`);
    element.setAttribute('download', arg.fileName);

    let event = new MouseEvent("click");
    element.dispatchEvent(event);
  }
}
