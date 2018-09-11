import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TUserListComponent} from "./t-user-list.component";
import {IndexComponent} from "../index/index.component";
import {TUserAddComponent} from "./t-user-add.component";
import {TUserUpdateComponent} from "./t-user-update.component";
import {TElementAddComponent} from "./t-element-add.component";
import {TElementUpdateComponent} from "./t-element-update.component";
import {TMenuUpdateComponent} from "./t-menu-update.component";
import {TMenuAddComponent} from "./t-menu-add.component";
import {TMenuListComponent} from "./t-menu-list.component";
import {TGroupTypeListComponent} from "./t-group-type-list.component";
import {TGroupTypeUpdateComponent} from "./t-group-type-update.component";
import {TGroupTypeAddComponent} from "./t-group-type-add.component";
import {TGroupListComponent} from "./t-group-list.component";
import {TGroupAddComponent} from "./t-group-add.component";
import {TGroupUpdateComponent} from "./t-group-update.component";
import {TGroupAddUserComponent} from "./t-group-add-user.component";
import {TGroupAuthorityComponent} from "./t-group-authority.component";
import {TDictComponent} from "./dict/t-dict.component";
import {TDictUpdateComponent} from "./dict/t-dict-update.component";
import {TDictAddComponent} from "./dict/t-dict-add.component";
import {TUserLogComponent} from "./userLog/t-user-log.component"; 
import {DashboardComponent} from "./dashboard.component"; 
import { TestComponent } from '../knwcenter/components/glossary/test.component';

const routes: Routes = [
  {path: 'privilege',  component: IndexComponent,children: [
    {
      path: 'tUserList',
      component: TUserListComponent
    },
    {
      path: 'tUserAdd',
      component: TUserAddComponent
    },
    {
      path: 'tUserUpdate/:id',
      component: TUserUpdateComponent
    },
    {
      path: 'tElementAdd/:id',
      component: TElementAddComponent
    },
    {
      path: 'tElementUpdate/:id',
      component: TElementUpdateComponent
    },
    {
      path: 'tMenuList',
      component: TMenuListComponent
    },
    {
      path: 'tMenuAdd',
      component: TMenuAddComponent
    },
    {
      path: 'tMenuUpdate/:id',
      component: TMenuUpdateComponent
    },
    {
      path: 'tGroupTypeList',
      component: TGroupTypeListComponent
    },
    {
      path: 'tGroupTypeUpdate/:id',
      component: TGroupTypeUpdateComponent
    },
    {
      path: 'tGroupTypeAdd',
      component: TGroupTypeAddComponent
    },
    {
      path: 'tGroupList',
      component: TGroupListComponent
    },
    {
      path: 'tGroupAdd/:id',
      component: TGroupAddComponent
    },
    {
      path: 'tGroupUpdate/:id',
      component: TGroupUpdateComponent
    },
    {
      path: 'tGroupAddUser/:id',
      component: TGroupAddUserComponent
    },
    {
      path: 'tGroupAuthority/:id',
      component: TGroupAuthorityComponent
    },
    {
      path: 'tDictList',
      component: TDictComponent
    },
    {
      path: 'tDictUpdate/:id',
      component: TDictUpdateComponent
    },
    {
      path: 'tDictAdd',
      component: TDictAddComponent
    },
    {
      path: 'tUserLogList',
      component: TUserLogComponent 
    },
    {
      path: 'dashboard',
      component: DashboardComponent
    },
    { path: '**',
      redirectTo: '/privilege/dashboard',
      pathMatch: 'full' 
    }
  ]},
  {path: 'knwcenter',  component: IndexComponent,children: [
    {
      path: '',
      loadChildren: '../knwcenter/knwcenter.module#KNWCenterModule'
    },
  ]},
  {path: 'prsncenter',  component: IndexComponent,children: [
    {
      path: '',
      loadChildren: '../prsncenter/prsncenter.module#PRSNCenterModule'
    },
  ]},
  { path: '**',
    redirectTo: '/privilege/dashboard',
    pathMatch: 'full'
  }
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class TUserRoutingModule {}
