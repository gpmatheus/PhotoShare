import { Profile } from './profile';
import { Description } from './description';
import { Comment } from './comment'
export interface Post {
  id: string;
  imageUrl: string;
  postingDate: Date;
  descriptions?: Description[];
  comments?: Comment[];
  profile: Profile;
}
