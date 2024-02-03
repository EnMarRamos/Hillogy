import {Injectable} from '@angular/core';
import {Member} from '../model/member';
import {BookLoan} from '../model/book-loan';
import {CheckInBook} from '../model/check-in-book';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {Fine} from '../model/fine';
import {Book} from "../model/book";
import {PaginatedBook} from "../model/paginated-book";
import {environment} from "../../environments/environment";


@Injectable()
export class LibraryService {

  private apiURL = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  addMember(member: Member): Observable<any> {
    return this.http.post(this.apiURL + 'member', member);
  }

  search(searchQuery: string, page: number, size: number): Observable<PaginatedBook> {
    return this.http.get<PaginatedBook>(this.apiURL + 'search/books?q=' + searchQuery + '&page=' + page + '&size=' + size)
      .pipe(map(paginatedBook => {
        return this.mapPaginatedBookData(paginatedBook);
      }));
  }

  private mapPaginatedBookData(paginatedBook: PaginatedBook) {
    let books = paginatedBook.books.map(b => {
      const book = new Book();
      book.authors = b.authors;
      book.isbn = b.isbn;
      book.concatenatedAuthors = b.authors.map(z => z.name).join(", ")
      book.title = b.title;
      book.available = b.available;
      if(b.member){
        const member = new Member();
        member.memberId = b.member.memberId;
        member.name = b.member.name;
        book.member = member;
      }
      return book;
    });
    let page = paginatedBook.pagination;
    const paginated = new PaginatedBook();
    paginated.pagination = page;
    paginated.books = books;
    return paginated;
  }

  addLoan(bookLoan: BookLoan): Observable<any> {
    return this.http.post(this.apiURL + 'member/checkout', bookLoan);
  }

  calculateFines(): Observable<any> {
    return this.http.post(this.apiURL + 'fine/calculate', {});
  }

  searchToCheckIn(checkIn: CheckInBook, page: number, size: number): Observable<PaginatedBook> {
    return this.http.get<PaginatedBook>(this.apiURL + 'search/member?name=' +
      checkIn.name + '&cardId=' + checkIn.cardId + '&isbn=' + checkIn.isbn + '&page=' + page + '&size=' + size)
      .pipe(map(paginatedBook => {
        return this.mapPaginatedBookData(paginatedBook);
      }));
  }

  checkIn(checkInBook: CheckInBook): Observable<any> {
    return this.http.post(this.apiURL + 'member/checkIn', checkInBook);
  }

  showFines(): Observable<any> {
    return this.http.get(this.apiURL + 'fines');
  }

  payFine(fine: Fine): Observable<any> {
    return this.http.post(this.apiURL + 'fine', fine);
  }

  getFineForCardId(cardId: number): Observable<any> {
    return this.http.get(this.apiURL + 'fine?cardId=' + cardId);
  }
}
