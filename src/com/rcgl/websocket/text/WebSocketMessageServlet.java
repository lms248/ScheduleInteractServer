package com.rcgl.websocket.text;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;

//如果要接收浏览器的ws://协议的请求就必须实现WebSocketServlet这个类
@SuppressWarnings("deprecation")
public class WebSocketMessageServlet extends org.apache.catalina.websocket.WebSocketServlet {
	public static int ONLINE_USER_COUNT	= 1;
	private static final long serialVersionUID = 1L;
	public String getUser(HttpServletRequest request){
		System.out.println("---"+request.getParameter("user"));
		request.getSession().setAttribute("user", request.getParameter("user"));
		return (String) request.getSession().getAttribute("user");
	}

	//跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
        return new WebSocketMessageInbound(this.getUser(request));
    }
}
