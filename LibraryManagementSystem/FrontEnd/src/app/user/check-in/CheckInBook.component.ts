import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../../services/library.services';
import { ActivatedRoute, Params } from '@angular/router';
import { CheckInBook } from '../../model/check-in-book';

@Component({
  selector: 'checkIn-book',
  templateUrl: './checkInBook.component.html',
  styleUrls: ['./checkInBook.component.css']
})
export class CheckInBookComponent implements OnInit {

  isbn: string;
  memberId: string;
  success: boolean;
  submitted: boolean;
  message: string;
  errorMsg: string;
  constructor(private route: ActivatedRoute,
    private libraryService: LibraryService) {
  }


  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.isbn = params['isbn']);
    this.route.params.subscribe((params: Params) => this.memberId = params['memberId']);
  }

  checkInBook(): void {
    this.submitted = false;
    const checkIn = new CheckInBook();
    checkIn.isbn = this.isbn;
    checkIn.cardId = this.memberId;
    this.libraryService.checkIn(checkIn).subscribe(x => {
      this.submitted = true;
      this.success = true;
      this.message = x.message;
    }, e => {
      this.submitted = true;
      this.success = false;
      this.message = e.error.message;
    });
  }
}
