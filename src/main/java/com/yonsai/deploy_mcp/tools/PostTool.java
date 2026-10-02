package com.yonsai.deploy_mcp.tools; 

import java.util.List;
import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonsai.deploy_mcp.client.TestClient;

@Component // 서버가 실행하면 자동으로 객체 만들기
public class PostTool {

  @Autowired
  private TestClient 자동코드담당자;

  @Tool(description = "게시글 목록을 조회한다.")
  public String getPosts() {
    // 1. 로그확인
    System.out.println("PostTool - getPosts()");

    // 2. 전체 게시글 가져오기
    List<Map<String, Object>> 결과 = 자동코드담당자.getPosts();

    // 3.결과를 문자로 바꾸고 브라우저로보내기
    return 결과.toString();
  }

}