import {TokenModel} from '../../models/token.model';

export interface AuthStateModel {
  code: string | null;
  token: TokenModel | null;
  username: string | null;
  tenantId: string | null;
  isLoading: boolean;
}
