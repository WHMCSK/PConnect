import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TodoListComponent} from "./components/todo/todo-list.component";
import {IndexComponent} from "../index/index.component";
import {TodoAddComponent} from "./components/todo/todo-add.component";
import {TodoUpdateComponent} from "./components/todo/todo-update.component";
import { PRSNCenterComponent } from './prsncenter.component';

console.log("routej88iii");

const routes: Routes = [{
  path: '',
  component: PRSNCenterComponent,
  children: [
    {
      path: 'todoList',
      component: TodoListComponent
    },
    {
      path: 'todoAdd',
      component: TodoAddComponent
    },
    {
      path: 'todoUpdate/:id',
      component: TodoUpdateComponent
    },
  ]
}];
@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class PRSNCenterRoutingModule {}
