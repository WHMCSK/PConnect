/**
 * Created by F1 on 2017/6/1.
 */
import {Component, OnInit,  enableProdMode} from '@angular/core';
import {Router, Params, ActivatedRoute} from '@angular/router';
import {TMenuService} from './t-menu.service';
import {TElementService} from "./t-element.service";
import {TMenu} from "./t-menu";
import {TElement} from "./t-element";
import {TGroup} from "./t-group";
import {TGroupService} from "./t-group.service";
import {TAuthorityService} from "./t-authority.service";
import {element} from "protractor/built/index";
enableProdMode();
@Component({
  templateUrl: './t-group-authority.component.html',
})
export class TGroupAuthorityComponent implements OnInit {
  msg:string;
  menuList:any[];
  menuTree:any[];
  elementList:any[];
  pageSize:number = 8;
  totalItems:number;
  currentPage:number = 1;
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '基础配置';
  secondName:string = '组织架构管理';
  maxSize:number = 5;
  selectedMenu : TMenu = new TMenu();
  selectedElement:TElement = new TElement();
  tGroup:TGroup = new TGroup();
  selectedAll:boolean;

  /**选中的树形菜单**/
  treeSelected:any[];
  /**选中的元素**/
  selectedElementIds:number[] = [];
  /**选中的元素**/
  selectedMenuIds:number[] = [];


  constructor(private router:Router,
              private tMenuService:TMenuService,
              private tElementService:TElementService,
              private tGroupService:TGroupService,
              private tAuthorityService:TAuthorityService,
              private route: ActivatedRoute) {
  }

  ngOnInit():void {
    //this.getMenuList();

    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => {
        return this.tGroupService.getById(+params['id'])
      })
      .subscribe(data => {
        this.tGroup = data.data;
        this.tMenuService.getTree().subscribe(data => {
          this.menuTree = data.data;
          this.tAuthorityService.getAuthority(this.tGroup.id).subscribe(data =>{
            this.selectedElementIds = data.data.elementIds;
            this.selectedMenuIds = data.data.menuIds;
            this.setText(this.menuTree,data.data.menuIds);
            this.treeSelected = this.menuTree;
          })
        })
      });
  }


  private setText(nodeTree:any[],menuIds:any[]) {
    nodeTree.forEach((node) =>{
      node.text = node.title;
      menuIds.forEach((menuId)=>{
        if(menuId==node.id){
          node.checked = true;
        }
      })
      if(node.children && node.children.length>0){
        this.setText(node.children,menuIds);
      }
    })
  }

  selectedAllElement(){
    this.selectedAll = !this.selectedAll;
    if(this.selectedAll){
      this.elementList.forEach((element) => {
        let flag = false;
        for(let id of this.selectedElementIds){
          if(id==element.id){
            flag =true;
          }
        }
        if(!flag){
          this.selectedElementIds.push(element.id);
        }
      })
    }else{
      this.elementList.forEach((element) => {
        this.selectedElementIds = this.selectedElementIds.filter(id => id!=element.id);
      });
    }
    this.setCheckedElements();
  }

  selectedElement_(element:any){
    element.selected = !element.selected;
    if(element.selected){
      this.selectedElementIds.push(element.id);
    }else{
      this.selectedElementIds = this.selectedElementIds.filter(elementId => elementId!=element.id);
    }
  }

  onCheckboxSelect(data){
    this.treeSelected=data;
  }

  selectedRecord(data){
    this.selectedMenu = data;
    this.getElementByMenuId(this.selectedMenu.id);
    this.selectedElement = new TElement();
  }


  msg_(msg_:string) {
    this.msg = msg_;
  }

  addGroupAuthority(){
    this.selectedMenuIds = [];
    this.treeSelected.forEach((menu) => {
      if(menu.checked){
        this.selectedMenuIds.push(menu.id);
      }
      if(menu.children && menu.children.length>0){
         this.setChildrenChecked(this.selectedMenuIds,menu.children);
      }
    });
    let param = {menuIds:[],elementIds:[]};
    param.menuIds = this.selectedMenuIds;
    param.elementIds = this.selectedElementIds;
    console.log(JSON.stringify(param));
    this.tAuthorityService.authority(param,this.tGroup.id).subscribe(data => {
      if(data.code == 0){
        this.msg = "授权成功";
      }else{
        this.msg = "授权失败";
      }
    });
  }


  private setChildrenChecked(selectedMenuIds:any[], children:any[]):void {
    children.forEach((menu) => {
      if(menu.checked){
        selectedMenuIds.push(menu.id);
      }
      if(menu.children && menu.children.length>0){
        this.setChildrenChecked(selectedMenuIds,menu.children);
      }
    })
  }

   selectedMenu1(menu:any) {
     this.selectedMenu = menu;
     this.getElementByMenuId(this.selectedMenu.id);
     this.selectedElement = new TElement();
   }

  getElementByMenuId(menuId:number){
    this.selectedAll = false;
    this.tElementService.getByMenuId(menuId).subscribe(data => {
      this.elementList = data.data;
      this.selectedAll = this.setCheckedElements();
    })
  }

  setCheckedElements(){
    let selectedAllFlag = true;
    this.elementList.forEach((element) => {
      let flag = false;
      this.selectedElementIds.forEach((id) => {
        if(element.id==id){
          element.selected = true;
          flag = true;
        }
      })
      if(!flag){
        element.selected = false;
        selectedAllFlag = false;
      }
    })
    return selectedAllFlag;
  }

}
