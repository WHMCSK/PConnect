package cn.bgenius.pconnect.common.util;

import java.util.Date;

public class PConnectLog {
	private Class<?> clazz;
    public PConnectLog(Class<?> clazz) {
        this.clazz = clazz;
    }
    public boolean isDebugEnabled() {
    	return true;
    }
    public void debug(Object message) {
      //if(repository.isDisabled(Level.DEBUG_INT))
        //return;
      //if(Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
    	  StringBuilder sb = new StringBuilder();
    	  sb.append(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
  		  sb.append(" ");
  		  sb.append("INFO");
  		  sb.append(" ");
  		  sb.append("(");
  		  sb.append(StrUtil.getInvokStack(clazz));
  		  sb.append(")");
  		  sb.append(" - ");
  		  sb.append(message);
  		  //
  		  System.out.println(sb.toString());
      //}
    }
}
