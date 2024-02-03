import { NgModule } from '@angular/core';
import { FineComponent } from './fine/fine.component';
import { MemberComponent } from './member/member.component';
import { SharedModule } from '../shared/shared.module';
import { FineDetailComponent } from './fine/fine-detail/fine-detail.component';
import { RouterModule } from '@angular/router';
import { CalculateFineComponent } from './fine/calculate-fine/calculate-fine.component';
import { PayFineComponent } from './fine/pay-fine/pay-fine.component';
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  imports: [SharedModule, RouterModule, MatButtonModule, MatInputModule],
  declarations: [FineComponent, MemberComponent, FineDetailComponent, CalculateFineComponent, PayFineComponent]
})

export class OperationModule { }
