import { Profile } from './profile';
export interface User {
  id: string,
  email: string,
  profiles?: Profile[],
  roles: string[],
  username: string,
  token: string
}
