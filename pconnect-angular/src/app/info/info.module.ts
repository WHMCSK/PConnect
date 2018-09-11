import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {InfoComponent} from "../info/info.component";

@NgModule({
  imports: [CommonModule
  ],
  exports:      [ InfoComponent ],
  declarations: [InfoComponent]
})
export class InfoModule {

}
