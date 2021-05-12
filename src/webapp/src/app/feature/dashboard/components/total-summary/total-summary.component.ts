import {Component, OnInit} from '@angular/core';
import {CompanyRestService} from '../../../../core/services/rest/company.rest.service';
import {ContactModel} from '../../../../core/models/contact.model';

@Component({
  selector: 'app-total-summary',
  templateUrl: './total-summary.component.html',
  styleUrls: ['./total-summary.component.scss']
})
export class TotalSummaryComponent implements OnInit {

  constructor(private companyService: CompanyRestService) {
  }

  numberOfEmployees: number = 0;
  averageAnnualSummary: number = 0;
  customers: ContactModel[] = [];
  suppliers: ContactModel[] = [];

  employeesLoading = false;
  contactsLoading = false;

  ngOnInit(): void {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.employeesLoading = true;
    this.companyService.getEmployees().subscribe(res => {
      this.numberOfEmployees = res.length;
      let totalSummary = 0;
      let hasSalary = 0;
      for (let employee of res) {
        totalSummary += employee.salary ?? 0;
        hasSalary++;
      }
      this.averageAnnualSummary = (totalSummary / hasSalary);
      this.employeesLoading = false;
    });
    this.contactsLoading = true;
    this.companyService.getContacts().subscribe(res => {
      for (let contact of res) {
        if (contact.customer) {
          this.customers.push(contact);
        } else if (contact.supplier) {
          this.suppliers.push(contact)
        }
      }
      this.contactsLoading = false;
    });
  }

  canDownloadData(): boolean {
    return (this.numberOfEmployees !== undefined && this.suppliers !== undefined && this.customers !== undefined);
  }

  downloadData(): void {
    this.downloadFile(this.suppliers, 'suppliers.csv');
    this.downloadFile(this.customers, 'customers.csv');
    this.downloadSummary();
  }

  downloadFile(data: ContactModel[], fileName: string) {
    const replacer = (key, value) => (value === null ? '' : value);
    const header = Object.keys(data[0]);
    const csv = data.map((row) =>
      header
        .map((fieldName) => JSON.stringify(row[fieldName], replacer))
        .join(',')
    );
    csv.unshift(header.join(','));
    const csvArray = csv.join('\r\n');
    this.downloadCsv(fileName, csvArray);
  }

  downloadSummary() {
    const csv = ['numberOfEmployees,averageAnnualSummary', this.numberOfEmployees + ',' + this.averageAnnualSummary]
    const csvArray = csv.join('\r\n');
    this.downloadCsv('summary.csv', csvArray);
  }

  downloadCsv(fileName: string, csvArray: any) {
    const a = document.createElement('a');
    const blob = new Blob([csvArray], {type: 'text/csv'});
    const url = window.URL.createObjectURL(blob);

    a.href = url;
    a.download = fileName;
    a.click();
    window.URL.revokeObjectURL(url);
    a.remove();
  }
}
