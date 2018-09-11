import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import {HttpModule, JsonpModule} from "@angular/http";
import {Config} from "../app-config";
import {TUserModule} from "../baseinfo/t-user.module";
import {LoginComponent} from "./login.component";
import {LoginRoutingModule} from "./login-routing.module";
import {LoginService} from "./Login.service";
import {RegisterComponent} from "./register.component";
import {InfoModule} from "../info/info.module";

@NgModule({
  imports: [CommonModule, FormsModule,
    LoginRoutingModule,
    HttpModule,
    JsonpModule,
    TUserModule,
    InfoModule
  ],
  declarations: [LoginComponent,RegisterComponent
     ],
  providers: [
    Config,LoginService
  ]
})
export class LoginModule {

}
