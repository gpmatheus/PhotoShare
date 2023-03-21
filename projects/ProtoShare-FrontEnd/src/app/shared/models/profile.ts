import { Post } from './post';
export interface Profile {
  id: string;
  name: string;
  about: string;
  profileImageUrl: string;
  posts?: Post[];
  ownerId?: string;
}
