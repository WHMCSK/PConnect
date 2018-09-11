/**
 * Created by F1 on 2017/5/28.
 */
import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  // { path: 'index', loadChildren: './index/index.module#IndexModule' },
  { path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  // { path: '**', redirectTo: 'index' },
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]

})
export class AppRoutingModule {}
