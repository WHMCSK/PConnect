import { Component,OnInit } from '@angular/core';
import { Config } from '../../app-config';
import {TMenuService} from "../../baseinfo/t-menu.service";
import {TUserService} from "../../baseinfo/t-user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'pconnect-aside',
  templateUrl: './aside.component.html',
  styleUrls: ['./aside.component.css']
})
export class AsideComponent implements OnInit {
  treeMenu:any[];
  app:any;
  selectedFirstMenuId:any;
  selectedSecondMenuId:any;
  selectedThirdMenuId:any;
  username:string;
  flag : boolean;

  constructor(private config:Config,
              private router:Router,
              private tMenuService:TMenuService,
              private tUserService:TUserService){
    this.app = config.appConfig;
    this.flag=false;
  }
  ngOnInit():void {
    this.selectedFirstMenuId = sessionStorage.getItem("firstMenu");
    this.selectedSecondMenuId = sessionStorage.getItem("secondMenu");
    this.selectedThirdMenuId = sessionStorage.getItem("thirdMenu");
    this.tUserService.getSessionInfo().subscribe(data =>{
      this.tUserService.setLocalSessionInfo(data.data);
      this.username = data.data.username;
      //this.getUserMenu();
      this.tUserService.getAuthorityByUsername(this.username).subscribe(data =>{
        this.treeMenu = data.data.tMenuTrees;
        this.selectMenuList();
        this.tUserService.setLocalAuthorityTElements(data.data.tElements);
      });
    });
  }
  selectFirstMenu(menu:any){
    menu.isOpen = !menu.isOpen;
    if(menu.isOpen){
      this.selectedFirstMenuId = menu.id;
      sessionStorage.setItem("firstMenu",this.selectedFirstMenuId);
    }else{
      this.selectedFirstMenuId = null;
      sessionStorage.setItem("firstMenu",null);
    }
    this.treeMenu.forEach(menu=>{
      if(menu.id != this.selectedFirstMenuId){
        menu.isOpen = false;
      }
    })
  }
  selectSecondMenu(menu:any){
    menu.isOpen = !menu.isOpen;
    if(menu.isOpen){
      this.selectedSecondMenuId = menu.id;
      sessionStorage.setItem("secondMenu",this.selectedSecondMenuId);
    }else{
      this.selectedFirstMenuId = null;
      sessionStorage.setItem("secondMenu",null);
    }
  }
  selectThirdMenu(menu:any,href:any){
    this.selectedThirdMenuId=menu.id;
    sessionStorage.setItem("thirdMenu",this.selectedThirdMenuId);
    console.log(href,"+++++++");
    this.router.navigate([href]);
  }
  toggle(){
    this.flag = !this.flag;
  }

  getUserMenu():void {
    this.tMenuService.getTree().subscribe(data => {
      this.treeMenu = data.data;
      this.selectMenuList();
    });
  }

  selectMenuList():void {
    if(this.treeMenu && this.treeMenu.length>0 ){
      if(!this.selectedFirstMenuId){
        this.selectedFirstMenuId = this.treeMenu[0].id;
      }
    }
    for(let i=0;i<this.treeMenu.length;i++){
      if(this.treeMenu[i].id==this.selectedFirstMenuId){
        this.treeMenu[i].isOpen = true;
        let secondMenus = this.treeMenu[i].children;
        secondMenus.forEach(menu=>{
          if(menu.id==this.selectedSecondMenuId){
            menu.isOpen = true;
            let thirdMenus = menu.children;
            thirdMenus.forEach(menu=>{
              menu.isOpen = true;
            })
          }
        });
      }
    }
  }
}
