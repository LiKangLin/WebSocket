package com.ricky.websocket;

import com.ricky.entity.HydrologyEntity;
import com.ricky.mapper.HydrologyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by PVer on 2018/6/10.
 * 这部分程序移到另外一个类中实现
 */

@Component
public class ScheduledTasks {
   /* //开启定时任务，用作监听数据库中数据的增量变化
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private HydrologyMapper hydrologyMapper;
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;

    @Scheduled(cron="0/10 * * * * *")
    public void reportCurrentTime() {
        boolean flag=true;
        NotificationToUser(flag);

        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    public void NotificationToUser(boolean flag){
        if(flag){
            long id=1;
            HydrologyEntity he=hydrologyMapper.queryById(id);
            *//*System.out.println(he.toString());*//*
            String session2Id=webAgentSessionRegistry.getSessionIds("1234")
                    .stream().findFirst().get();
            *//*template.convertAndSend("/topic/greetings",new OutMessage("用户1234"+"收到推送消息为:"
                    +he.toString()+"!"),createHeaders(session2Id));*//*
            template.convertAndSendToUser(session2Id,"/topic/greetings",new OutMessage("用户1234"+"收到推送消息为:"
                    +he.toString()+"!"),createHeaders(session2Id));
            flag=false;
        }

    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }*/
}
