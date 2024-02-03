import { Component, OnInit } from '@angular/core';
import { Member } from '../../model/member';
import { LibraryService } from '../../services/library.services';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'member-component',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})

export class MemberComponent implements OnInit {

  memberForm: FormGroup;
  member: Member;
  message: string;
  errorMsg: string;
  submitted = false;
  success = false;
  constructor(
    private libraryService: LibraryService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.memberForm = this.formBuilder.group({
      name: ['', Validators.required],
      dni: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', Validators.required]
    });
  }

  addMember() {
    this.submitted = false;
    if (!this.memberForm.valid) {
      return;
    }

    let b = new Member();
    b.name = this.memberForm.value.name;
    b.address = this.memberForm.value.address;
    b.phone = this.memberForm.value.phone;
    b.dni = this.memberForm.value.dni;

    this.libraryService.addMember(b).subscribe(x => {
      this.success = true;
      this.submitted = true;
      this.message = 'Added succesfully';
    }, e => {
      this.success = false;
      this.submitted = true;
      this.errorMsg = e.error.message;
    });
  }
}
