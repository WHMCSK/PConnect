package cn.bgenius.pconnect.common.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by 金飞 on 2017/10/23.
 */
public class HttpHelper {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
        return isAjax;
    }
    public static void setResponseJsonData(HttpServletResponse httpServletResponse, String result) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        OutputStream out = httpServletResponse.getOutputStream();
        out.write(result.getBytes("UTF-8"));
        out.flush();
    }
}