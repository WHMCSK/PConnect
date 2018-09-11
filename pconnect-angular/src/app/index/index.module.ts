import {NgModule, ModuleWithProviders}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {FormsModule}   from '@angular/forms';


import {IndexComponent}        from './index.component';
import {IndexRoutingModule} from "./index-routing.module";
import { HttpModule, JsonpModule } from '@angular/http';
import {Config} from "../app-config";

// import {TUserModule} from "../baseinfo/t-user.module";
import { KNWCenterModule } from '../knwcenter/knwcenter.module';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { FrameModule } from '../frame/frame.module';


@NgModule({
  imports: [CommonModule, FormsModule,
    BsDropdownModule.forRoot(),
    IndexRoutingModule,
    HttpModule,
    JsonpModule,
    // TUserModule,
    KNWCenterModule,FrameModule
  ],
  declarations: [IndexComponent,
     ],
  providers: [
    Config
  ],
  exports: [IndexComponent]
})
export class IndexModule {

}
