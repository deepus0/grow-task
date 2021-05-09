
export class Logout {
  static readonly type = '[Auth] Logout';
}

export class Authenticate {
  static readonly type = '[Auth] Authenticate';

  constructor(public payload: any) {
  }
}
