import {Component, Inject, OnInit} from '@angular/core';
import {DOCUMENT} from '@angular/common';
import {AuthRestService} from '../../../../core/services/rest/auth.rest.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isLoading: boolean;

  constructor(@Inject(DOCUMENT) private document: Document, private authService: AuthRestService) {
  }

  ngOnInit(): void {
  }

  directToXero(): void {
    this.isLoading = true;
    this.authService.getXeroRedirectUrl().subscribe((res) => {
      this.document.location.href = res.url;
    });
  }
}
