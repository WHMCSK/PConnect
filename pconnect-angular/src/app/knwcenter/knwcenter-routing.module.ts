import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {GlossaryListComponent} from "./components/glossary/glossary-list.component";
import {IndexComponent} from "../index/index.component";
import {GlossaryAddComponent} from "./components/glossary/glossary-add.component";
import {GlossaryUpdateComponent} from "./components/glossary/glossary-update.component";
import { TestComponent } from './components/glossary/test.component';
import { KNWCenterComponent } from './knwcenter.component';

console.log("routej88iii");

const routes: Routes = [{
  path: '',
  component: KNWCenterComponent,
  children: [
    {
      path: 'tGlossaryList',
      component: GlossaryListComponent
    },
    {
      path: 'tGlossaryAdd',
      component: GlossaryAddComponent
    },
    {
      path: 'tGlossaryUpdate/:id',
      component: GlossaryUpdateComponent
    },
  ]
}];
@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class KNWCenterRoutingModule {}
