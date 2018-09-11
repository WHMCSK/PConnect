import {Injectable} from "@angular/core";
import {HttpUtil} from "../util/http.util";
import {TGroupType} from "./t-group-type";


@Injectable()
export class TGroupTypeService{
  private baseUrl = "/pconnect-user/v1/tGroupType";
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

  add(tGroupType: TGroupType){
    let url = this.baseUrl+"/add";
    return this.httpUtil.post(url, tGroupType);
  }
  getById(id:number){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.get(url);
  }
  update(tGroupType: TGroupType){
    let url = this.baseUrl+"/update";
    return this.httpUtil.put(url, tGroupType);
  }
}
