import { Component, OnInit } from '@angular/core';
import { LibraryService } from 'src/app/services/library.services';

@Component({
  selector: 'app-calculate-fine',
  templateUrl: './calculate-fine.component.html',
  styleUrls: ['./calculate-fine.component.css']
})
export class CalculateFineComponent implements OnInit {

  success: boolean;
  message: boolean;
  error: boolean;
  constructor(private libraryService: LibraryService) { }

  ngOnInit() {
    this.success = false;
    this.libraryService.calculateFines().subscribe(x => {
      this.success = true;
      this.message = x.message;
    }, e => {
      this.success = false;
      this.error = e.error.message;
    });
  }

}
