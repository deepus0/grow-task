import {UserStateModel} from './user.state.model';
import {Action, Selector, State, StateContext} from '@ngxs/store';
import {Injectable} from '@angular/core';
import * as UserActions from './user.action';

const initialState: UserStateModel = {
  username: undefined,
  lastLoginDate: null,
}

@State<UserStateModel>({
  name: 'user',
  defaults: initialState
})
@Injectable({providedIn: 'root'})
export class UserState {

  @Selector()
  static getUser(state: UserStateModel) {
    return state.username;
  }

  @Selector()
  static getCode(state: UserStateModel) {
    return state.code;
  }

  @Action(UserActions.AddCode)
  addCode({patchState}: StateContext<UserStateModel>, {payload}: UserActions.AddCode) {
    patchState(({code: payload}));
  }


  @Action(UserActions.ResetUser)
  resetUser({setState}: StateContext<UserStateModel>, action: UserActions.ResetUser) {
    setState(({...initialState}));
  }

}
