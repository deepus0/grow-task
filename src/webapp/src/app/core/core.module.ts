import {NgModule} from '@angular/core';
import {LoginComponent} from '../feature/login/page/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {PreloginComponent} from './layout/prelogin/prelogin.component';
import {PostloginComponent} from './layout/postlogin/postlogin.component';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {SharedModule} from '../shared/shared.module';
import {RouterModule} from '@angular/router';
import { CallbackComponent } from './layout/callback/callback.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    LoginComponent,
    PreloginComponent,
    PostloginComponent,
    CallbackComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    SharedModule,
    RouterModule,
  ],
  providers: []
})
export class CoreModule {
}
