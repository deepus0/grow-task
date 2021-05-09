import {Component, OnInit} from '@angular/core';
import {Select} from '@ngxs/store';
import {AuthState} from '../../../../core/state/auth/auth.state';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent implements OnInit {

  @Select(AuthState.isLoading)
  isLoading$: Observable<boolean>;

  constructor() {
  }

  ngOnInit(): void {
  }

}
