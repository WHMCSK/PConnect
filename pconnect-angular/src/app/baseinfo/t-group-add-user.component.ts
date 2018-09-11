/**
 * Created by F1 on 2017/6/1.
 */
import {Component, OnInit, enableProdMode} from "@angular/core";
import {Router, Params, ActivatedRoute} from "@angular/router";
import {TUserService} from "./t-user.service";
import {TUser} from "./t-user";
import {PageChangedEvent} from "ngx-bootstrap/pagination/pagination.component";
import {TGroup} from "./t-group";
import {Location} from "@angular/common";
import {TGroupService} from "./t-group.service";
import {TUserGroup} from "./t-user-group";
import {TUserGroupService} from "./t-user-group.service";
enableProdMode();//阻止报错：Expression has changed after it was checked
  
@Component({
  templateUrl: './t-group-add-user.component.html',
})

export class TGroupAddUserComponent implements OnInit {
  msg: string;
  userList:any[];
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '基础配置';
  secondName:string = '组织架构管理';
  tGroup:TGroup = new TGroup();

  /**分页用到的参数**/
  pageSize:number = 2;
  maxSize:number = 5;
  totalItems:number;
  currentPage:number = 1;

  leaders:any[] = [];
  members:any[] = [];
  leaderUserIds:number[]=[];
  memberUserIds:number[]=[];

  tUserGroup:TUserGroup = new TUserGroup();

  constructor(private router:Router,
              private route: ActivatedRoute,
              private tGroupService: TGroupService,
              private tUserService:TUserService,
              private tUserGroupService:TUserGroupService,
              private location:Location) {

    //noinspection TypeScriptValidateTypes
    this.route.params
      .switchMap((params: Params) => {
        return this.tGroupService.getById(+params['id'])
      })
      .subscribe(data => {
        this.tGroup = data.data;
        this.getTUserGroupByGroupId();
      });
  }

  ngOnInit():void {
    this.tUserGroup.type = "leader";
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
    //console.log(numPages);
  }


  getUserList():void {
    this.tUserService.getList(this.currentPage, this.pageSize).subscribe(data => {
      this.userList = data.data.list;
      this.totalItems = data.data.total;
      this.selectGroupUserType();
    });
   }

  goBack(): void {
    this.location.back();
  }


  selectedUser_(user:any){
    user.selected = !user.selected;
    this.tUserGroup.groupId = this.tGroup.id;
    this.tUserGroup.userId = user.id;
    if(user.selected){
      this.tUserGroupService.addByUserIdAndGroupId(this.tUserGroup).subscribe(data =>{
        if(data.code == 0){
          this.msg = "成功";
          this.getTUserGroupByGroupId()
        }else{
          this.msg = "失败";
        }
      });
    }else{
      this.tUserGroupService.deleteByUserIdAndGroupId(this.tUserGroup).subscribe(data=>{
        if(data.code == 0){
          this.msg = "成功";
          this.getTUserGroupByGroupId()
        }else{
          this.msg = "失败";
        }
      })
    }
  }

  getTUserGroupByGroupId() {
    this.tUserService.getListByGroupId(this.tGroup.id).subscribe(data => {

      data.data.leaders.forEach((user) =>{
        this.leaderUserIds.push(user.id);
      });
      data.data.members.forEach((user) =>{
        this.memberUserIds.push(user.id);
      });

      this.getUserList();
    });
  }

  changeUserGroupType(){
    this.selectGroupUserType();
  }

  selectGroupUserType() {
    this.userList.forEach((user) =>{
      user.selected = false;
      if(this.tUserGroup.type=='leader') {
        this.leaderUserIds.forEach((userId) =>{
          if(userId==user.id){
            user.selected = true;
          }
        });
      }else if(this.tUserGroup.type=='member'){
        this.memberUserIds.forEach((userId) =>{
          if(userId==user.id){
            user.selected = true;
          }
        });
      }
    })
  }
}
