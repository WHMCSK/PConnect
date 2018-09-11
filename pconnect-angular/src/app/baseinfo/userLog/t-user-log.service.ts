import {Injectable} from "@angular/core"; 
import {TUserLog} from "./t-user-log";  
import {HttpUtil} from "../../util/http.util";


@Injectable()
export class TUserLogService{
  private baseUrl = "/pconnect-user/v1/tUserLog";
  constructor(private httpUtil: HttpUtil){
  }
  getList(currentPage:number, pageSize:number) {
    let param = "?pageSize="+pageSize+"&pageNum="+currentPage;
    let url = this.baseUrl+"/listByPage"+param;
    return this.httpUtil.get(url);
  }
}
