package cn.bgenius.pconnect.common.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
//将base64转换为byte[]
//转byet[]换为base64

public class BASE64ConvertUtil {

	private BASE64ConvertUtil() {
	}

	public static String toBase64Str(byte[] srcByte) { 
        Base64 base64=new Base64();  
        byte[] b=base64.encode(srcByte);  
        String s=new String(b);  
        return s;  
	}
	
	public static byte[] toByte(String base64Str) throws IOException{ 
        Base64 base64=new Base64();  
        return base64.decode(base64Str);  
	}
	
	public static void main(String[] args) throws Exception{
		FileInputStream fin = new FileInputStream(new File("C:\\sj1.jpg"));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
        //System.out.println(toBase64Str(bytes));
        FileOutputStream fout = new FileOutputStream("C:\\ww.jpg");
        //将字节写入文件
        fout.write(toByte(toBase64Str(bytes)));
        fout.close();
	}
}