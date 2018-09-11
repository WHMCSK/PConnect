import { Location }               from '@angular/common';
import {Component, OnInit, enableProdMode} from '@angular/core';
import {TElement} from "./t-element";
import {TElementService} from "./t-element.service";
import 'rxjs/add/operator/switchMap';
import {Params, ActivatedRoute} from '@angular/router';
import {TMenu} from "./t-menu";
import {TMenuService} from "./t-menu.service";
enableProdMode();
@Component({
  templateUrl: './t-element-add.component.html',
})
export class TElementAddComponent implements OnInit {

  msg:string = "";
  tElement:any = new TElement();
  data:any;
  errorMessage:any;
  firstName:string = '基础配置';
  secondName:string = '菜单管理';
  tMenu:TMenu;
  menuList:any;
  constructor(private tElementService:TElementService,
              private tMenuService:TMenuService,
              private route: ActivatedRoute,
              private location:Location) {
  }

  ngOnInit():void {
    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => this.tMenuService.getById(+params['id']))
      .subscribe(data => {
        this.tMenu = data.data;
        this.tElement.menuId = this.tMenu.id;
      });
    this.getMenuList();
    this.tElement.type = 'button';
    this.tElement.method = 'GET';
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }

  getMenuList(){
    this.tMenuService.getList(1,1000).subscribe(data => {
      this.menuList = data.data;
    });
  }

  checkElement(element:TElement){
    let result =true;

    if(!element.code ){
      this.msg = '编码不能为空';
      result = false;
    }else if(!element.type ){
      this.msg = '类型不能为空';
      result = false;
    }else if(!element.name ){
      this.msg = '名称不能为空';
      result = false;
    }else if(!element.uri ){
      this.msg = 'uri不能为空';
      result = false;
    }else if(!element.menuId ){
      this.msg = '请选择菜单';
      result = false;
    }
    return result;
  }
  onSubmit(){
    if(!this.checkElement(this.tElement)){
      return;
    }
    this.tElementService.add(this.tElement)
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

