import { Component, OnInit } from '@angular/core';
import { LibraryService } from '../../services/library.services';
import { Fine } from '../../model/fine';
import { Router, ActivatedRoute } from '@angular/router';

@Component({

  selector: 'fine',
  templateUrl: './fine.component.html',
  styleUrls: ['./fine.component.css']
})

export class FineComponent {

  constructor() { }

  // pay(cardId: number) {
  //   this.router.navigate(['/pay', cardId]);
  // }

}
