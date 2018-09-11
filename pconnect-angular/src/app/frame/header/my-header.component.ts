import { Component,OnInit } from '@angular/core';
import { Config } from '../../app-config';
import {TUserService} from "../../baseinfo/t-user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'my-header',
  templateUrl: './my-header.component.html'
})

export class MyHeaderComponent  implements OnInit{
  app:any;
  username:string;

  sidebar:boolean=true;
  constructor(private config:Config,
              private router: Router,
              private tUserService:TUserService){
    this.app = config.appConfig;
    this.tUserService.getSessionInfo().subscribe(data =>{
      if( data.code==0 && data.data && data.data.username){
        this.tUserService.setLocalSessionInfo(data.data);
        this.username = data.data.username;
      }else{
        top.location.href = "/";
      }
    });
  }

  ngOnInit():void {
  }

  logout(){
    this.tUserService.logout().subscribe(data =>{
      console.log("logout:",data);
      top.location.href = "/";
    });
  }

  dropdownToggle(){
    this.sidebar = !this.sidebar;
    if(this.sidebar){
      document.getElementsByTagName("body").item(0).classList.remove('sidebar-collapse');
    }else{
      document.getElementsByTagName("body").item(0).classList.add('sidebar-collapse');
    }

  }
}
