package com.zifuji.cloud.server.websocket.module.websocket.component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

<<<<<<< HEAD
//@Slf4j
=======
@Slf4j
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
public class WebsocketSessionManager {
	/**
     * 保存连接 session 的地方
     */
    private static ConcurrentHashMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>(1000);

    /**
     * 添加 session
     *
     * @param key
     */
    public static void add(String key, WebSocketSession session) {
    	
        // 添加 session
        SESSION_POOL.put(key, session);
    }

    /**
     * 删除 session,会返回删除的 session
     *
     * @param key
     * @return
     */
    public static WebSocketSession remove(String key) {
        // 删除 session
        return SESSION_POOL.remove(key);
    }

    public static Integer size() {
    	 return SESSION_POOL.size();
    }
    
    /**
     * 删除并同步关闭连接
     *
     * @param key
     */
    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                // 关闭连接
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得 session
     *
     * @param key
     * @return
     */
    public static WebSocketSession get(String key) {
        // 获得 session
        return SESSION_POOL.get(key);
    }
    public static ConcurrentHashMap<String, WebSocketSession> get() {
        // 获得 session
        return SESSION_POOL;
    }
    
    public static void sendMessage() {
    	
    }
    
    
}
