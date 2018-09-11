import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {FormsModule}   from '@angular/forms';

import {Config} from "../app-config";
import {TodoService} from "./components/todo/todo.service";
import {PRSNCenterRoutingModule} from "./prsncenter-routing.module";

import {TodoListComponent} from "./components/todo/todo-list.component";
import {PaginationModule} from "ngx-bootstrap/index";
import {TodoAddComponent} from "./components/todo/todo-add.component";
import {TodoUpdateComponent} from "./components/todo/todo-update.component";

import { AmexioWidgetModule,CommonHttpService } from 'amexio-ng-extensions';
import {InfoModule} from "../info/info.module"; 
import { FrameModule } from '../frame/frame.module';
import { PRSNCenterComponent } from './prsncenter.component';

@NgModule({
  imports: [
    FrameModule,
    CommonModule,
    FormsModule,
    PRSNCenterRoutingModule,
    PaginationModule.forRoot(),
    AmexioWidgetModule,
    InfoModule],
  declarations: [
    PRSNCenterComponent,
    TodoListComponent,
    TodoAddComponent,
    TodoUpdateComponent,
  ],
  providers: [
    Config,
    TodoService,
    CommonHttpService
  ]
})
export class PRSNCenterModule {

}
