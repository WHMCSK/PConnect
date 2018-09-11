import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import {FormsModule}   from '@angular/forms';


import {Config} from "../app-config";
import {TUserService} from "./t-user.service";
import {TUserRoutingModule} from "./t-user-routing.module";

import {TUserListComponent} from "./t-user-list.component";
import {PaginationModule} from "ngx-bootstrap/index";
import {TMenuListComponent} from "./t-menu-list.component";
import {TMenuService} from "./t-menu.service";
import {TElementService} from "./t-element.service";
import {TGroupTypeListComponent} from "./t-group-type-list.component";
import {TGroupTypeService} from "./t-group-type.service";
import {TGroupService} from "./t-group.service";
import {TUserAddComponent} from "./t-user-add.component";
import {TUserUpdateComponent} from "./t-user-update.component";

import { AmexioWidgetModule,CommonHttpService } from 'amexio-ng-extensions';
import {TMenuAddComponent} from "./t-menu-add.component";
import {TMenuUpdateComponent} from "./t-menu-update.component";
import {TElementAddComponent} from "./t-element-add.component";
import {TElementUpdateComponent} from "./t-element-update.component";
import {TGroupTypeUpdateComponent} from "./t-group-type-update.component";
import {TGroupTypeAddComponent} from "./t-group-type-add.component";
import {TGroupListComponent} from "./t-group-list.component";
import {TGroupAddComponent} from "./t-group-add.component";
import {TGroupUpdateComponent} from "./t-group-update.component";
import {TGroupAddUserComponent} from "./t-group-add-user.component";
import {TGroupAuthorityComponent} from "./t-group-authority.component";
import {TAuthorityService} from "./t-authority.service";
import {TUserGroupService} from "./t-user-group.service";
import {TDictComponent} from "./dict/t-dict.component";
import {TDictService} from "./dict/t-dict.service";
import {TDictUpdateComponent} from "./dict/t-dict-update.component";
import {TDictAddComponent} from "./dict/t-dict-add.component";
import {TUserLogComponent} from "./userLog/t-user-log.component";
import {TUserLogService} from "./userLog/t-user-log.service";
import {InfoModule} from "../info/info.module"; 
import {DashboardComponent} from "./dashboard.component"; 

@NgModule({
  imports: [CommonModule, FormsModule,TUserRoutingModule,PaginationModule.forRoot(),AmexioWidgetModule,InfoModule],
  declarations: [TUserListComponent,TUserAddComponent,TUserUpdateComponent,
    TMenuListComponent,TMenuAddComponent,TMenuUpdateComponent,
    TElementAddComponent,TElementUpdateComponent,
    TGroupTypeListComponent,TGroupTypeUpdateComponent,TGroupTypeAddComponent,
    TGroupListComponent,TGroupAddComponent,TGroupUpdateComponent,TGroupAddUserComponent,TGroupAuthorityComponent,
    TDictComponent,TDictUpdateComponent,TDictAddComponent,  
    TUserLogComponent,
    DashboardComponent 
     ],
  providers: [Config,TUserService,TMenuService,TElementService,TGroupTypeService,TGroupService,
    CommonHttpService,TAuthorityService,TUserGroupService,TDictService,TUserLogService
  ]
})
export class TUserModule {

}
