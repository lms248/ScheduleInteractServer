package com.rcgl.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

@WebServlet("/chat")
public class ChatWebSocketServlet extends WebSocketServlet {

    private final Map<Integer, WsOutbound> map = new HashMap<Integer, WsOutbound>();
    //private static final long serialVersionUID = -1058445282919079067L;
    private static final long serialVersionUID = 911879078000755859L;
    
    @Override
    protected StreamInbound createWebSocketInbound(String arg0,
            HttpServletRequest request) {
    	System.out.println(1111111);
    	String username = null;
		try {
			username = request.getParameter("username");
			if(username != null){
				username = new String(username.getBytes("ISO8859_1"),"UTF-8");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(username+"请求连接：");
    	
        return new ChatMessageInbound();
    }
    
    public class ChatMessageInbound extends MessageInbound {

        @Override
        protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
            // TODO Auto-generated method stub

        }

        @Override
        protected void onTextMessage(CharBuffer arg0) throws IOException {
            String msg = arg0.toString();
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            //msg = " <font color=green>" + sdf.format(date) + "</font><br/> " + msg;
            String[] msg_array = msg.split(":");
            msg = " <font color=green>"+msg_array[0]+": " + sdf.format(date) + "</font><br/>&emsp;&emsp;" + msg_array[1];
            broadcast(msg);
        }

        @Override
        protected void onClose(int status) {
            map.remove(getWsOutbound().hashCode());
            System.out.println("下线" + status);
            super.onClose(status);
        }

        @Override
        protected void onOpen(WsOutbound outbound) {
            map.put(outbound.hashCode(), outbound);
            System.out.println("上线" + outbound.hashCode());
            super.onOpen(outbound);
        }

    }


    private void broadcast(String msg) {
        Set<Integer> set = map.keySet();
        for (Integer integer : set) {
            WsOutbound outbound = map.get(integer);
            CharBuffer buffer = CharBuffer.wrap(msg);
            try {
                outbound.writeTextMessage(buffer);
                outbound.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
