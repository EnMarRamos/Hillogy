import { Author } from "./author";
import { Member } from './member';

export class Book {

  isbn: string;
  title: string;
  available: boolean;
  authors: Author[];
  member: Member;
  concatenatedAuthors: string;
}


