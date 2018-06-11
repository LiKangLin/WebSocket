package com.ricky.websocket;

import com.ricky.entity.HydrologyEntity;
import com.ricky.entity.ProductEntity;
import com.ricky.entity.user;
import com.ricky.mapper.HydrologyMapper;
import com.ricky.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnOpen;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 聊天控制器
 */
@Controller
public class GreetingController {
    /**session操作类*/
    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;
    /**消息发送工具*/
    @Autowired
    private SimpMessagingTemplate template;


    @Autowired
    private HydrologyMapper hydrologyMapper;

    @Autowired
    STOMPConnectEventListener stompConnectEventListener;

    @RequestMapping(value = "/addHydrologyEntity", method = RequestMethod.POST)
    public boolean addHydrologyEntity( HydrologyEntity hydrologyEntity) {
        System.out.println("开始新增...");
        return hydrologyMapper.addHydrologyEntity(hydrologyEntity);
    }

    @RequestMapping(value = "/Hydrology/{id}")
    public HydrologyEntity queryById(@PathVariable Long id) {
        return hydrologyMapper.queryById(id);
    }

    @RequestMapping(value = "/Hydrology/{address}")
    public HydrologyEntity queryByAddress(@PathVariable String address) {
        return hydrologyMapper.queryByAddress(address);
    }
    /*@RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public void insert(@RequestBody ProductEntity product) {
        productMapper.insert(product);
    }*/
    @PostMapping(value = "/index")
    public  String index(@ModelAttribute user auser, Model model){
        //获取用户登录数据，接下来是验证数据库中是否有该用户
        String username=auser.getUsername();
        String password=auser.getPassword();
        model.addAttribute("username",username);
        System.out.print(username);
        return "/messaget3";
    }
    @RequestMapping(value = "/index1")
    public  String index1(){
        return "/index1";
    }
    @RequestMapping(value = "/msg/message")
    public  String ToMessage(){
        return "/message";
    }
    @RequestMapping(value = "/msg/messaget2")
    public  String ToMessaget2(){
        return "/messaget2";
    }
    @RequestMapping(value = "/msg/messaget3")
    public  String ToMessaget3(){
        return "/messaget3";
    }

    /**
     * 用户广播
     * 发送消息广播  用于内部发送使用
     * @param request
     * @return
     */
    @GetMapping(value = "/msg/sendcommuser")
    public  @ResponseBody
    OutMessage SendToCommUserMessage(HttpServletRequest request){
        List<String> keys=webAgentSessionRegistry.getAllSessionIds().entrySet()
                .stream().map(Map.Entry::getKey)
                .collect(Collectors.toList());
        keys.forEach(x->{
            String sessionId=webAgentSessionRegistry.getSessionIds(x).stream().
                    findFirst().get().toString();
            template.convertAndSendToUser(sessionId,"/topic/greetings",
                    new OutMessage("commmsg：allsend, " + "send  comm"
                            ),createHeaders(sessionId));
        });
        long id=2;
        HydrologyEntity  he=hydrologyMapper.queryById(id);
        return new OutMessage(" 广播消息为：" + ":"+he.toString());
    }




    /**
     * 同样的发送消息   只不过是ws版本  http请求不能访问
     * 根据用户key发送消息
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/hellosingle")
    public void greeting2(InMessage message) throws Exception {
        Map<String,String> params = new HashMap(1);
        params.put("1111","1111");
        String sessionId=webAgentSessionRegistry.getSessionIds(message.getId())
                .stream().findFirst().get();
        long id=1;
        HydrologyEntity  he=hydrologyMapper.queryById(id);
        template.convertAndSendToUser(sessionId,"/topic/greetings",new OutMessage("single send to："
                +he.toString()+", from:" + message.getTopic() + "!"),createHeaders(sessionId));
    }

    /**
     * 同样的发送消息
     * 根据用户key发送消息，单点登录
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/msg/single")
    public void greeting3(InMessage message) throws Exception {
        //String username=message.getName();
      //  Map<String,String> params2 = new HashMap(1);
     //   params2.put("1111","1111");

        //这里的Session值和监听时的相同
        String session2Id=webAgentSessionRegistry.getSessionIds(message.getId())
                .stream().findFirst().get();
        String address=message.getTopic();
       // long id=1;
        HydrologyEntity  he=hydrologyMapper.queryByAddress(address);
       /* for(int i=0;i<stompConnectEventListener.hashmap.size();i++){
            stompConnectEventListener.hashmap.get(message.getId());
            template.convertAndSendToUser(session2Id,"/topic/greetings",new OutMessage("用户"+message.getId()+"收到推送消息为:"
                    +he.toString()+"!"),createHeaders(session2Id));
        }*/
       System.out.println(session2Id);
        template.convertAndSendToUser(session2Id,"/topic/greetings",new OutMessage("用户"+message.getId()+"收到推送消息为:"
                +he.toString()+"!"),createHeaders(session2Id));
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

 /*   @OnOpen
    public void onOpen(@PathParam(value = "userno") String param, Session session, EndpointConfig config) {
        System.out.println(param);
        userno = param;//接收到发送消息的人员编号
        this.session = session;
        webSocketSet.put(param, this);//加入map中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }*/
}
