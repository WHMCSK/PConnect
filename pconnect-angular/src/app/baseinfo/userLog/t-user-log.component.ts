import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {TUserLog} from "./t-user-log";
import {TUserLogService} from "./t-user-log.service";
import {TUserService} from "../t-user.service";
import {PageChangedEvent} from "ngx-bootstrap/pagination/pagination.component";

@Component({
  templateUrl: './t-user-log.component.html',
  styleUrls: ['./t-user-log.component.css']
})
export class TUserLogComponent implements OnInit {
  msg: string;
  userLogList:TUserLog[];
  selectedUserLog:TUserLog = new TUserLog();
  firstText:string = '首页';
  disabled:boolean;
  firstName:string = '基础配置';
  secondName:string = '日志管理';

  /**分页用到的参数**/
  pageSize:number = 10;
  maxSize:number = 5;
  totalItems:number;
  currentPage:number = 1;

  authorityTElements:any[];
  viewButton:boolean=false;
  constructor(private router:Router,
              private tUserService:TUserService,
              private tUserLogService:TUserLogService) { }


  ngOnInit():void {
    this.authorityTElements = this.tUserService.getLocalAuthorityTElements();
    console.log("this.authorityTElements:",this.authorityTElements);
    this.authorityTElements.forEach((tElement) =>{
      if(tElement.code == 'userLogManager:view'){
        this.viewButton=true;
      }
    })
    if(this.viewButton) {
      this.getUserLogList();
    }
  }
  msg_(msg_:string) {
    this.msg = msg_;
  }
  pageChanged(page:PageChangedEvent) {
    this.currentPage = page.page;
    this.pageSize = page.itemsPerPage;
    this.getUserLogList();
  }

  numPages(numPages:number) {
    console.log(numPages);
  }



  onSelect(userLog:TUserLog):void {
    this.selectedUserLog = userLog;
  }

  getUserLogList():void {
    this.tUserLogService.getList(this.currentPage, this.pageSize).subscribe(data => {
      console.log("tUserLogService:",data);
      this.userLogList = data.data.list;
      this.totalItems = data.data.total;
    });

  }
}
