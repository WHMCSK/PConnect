/**
 * Created by F1 on 2017/6/1.
 */
import {Component, OnInit, enableProdMode} from "@angular/core";
import {Router} from "@angular/router";
import {TMenuService} from "./t-menu.service";
import {TElementService} from "./t-element.service";
import {TMenu} from "./t-menu";
import {TElement} from "./t-element";
import {TUserService} from "./t-user.service";
enableProdMode();
@Component({
  templateUrl: './t-menu-list.component.html',
})
export class TMenuListComponent implements OnInit {
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
  secondName:string = '菜单管理';
  maxSize:number = 5;

  selectedMenu : TMenu = new TMenu();

  selectedElement:TElement = new TElement();

  authorityTElements:any[];
  editTMenuButton:boolean=false;
  deleteTMenuButton:boolean=false;
  addTMenuButton:boolean=false;
  viewTMenuButton:boolean=false;

  editTElementButton:boolean=false;
  deleteTElementButton:boolean=false;
  addTElementButton:boolean=false;
  viewTElementButton:boolean=false;

  constructor(private router:Router,
              private tMenuService:TMenuService,
              private tUserService:TUserService,
              private tElementService:TElementService) {
  }

  ngOnInit():void {
    this.authorityTElements = this.tUserService.getLocalAuthorityTElements();
    console.log("this.authorityTElements",this.authorityTElements);
    this.authorityTElements.forEach((tElement) =>{
      if(tElement.code == 'menuManager:view'){
        this.viewTMenuButton=true;
      }else if(tElement.code == 'menuManager:btn_add'){
        this.addTMenuButton = true;
      }else if(tElement.code == 'menuManager:btn_edit'){
        this.editTMenuButton = true;
      }else if(tElement.code == 'menuManager:btn_del'){
        this.deleteTMenuButton = true;
      }

      if(tElement.code == 'menuManager:element_view'){
        this.viewTElementButton=true;
      }else if(tElement.code == 'menuManager:btn_element_add'){
        this.addTElementButton = true;
      }else if(tElement.code == 'menuManager:btn_element_edit'){
        this.editTElementButton = true;
      }else if(tElement.code == 'menuManager:btn_element_del'){
        this.deleteTElementButton = true;
      }
    })
    if(this.viewTMenuButton){
      this.getList();
    }
  }

  msg_(msg_:string) {
    this.msg = msg_;
  }

   selectedMenu1(menu:any) {
     this.selectedMenu = menu;
     this.getElementByMenuId(this.selectedMenu.id);
     this.selectedElement = new TElement();
   }

  getList(){
    this.tMenuService.getTree().subscribe(data => {
      this.menuTree = data.data;
      this.menuTree.forEach((menu) =>{
        menu.parentId = null;
      });
    })
  }

  updateMenu(){
    if(!this.selectedMenu.id){
      this.msg = "请选择菜单";
    }else{
      this.router.navigate(['/privilege/tMenuUpdate', this.selectedMenu.id]);
    }
  }
  deleteMenu(){
    if(!this.selectedMenu.id){
      this.msg = "请选择菜单";
      return;
    }
    if(window.confirm('你确定要删除记录吗？')){
      this.tMenuService.delete(this.selectedMenu.id).subscribe(data => {
        if(data.code==0){
          this.msg = "删除成功";
          this.getList();
        }else{
          this.msg = "删除失败";
        }
      });
    }
  }

  updateElement(){
    if(!this.selectedElement.id){
      this.msg = "请选择元素";
    }else{
      this.router.navigate(['/privilege/tElementUpdate', this.selectedElement.id]);
    }
  }

  addElement(){
    if(!this.selectedMenu.id){
      this.msg = "请选择菜单";
    }else{
      this.router.navigate(['/privilege/tElementAdd', this.selectedMenu.id]);
    }
  }

  deleteElement(){
    if(!this.selectedElement.id){
      this.msg = "请选择元素";
      return;
    }
    if(window.confirm('你确定要删除记录吗？')){
      this.tElementService.delete(this.selectedElement.id).subscribe(data => {
        if(data.code==0){
          this.msg = "删除成功";
          this.getElementByMenuId(this.selectedMenu.id);
        }else{
          this.msg = "删除失败";
        }
      });
    }
  }

  getElementByMenuId(menuId:number){
    this.tElementService.getByMenuId(menuId).subscribe(data => {
      this.elementList = data.data;
    })
  }




}
