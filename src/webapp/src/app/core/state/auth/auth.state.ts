import {Injectable} from '@angular/core';
import * as AuthActions from './auth.action';
import {Action, Selector, State, StateContext} from '@ngxs/store';
import {AuthStateModel} from './auth.state.model';
import {TokenModel} from '../../models/token.model';
import {AuthRestService} from '../../services/rest/auth.rest.service';
import {UserRestService} from '../../services/rest/user.rest.service';

const initialState: AuthStateModel = {
  code: null,
  token: null,
  tenantId: null,
  username: null,
  isLoading: false,
}

@State<AuthStateModel>({
  name: 'auth',
  defaults: initialState
})

@Injectable()
export class AuthState {

  constructor(private authService: AuthRestService, private userService: UserRestService) {
  }

  @Selector()
  static getCode(state: AuthStateModel): string {
    return state.code;
  }

  @Selector()
  static getToken(state: AuthStateModel): TokenModel {
    return state.token;
  }

  @Selector()
  static isLoading(state: AuthStateModel): boolean {
    return state.isLoading;
  }

  @Selector()
  static getTenantId(state: AuthStateModel): string {
    return state.tenantId;
  }

  @Action(AuthActions.Authenticate)
  authenticate(ctx: StateContext<AuthStateModel>, {payload}: AuthActions.Authenticate) {
    ctx.patchState({
      isLoading: true
    })
    this.authService.retrieveToken(payload.code).subscribe(response => {
      this.userService.getTenants(response.access_token).subscribe(res => {
        ctx.patchState({
          token: response,
          tenantId: res[0].tenantId,
          isLoading: false,
        })
      })
    });

    ctx.patchState({
      code: payload.code
    });

  }
}
