import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MyHeaderComponent } from './header/my-header.component';
import { AsideComponent } from './aside/aside.component';
import { FooterComponent } from './footer/footer.component';


const Frame_Content = [
    MyHeaderComponent,
    AsideComponent,
    FooterComponent
];

@NgModule({
    declarations: [
        ...Frame_Content
    ],
    imports: [
        CommonModule
    ],
    exports: [
        CommonModule,
        ...Frame_Content
    ],
})
export class FrameModule {
    static forRoot(): ModuleWithProviders {
        return <ModuleWithProviders>{
            ngModule: FrameModule,
            providers: [
                
            ],
        };
    }
}
