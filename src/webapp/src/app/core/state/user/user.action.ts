export class SetUser {
  static readonly type = '[User] Set User';

  constructor(public payload: string) {
  }
}

export class AddCode {
  static readonly type = '[User] Add Code';

  constructor(public payload: string) {
  }
}

export class ResetUser {
  static readonly type = '[User] Reset User';
}
