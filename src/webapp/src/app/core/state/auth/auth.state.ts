import {Injectable} from '@angular/core';
import * as AuthActions from './auth.action';
import {Action, Selector, State, StateContext} from '@ngxs/store';
import {AuthStateModel} from './auth.state.model';
import {TokenModel} from '../../models/token.model';
import {AuthRestService} from '../../services/rest/auth.rest.service';
import {UserRestService} from '../../services/rest/user.rest.service';
import {decodeJwt} from '../../util/jwt.util';
import {Invalidate} from './auth.action';

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
      isLoading: true,
      code: payload.code
    })
    this.authService.retrieveToken(payload.code).subscribe(token => {
      this.userService.getTenants(token.access_token).subscribe(tenants => {
        const event_id = decodeJwt(token.access_token).authentication_event_id;
        const validTenant = tenants.find(tenant => tenant.authEventId === event_id);
        if (validTenant) {
          ctx.patchState({
            token: token,
            tenantId: validTenant.tenantId,
            isLoading: false,
          });
        } else {
          ctx.dispatch(new Invalidate());
        }

      })
    });
  }

  @Action(AuthActions.Invalidate)
  invalidate(ctx: StateContext<AuthStateModel>, action: AuthActions.Authenticate) {
    ctx.setState(initialState);
  }
}
