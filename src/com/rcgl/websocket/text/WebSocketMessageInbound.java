package com.rcgl.websocket.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

@SuppressWarnings("deprecation")
public class WebSocketMessageInbound extends MessageInbound {

	//当前连接的用户名称
	private final String user;

	public WebSocketMessageInbound(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	//建立连接的触发的事件
	@SuppressWarnings("unchecked")
	@Override
	protected void onOpen(WsOutbound outbound) {
		// 触发连接事件，在连接池中添加连接
		JSONObject result = new JSONObject();
		result.put("type", "用户加入");
		result.put("用户名", this.user);
		//向所有在线用户推送当前用户上线的消息
		WebSocketMessageInboundPool.sendMessage(result.toString());
		result = new JSONObject();
		result.put("type", "在线用户数");
		result.put("用户列表", WebSocketMessageInboundPool.getOnlineUser());
		//向连接池添加当前的连接对象
		WebSocketMessageInboundPool.addMessageInbound(this);
		//向当前连接发送当前在线用户的列表
		WebSocketMessageInboundPool.sendMessageToUser(this.user, result.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		WebSocketMessageInboundPool.removeMessageInbound(this);
		JSONObject result = new JSONObject();
		result.put("type", "用户离开");
		result.put("用户名", this.user);
		//向在线用户发送当前用户退出的消息
		WebSocketMessageInboundPool.sendMessage(result.toString());
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
        String str=message.toString();
        
        System.out.println(str+"message="+message.toString());
        String str1[]={""};
        if(str!=null){
        	 str1=str.split(";");
        	if(str1.length>0){
        		str=str1[str1.length-1];
        	}
        	System.out.println(str);
        }
        //WebSocketMessageInboundPool.sendMessageToOther(str1[0],str);
		//向所有在线用户发送消息
		WebSocketMessageInboundPool.sendMessage(message.toString());
	}
}
