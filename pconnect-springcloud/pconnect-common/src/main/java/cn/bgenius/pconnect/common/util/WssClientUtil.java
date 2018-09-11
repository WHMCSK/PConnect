package cn.bgenius.pconnect.common.util;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
/**
 * WebSocket
 */
public class WssClientUtil {
	private static final Logger LOG = LogManager.getLogger(WssClientUtil.class);
	public static String fcatRISKREPORT_HANDLER = "pconnectRiskReport";
	public static String HIGHRISKREPORT_HANDLER = "highRiskReport";
	public static String WEIXINMONITOR_HANDLER = "monitorReport";
//	private static ConfigPropertiesCache propertiesCache = ConfigPropertiesCache.getInstance();
//	private final static String WSSURL = propertiesCache.get("wss.server.url");
//	private final static int TIMEOUT = Integer.parseInt(propertiesCache.get("wss.client.timeout"));
//	private final static int DELAY = Integer.parseInt(propertiesCache.get("wss.client.delay"));
//	wss.server.url=http://120.76.193.240:3000/wisdompconnect-ws
//		#4 seconds closed
//		wss.client.timeout=4
//		#500milionseconds delay
//		wss.client.delay=500
	private final static String WSSURL = "test";
	private final static int TIMEOUT = 10;
	private final static int DELAY = 20;
	
	/**
	 * 回推数据
	 * @param handler
	 * @param jsonBodyStr
	 */
	public static synchronized void send(final String handler,final String jsonBodyStr){
		Socket socket = null;
		try {
			LOG.debug("[Websocket Created] Prepared for connect , TIMEOUT="+TIMEOUT+" s,Delay="+DELAY);			
			socket = IO.socket(WSSURL);
			socket.open();
			int times = 0;
			socket.connect();
			//等待连接 超时释放
			while(!socket.connected()&&times*DELAY<=TIMEOUT*1000){
				Thread.sleep(DELAY);
				LOG.debug("[Websocket Connecting] Connecting "+(++times*0.5)+" times");
			}
			if(!socket.connected()){				
				LOG.debug("[Websocket Stop] Lost connection by timeout over "+times*0.5+" s");
				if(socket!=null)socket.close();
			}else{
				socket.emit(handler,jsonBodyStr);
				LOG.debug("[Websocket Success] Sended In "+times*0.5+" s");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			if(socket!=null)socket.close();
			socket = null;
			LOG.debug("[Websocket Closed] Closed Successfully");
		}
	}
	/**
	 * 回推URL
	 * @param handler
	 * @param data
	 */
	public static synchronized void sendURL(final String handler,final Map<String,Object> data){
		Socket socket = null;		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("data", data);
			LOG.error("[Websocket Created "+handler+"] Prepared for connect , TIMEOUT="+TIMEOUT+" s,Delay="+DELAY);			
			socket = IO.socket(WSSURL);
			socket.open();
			int times = 0;
			socket.connect();
			//等待连接 超时释放
			while(!socket.connected()&&times*DELAY<=TIMEOUT*1000){
				Thread.sleep(DELAY);
				LOG.error("[Websocket Connecting] Connecting "+(++times*0.5)+" times");
			}
			if(!socket.connected()){				
				LOG.error("[Websocket Stop] Lost connection by timeout over "+times*0.5+" s");
			}else{
				socket.emit(handler,JsonUtil.getSuccessJsonObject(jsonObject).toJSONString());
				LOG.error("[Websocket Success] Sended In "+times*0.5+" s");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally {
			if(socket!=null)socket.close();
			socket = null;
			LOG.error("[Websocket Closed] Closed Successfully");
		}
	}
}
