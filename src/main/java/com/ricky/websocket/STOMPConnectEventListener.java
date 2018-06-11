package com.ricky.websocket;


import com.ricky.entity.HydrologyEntity;
import com.ricky.mapper.HydrologyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * STOMP监听类
 * 用于session注册 以及key值获取
 */
public class STOMPConnectEventListener  implements ApplicationListener<SessionConnectEvent> {
    /*Map hashmap= new HashMap();*/
    List<String> list= new ArrayList<>();
    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        //login get from browser
        String agentId = sha.getNativeHeader("login").get(0);
        String sessionId = sha.getSessionId();
        webAgentSessionRegistry.registerSessionId(agentId,sessionId);
        //把每一次的客户端的连接信息保存在hashmap中
        /*hashmap.put(agentId,sessionId);*/
        //此处是在点击Conncet连接时执行的
        list.add(agentId);
        System.out.println(sessionId);
        System.out.println(agentId );


        System.out.println("我执行了");
        System.out.println("我执行了");
    }

    //开启定时任务，用作监听数据库中数据的增量变化
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private HydrologyMapper hydrologyMapper;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Scheduled(cron="0/15 * * * * *")
    public void reportCurrentTime() {
        boolean flag=true;
        NotificationToUser(flag);

        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    public void NotificationToUser(boolean flag) {
        if (flag) {
            for(int i=0;i<list.size();i++){
                long id = 1;
                HydrologyEntity he = hydrologyMapper.queryById(id);
                String session2Id = webAgentSessionRegistry.getSessionIds(list.get(i))
                        .stream().findFirst().get();

                template.convertAndSendToUser(session2Id, "/topic/greetings", new OutMessage(list.get(i) + "收到推送消息为:"
                        + he.toString() + "!"), createHeaders(session2Id));
            }
            /*long id = 1;
            HydrologyEntity he = hydrologyMapper.queryById(id);
            String session2Id = webAgentSessionRegistry.getSessionIds("1234")
                    .stream().findFirst().get();

            template.convertAndSendToUser(session2Id, "/topic/greetings", new OutMessage("用户1234" + "收到推送消息为:"
                    + he.toString() + "!"), createHeaders(session2Id));*/
        }
        flag=false;
    }









    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
