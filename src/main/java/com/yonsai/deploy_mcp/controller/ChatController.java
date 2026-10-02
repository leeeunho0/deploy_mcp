package com.yonsai.deploy_mcp.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yonsai.deploy_mcp.tools.CommentTool;
import com.yonsai.deploy_mcp.tools.LoanTool;
import com.yonsai.deploy_mcp.tools.PostTool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ChatController {

  private final ChatClient chatClient;

  @Autowired
  private PostTool postTool;

  @Autowired
  private CommentTool commentTool;

  @Autowired
  private LoanTool loanTool;

  // 생성자 서버가 실행할 때 한번만 실행해라! 타입검사해라!
  // private final 한번 저장된 객체는 절대 못바꾼다.
  // 매개변수를 이용해서 타입도 검사해준다! (안정성!)
  public ChatController(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @GetMapping("/chat")
  public String chat(@RequestParam("qus") String qus) {
    // 1. 로그 확인
    System.out.println("ChatController - chat()");

    // 2. AI 질문보내고 응답 받기
    String 결과 = chatClient
        .prompt()
        .user(qus)
        .tools(postTool, commentTool, loanTool)
        .call()
        .content();
    // 3. 브라우저로 보내기
    return 결과;
  }

}
/*
AI에게 쓸 수 있는 함수 목록을 알려주면 AI가 질문을 보고
필요한 함수를 골라서 호출을 요청하는 기능!
AI는 실시간 데이터를 모르기 때문에 우리가 설정한 함수들 가녀와서
실행하고 데이터를 가져온다!

AI가 내 함수들을 쓸 수 있도록 알려줘야 한다.
어노테이션이 붙었다. @Tool

*/