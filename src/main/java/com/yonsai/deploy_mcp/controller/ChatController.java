package com.yonsai.deploy_mcp.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class ChatController {
  
  private final ChatClient chatClient;

  // 생성자 서버가 실행할 때 한 번만 실행해라! 타입검사해라!
  // private final 한 번 저장된 객체는 절대 못 바꾼다.
  // 매개변수를 이용해서 타입도 검사해준다! (안정성!)
  public ChatController(ChatClient.Builder builder){
    this.chatClient = builder.build();
  }

  @GetMapping ("/chat")
  public String chat(@RequestParam ("qus") String qus){
    //1.로그확인
    System.out.println("ChatController - chat()");

    //2.AI 질문보내고 응답 받기
    String 결과 = chatClient
                .prompt()
                .user(qus)
                .call()
                .content();
                
    //3.브라우저로 보내기
    return 결과;
  }
}
