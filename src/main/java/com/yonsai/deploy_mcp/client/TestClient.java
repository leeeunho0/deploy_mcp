package com.yonsai.deploy_mcp.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// @FeignClient 스프링아! 자동으로 코드 만들어주는 것!
@FeignClient(
  name ="jsonplaceholderTest", // 코드를 자동으로 만들어주는 담당자이름
  url ="https://jsonplaceholder.typicode.com")
public interface TestClient {

  // 추가 경로
  // getPosts()호출하면 /post/1 로 GET요청을 보내고
  // 응답을 key:valus 값으로 받는 Map형식으로 받아줘!
  // @GetMapping ("/posts/1")
  // Map<String,Object> getPost();

  // 여러개를 가져올때 사용하는 방법
  @GetMapping ("/posts")
  List<Map<String,Object>> getPosts();

  // 오픈페인도구가 자동으로 rest api 코드 만들어서 데이터 받는다.
  @GetMapping ("/comments")
  List<Map<String, Object>> getComments();

}
