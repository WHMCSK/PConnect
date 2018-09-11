import { Location }               from '@angular/common';
import {Component, OnInit, enableProdMode} from '@angular/core';
import {TMenu} from "./t-menu";
import {TMenuService} from "./t-menu.service";
import 'rxjs/add/operator/switchMap';
import {Params, ActivatedRoute} from '@angular/router';
enableProdMode();
@Component({
  templateUrl: './t-menu-add.component.html',
})
export class TMenuUpdateComponent implements OnInit {

  msg:string = "";
  tMenu:any = new TMenu();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '菜单管理';
  menuList:any;
  constructor(private tMenuService:TMenuService,
              private route: ActivatedRoute,
              private location:Location) {
  }

  ngOnInit():void {
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => this.tMenuService.getById(+params['id']))
      .subscribe(data => this.tMenu = data.data);
    this.getMenuList();
  }

  getMenuList(){
    this.tMenuService.getList(1,1000).subscribe(data => {
      this.menuList = data.data.list;
    });
  }

  msg_(msg_:string) {
    this.msg = msg_;
  }


  checkMenu(menu:TMenu){
    let result =true;

    if(!menu.code ){
      this.msg = '编码不能为空';
      result = false;
    }else if(!menu.title ){
      this.msg = '名称不能为空';
      result = false;
    }else if(!menu.href ){
      this.msg = 'href不能为空';
      result = false;
    }else if(!menu.icon ){
      this.msg = 'icon不能为空';
      result = false;
    }else if(!menu.parentId ){
      this.msg = '请选择父菜单';
      result = false;
    }
    return result;
  }
  onSubmit(){
    if(!this.checkMenu(this.tMenu)){
      return;
    }
    this.tMenuService.update(this.tMenu)
      .subscribe(
        data  => {
          if(data.code == 0){
            this.msg = "添加成功";
            setTimeout(() => {this.goBack()},1000);
          }else{
            this.msg = "添加失败";
          }
        },
        error =>  this.errorMessage = <any>error);
  }
  goBack(): void {
    this.location.back();
  }


}

