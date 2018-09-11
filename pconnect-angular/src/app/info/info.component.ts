import {
  Component, OnInit, Input, Output, EventEmitter
} from '@angular/core';
import {AlertEnum} from "../alert/alert";



@Component({
  selector: 'my-info',
  template: `
   <div style="position: absolute;z-index: 200;left:50%;margin-left:-130px;width:260px;" *ngIf="msgFlag">
  <div class="alert {{alertClass}} alert-dismissible">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4> {{msgLabel}}</h4>
    <div style="min-width:100px;"> {{msg}}</div>
  </div>
</div>`
})
export class InfoComponent implements OnInit {
  public msgFlag:boolean;
  public alertClass:string;
  @Input() public timeOut:number = 1000;
  @Input() public msgLabel:string = '提示！';
  @Input() public alert:AlertEnum = AlertEnum.Info;
  public msgStr:string = '这是一个提示信息';
  public _msg:string;
  @Output() public msg_:EventEmitter<string> = new EventEmitter<string>();

  ngOnInit():void {
    this._msg = this.msgStr;
    this.msgFlag = false;
    this.msg_.emit(this._msg);
  }

  showInfo(){
    this.msgFlag = true;
    switch (this.alert){
      case AlertEnum.Danger:
        this.alertClass = "alert-danger";
        break;
      case AlertEnum.Success:
        this.alertClass = "alert-success";
        break;
      case AlertEnum.Warning:
        this.alertClass = "alert-warning";
        break;
      default:
        this.alertClass = "alert-info";
        break;
    }
    setTimeout(() => {
      this.ngOnInit();
    }, this.timeOut);
  }

  @Input()
  public get msg():string {
    return this._msg;
  }


  public set msg(msg:string) {
      if(msg==this.msgStr){
        return;
      }
      if(!msg || msg=='')return;
      this._msg = msg;
      this.showInfo();
  }
}
