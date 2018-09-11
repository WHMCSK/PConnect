/**
 * Created by F1 on 2017/6/8.
 */
import {Component, OnInit} from '@angular/core';
import {Config} from "./app-config";
import {Router} from "@angular/router";
import {TUserService} from "./baseinfo/t-user.service";

@Component({
  selector: 'my-app',
  template: `
    <router-outlet></router-outlet>
`
})

export class AppComponent implements  OnInit{
  app:any;
  constructor(private router:Router,
              private config:Config,
              private tUserService:TUserService){
    this.app = config.appConfig;
  };
  ngOnInit(){
    this.checkLogin();
  }
  checkLogin(){
    this.tUserService.getSessionInfo().subscribe(data =>{
      if(data && data.data && data.data.username){
        this.router.navigate(['/index']);
      }
    });
  }

}
