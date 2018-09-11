/**
 * Created by F1 on 2017/6/1.
 */
import {Component, OnInit, enableProdMode} from "@angular/core";
import {Router} from "@angular/router";
import {TUserService} from "./t-user.service";
import {TUser} from "./t-user";
import {PageChangedEvent} from "ngx-bootstrap/pagination/pagination.component";
enableProdMode();//阻止报错：Expression has changed after it was checked
@Component({
  templateUrl: './t-user-list.component.html',
})
export class TUserListComponent implements OnInit {
  msg: string;
  userList:TUser[];
  selectedUser:TUser = new TUser();
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '基础配置';
  secondName:string = '用户管理';

  /**分页用到的参数**/
  pageSize:number = 2;
  maxSize:number = 5;
  totalItems:number;
  currentPage:number = 1;

  authorityTElements:any[];

  editButton:boolean=false;
  deleteButton:boolean=false;
  addButton:boolean=false;
  viewButton:boolean=false;

  constructor(private router:Router,
              private tUserService:TUserService) {
  }

  ngOnInit():void {
    this.authorityTElements = this.tUserService.getLocalAuthorityTElements();
    this.authorityTElements.forEach((tElement) =>{
      if(tElement.code == 'userManager:view'){
        this.viewButton=true;
      }else if(tElement.code == 'menuManager:btn_add'){
        this.addButton = true;
      }else if(tElement.code == 'menuManager:btn_edit'){
        this.editButton = true;
      }else if(tElement.code == 'menuManager:btn_del'){
        this.deleteButton = true;
      }
    })
    if(this.viewButton){
      this.getUserList();
    }
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }
  pageChanged(page:PageChangedEvent) {
    this.currentPage = page.page;
    this.pageSize = page.itemsPerPage;
    this.getUserList();
  }

  numPages(numPages:number) {
    console.log(numPages);
  }



  onSelect(user:TUser):void {
    this.selectedUser = user;
  }

  getUserList():void {
    this.tUserService.getList(this.currentPage,this.pageSize).subscribe(data =>{
      this.userList = data.data.list;
      this.totalItems = data.data.total;
    })
   }

  delete():void {
    if(!this.selectedUser.id){
      this.msg = "请选择用户信息";
      return;
    }
    if(window.confirm('你确定要删除记录吗？')){
      this.tUserService.delete(this.selectedUser.id).subscribe(data => {
        if(data.code==0){
          this.msg = "删除成功";
          this.getUserList();
        }else{
          this.msg = "删除失败";
        }
      });
    }
  }

  edit(){
    if(!this.selectedUser.id){
      this.msg = "请选择用户信息";
    }else{
      this.router.navigate(['/privilege/tUserUpdate',this.selectedUser.id]);
    }
  }

}
