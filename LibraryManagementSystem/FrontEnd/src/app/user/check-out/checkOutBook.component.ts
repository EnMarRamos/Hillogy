import {Component, OnInit} from '@angular/core';
import {BookLoan} from '../../model/book-loan';
import {LibraryService} from '../../services/library.services';
import {ActivatedRoute, Params} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'checkout-book',
  templateUrl: './checkOutBook.component.html',
  styleUrls: ['./checkOutBook.component.css']
})
export class CheckOutBookComponent implements OnInit {

  bookLoan = new BookLoan('', '');
  isbn: string;
  message: string;
  errorMsg: string;
  success: boolean;
  submitted: boolean;
  addLoanForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private libraryService: LibraryService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.addLoanForm = this.formBuilder.group({
      memberId: ['', Validators.required]
    });
    this.route.params.subscribe((params: Params) => this.isbn = params['isbn']);
  }


  addLoan(): void {
    this.submitted = false;
    this.bookLoan.isbn = this.isbn;
    this.bookLoan.memberId = this.addLoanForm.value.memberId;
    this.libraryService.addLoan(this.bookLoan).subscribe(x => {
      this.submitted = true;
      this.success = true;
      this.message = x.message;
    }, e => {
      this.submitted = true;
      this.success = false;
      this.errorMsg = e.error.message;
    });

  }
}
