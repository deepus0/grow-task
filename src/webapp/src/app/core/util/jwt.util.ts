import jwt_decode from 'jwt-decode';

export function decodeJwt(token: string): any {
  try {
    return jwt_decode(token);
  } catch (Error) {
    return null;
  }
}
