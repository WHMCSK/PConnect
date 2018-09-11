/**
 * Created by F1 on 2017/6/1.
 */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {TGroupTypeService} from "./t-group-type.service";
import {TGroupService} from "./t-group.service";
import {PageChangedEvent} from "ngx-bootstrap/pagination/pagination.component";
import {TGroup} from "./t-group";
import {TUserService} from "./t-user.service";

@Component({
  templateUrl: 't-group-list.component.html'
})
export class TGroupListComponent implements OnInit {
  msg:string;
  groupTypeList:any[];
  selectedGroupTypeId:any;
  pageSize:number = 8;
  totalItems:number;
  currentPage:number = 1;
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '基础配置';
  secondName:string = '组织架构管理';
  maxSize:number = 5;
  groupList:any[];

  selectedGroup:TGroup = new TGroup();

  authorityTElements:any[];
  editButton:boolean=false;
  deleteButton:boolean=false;
  addButton:boolean=false;
  viewButton:boolean=false;
  groupAuthorityButton:boolean=false;
  groupAddUserButton:boolean=false;

  constructor(private router:Router,
              private tGroupTypeService:TGroupTypeService,
              private tUserService:TUserService,
              private tGroupService:TGroupService) {
  }

  ngOnInit():void {
    this.authorityTElements = this.tUserService.getLocalAuthorityTElements();
    this.authorityTElements.forEach((tElement) =>{
      if(tElement.code == 'groupManager:view'){
        this.viewButton=true;
      }else if(tElement.code == 'groupManager:btn_add'){
        this.addButton = true;
      }else if(tElement.code == 'groupManager:btn_edit'){
        this.editButton = true;
      }else if(tElement.code == 'groupManager:btn_del'){
        this.deleteButton = true;
      }else if(tElement.code == 'groupManager:btn_resourceManager'){
        this.groupAuthorityButton = true;
      }else if(tElement.code == 'groupManager:btn_userManager'){
        this.groupAddUserButton = true;
      }

    })
    if(this.viewButton) {
      this.getGrupTypeByPage();
    }
  }

  msg_(msg_:string) {
    this.msg = msg_;
  }
  pageChanged(page:PageChangedEvent) {
    this.currentPage = page.page;
    this.pageSize = page.itemsPerPage;
    this.selectedGroupType(this.selectedGroupTypeId);
  }

  numPages(numPages:number) {
   // console.log(numPages);
  }

  getGrupTypeByPage() {
    this.tGroupTypeService.getList(1,1000).subscribe(data => {
      this.groupTypeList = data.data.list;
      this.totalItems = data.data.total;
      this.selectedGroupTypeId = this.groupTypeList[0].id;
      this.selectedGroupType(this.selectedGroupTypeId);
    })
  }

  selectedGroupType(groupTypeId:any) {
    this.selectedGroup=new TGroup();
    this.selectedGroupTypeId = groupTypeId;
    this.tGroupService.getListByGroupTypeId(this.selectedGroupTypeId).subscribe(data => {
      this.groupList = data.data;
    });
  }


  delete():void {
    if(!this.selectedGroup.id){
      this.msg = "请选择组织";
      return;
    }
    if(window.confirm('你确定要删除记录吗？')){
      this.tGroupService.delete(this.selectedGroup.id).subscribe(data => {
        if(data.code==0){
          this.msg = "删除成功";
          this.getGrupTypeByPage();
        }else{
          this.msg = "删除失败";
        }
      });
    }
  }

  edit(){
    if(!this.selectedGroup.id){
      this.msg = "请选择组织";
    }else{
      this.router.navigate(['/privilege/tGroupUpdate', this.selectedGroup.id]);
    }
  }

  add(){
    this.router.navigate(['/privilege/tGroupAdd', this.selectedGroupTypeId]);
  }

  groupAddUser(){
    if(!this.selectedGroup.id){
      this.msg = "请选择组织";
    }else{
      this.router.navigate(['/privilege/tGroupAddUser', this.selectedGroup.id]);
    }
  }

  groupAuthority(){
    if(!this.selectedGroup.id){
      this.msg = "请选择组织";
    }else{
      this.router.navigate(['/privilege/tGroupAuthority', this.selectedGroup.id]);
    }
  }

}
