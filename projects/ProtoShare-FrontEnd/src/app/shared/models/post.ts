import { Profile } from './profile';
import { Comment } from './comment';
import { Description } from './description';
export interface Post {
  id: string;
  imageUrl: string;
  postingDate: Date;
  descriptions?: Description[];
  comments?: Comment[];
  profile: Profile;
}
