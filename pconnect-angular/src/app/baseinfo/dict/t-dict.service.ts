import {Injectable} from "@angular/core";
import {TDict} from "./t-dict";
import {HttpUtil} from "../../util/http.util";


@Injectable()
export class TDictService{
  private baseUrl = "/pconnect-user/v1/tDict";
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

  add(tDict: TDict){
    let url = this.baseUrl+"/add";
    return this.httpUtil.post(url, tDict);
  }
  getById(id:number){
    let url = this.baseUrl+"/"+id;
    return this.httpUtil.get(url);
  }
  update(tDict: TDict){
    let url = this.baseUrl+"/update";
    return this.httpUtil.put(url, tDict);
  }
}
