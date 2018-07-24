package com.github.mthizo247.hello;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000 * 2); // simulated delay
		Greeting greeting = new Greeting("Hello, " + message.getName() + "!");
		return greeting;
	}


//	@SubscribeMapping("/list")
//	public void list(@Header Map<String, List<String>> nativeHeaders, @Payload DataRequest request, Principal principal) {
//		String requestId = nativeHeaders.get("requestId") != null ? nativeHeaders.get("requestId").get(0) : "0";
//		messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/socket/responses", new Response(ResponseType.SUCCESS, requestId, null, getEmployees()));
//	}


	@MessageMapping("/sendToTopic2")
	@SendTo("/topic/t1")
	public String t1(String msg) throws Exception {
		System.out.println("sendToTopic	msg = [" + msg + "]");

//		Thread.sleep(1000 * 2); // simulated delay
//		MsgWeixin msgWeixin = JSONObject.parseObject(msg, MsgWeixin.class);
		return "hello : msg" + msg;
	}

	@MessageMapping("/sendToTopic")
//	@SendTo("/user/topic/t1")
	//开启STOMP协议来传输基于代理的消息，这时控制器支持使用@MessageController，就像使用@RequestMapping是一样的
	//当浏览器向服务端发送请求时，通过@MessageController映射/chat这个路径
	//在SpringMVC中，可以直接在参数中获得principal,其中包含当前用户的信息
	public void handleChassst(Principal principal, String msg){
		//下面的代码就是如果发送人是Michael，接收人就是Janet，发送的信息是message，反之亦然。
		System.out.println("principal = [" + principal.getName() + "], msg = [" + msg + "]");
		MsgWeixin msgWeixin = JSONObject.parseObject(msg, MsgWeixin.class);

		String content = msgWeixin.getContent();
		String toUserId = msgWeixin.getToUserId();
		if (!principal.getName().equals(toUserId)) {
			System.err.println(principal.getName()  + " --- toUserId = " + toUserId);
		}
		Map<String, Message<?>> tokens = WebSocketConfig.tokens;
        Set<String> tokensSet = tokens.keySet();
        for (Iterator<String> iterator = tokensSet.iterator(); iterator.hasNext(); ) {
            String next =  iterator.next();
            if (next.equals(principal.getName())) {
                if (!msgWeixin.isIncludeMe()) {
                    continue;
                }
            }
            simpMessagingTemplate.convertAndSendToUser(next,"/queue/notifications",
                    next + "__"  +principal.getName() + "__-发送:" + content);
        }
	}


	@Autowired
	//通过SimpMessagingTemplate模板向浏览器发送消息。如果是广播模式，可以直接使用注解@SendTo
	private SimpMessagingTemplate simpMessagingTemplate;

	//开启STOMP协议来传输基于代理的消息，这时控制器支持使用@MessageController，就像使用@RequestMapping是一样的
	//当浏览器向服务端发送请求时，通过@MessageController映射/chat这个路径
	@MessageMapping("/chat")
	//在SpringMVC中，可以直接在参数中获得principal,其中包含当前用户的信息
	public void handleChat(Principal principal, String msg){
		//下面的代码就是如果发送人是Michael，接收人就是Janet，发送的信息是message，反之亦然。
		System.out.println("principal = [" + principal + "], msg = [" + msg + "]");
		MsgWeixin msgWeixin = JSONObject.parseObject(msg, MsgWeixin.class);

		String content = msgWeixin.getContent();
		String toUserId = msgWeixin.getToUserId();
		if (!principal.getName().equals(toUserId)) {
			System.err.println(principal.getName()  + " --- toUserId = " + toUserId);
		}

//		simpMessagingTemplate.convertAndSendToUser(toUserId,"/queue/notifications",
//				new String[]{toUserId, principal.getName() + "-发送:" + content});

		simpMessagingTemplate.convertAndSendToUser(toUserId,"/queue/notifications",
				toUserId + "__"  +principal.getName() + "__-发送:" + content);
//        if(principal.getName().equals("Michael")){
//            //通过SimpMessagingTemplate的convertAndSendToUser向用户发送消息。
//            //第一参数表示接收信息的用户，第二个是浏览器订阅的地址，第三个是消息本身
//            simpMessagingTemplate.convertAndSendToUser("Janet","/queue/notifications",
//                    principal.getName() + "-发送:" + msg);
//        } else {
//            simpMessagingTemplate.convertAndSendToUser("Michael","/queue/notifications",
//                    principal.getName() + "-发送:" + msg);
//        }
	}


	@GetMapping("/getAvailableWsUser")
	@ResponseBody
	public  Map  getAvailableWsUser(String tenantId) {
		// 7ee46c5e-5dbb-4a63-8674-cad636f5ebf9
		Map<String, Message<?>> tokens = WebSocketConfig.tokens;
        Set<Map.Entry<String, Message<?>>> entries = tokens.entrySet();
        for (Iterator<Map.Entry<String, Message<?>>> iterator = entries.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Message<?>> next =  iterator.next();
            System.out.println("next = " + next.getKey() + "    " + next.getValue());
        }
        return tokens;
	}

}
