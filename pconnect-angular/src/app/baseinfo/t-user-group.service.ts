import {Injectable} from "@angular/core";
import {HttpUtil} from "../util/http.util";
import {TUserGroup} from "./t-user-group";


@Injectable()
export class TUserGroupService{
  private baseUrl = "/pconnect-user/v1/tUserGroup";
  constructor(private httpUtil: HttpUtil){
  }

  getList(currentPage:number, pageSize:number) {
    let param = "?pageSize="+pageSize+"&pageNum="+currentPage;
    let url = this.baseUrl+"/listByPage"+param;
    return this.httpUtil.get(url);
  }

  delete(id:any){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.delete(url);
  }

  add(tUserGroup: TUserGroup){
    let url = this.baseUrl+"/add";
    return this.httpUtil.post(url, tUserGroup);
  }
  getById(id:number){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.get(url);
  }
  update(tUserGroup: TUserGroup){
    let url = this.baseUrl+"/update";
    return this.httpUtil.put(url, tUserGroup);
  }

  addByUserIdAndGroupId(tUserGroup:TUserGroup) {
    let url = this.baseUrl+"/addByUserIdAndGroupId";
    return this.httpUtil.post(url, tUserGroup);
  }

  deleteByUserIdAndGroupId(tUserGroup:TUserGroup) {
    let url = this.baseUrl+"/deleteByUserIdAndGroupId";
    return this.httpUtil.post(url, tUserGroup);
  }
}
