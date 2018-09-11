import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {FormsModule}   from '@angular/forms';

import {Config} from "../app-config";
import {GlossaryService} from "./components/glossary/glossary.service";
import {KNWCenterRoutingModule} from "./knwcenter-routing.module";

import {GlossaryListComponent} from "./components/glossary/glossary-list.component";
import {PaginationModule} from "ngx-bootstrap/index";
import {GlossaryAddComponent} from "./components/glossary/glossary-add.component";
import {GlossaryUpdateComponent} from "./components/glossary/glossary-update.component";

import { AmexioWidgetModule,CommonHttpService } from 'amexio-ng-extensions';
import {InfoModule} from "../info/info.module"; 
import { FrameModule } from '../frame/frame.module';
import { TestComponent } from './components/glossary/test.component';
import { KNWCenterComponent } from './knwcenter.component';

@NgModule({
  imports: [
    FrameModule,
    CommonModule,
    FormsModule,
    KNWCenterRoutingModule,
    PaginationModule.forRoot(),
    AmexioWidgetModule,
    InfoModule],
  declarations: [
    KNWCenterComponent,
    TestComponent,
    GlossaryListComponent,
    GlossaryAddComponent,
    GlossaryUpdateComponent,
  ],
  providers: [
    Config,
    GlossaryService,
    CommonHttpService
  ]
})
export class KNWCenterModule {

}
