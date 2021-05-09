import {Component, Inject, OnInit} from '@angular/core';
import {DOCUMENT} from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  readonly test = 'https://login.xero.com/identity/connect/authorize?response_type=code&client_id=E765504CC2AB4969A3B9FCF5E847DA70&redirect_uri=http://localhost:4200/callback&scope=openid profile email accounting.contacts payroll.employees accounting.transactions&state=123';
  isLoading: boolean;

  constructor(@Inject(DOCUMENT) private document: Document) {
  }

  ngOnInit(): void {
  }

  directToXero(): void {
    this.isLoading = true;
    // TODO have this point to the backend
    this.document.location.href = this.test;
  }
}
