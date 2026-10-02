package com.yonsai.deploy_mcp.client;

import org.springframework.cloud.openfeign.FeignClient;

// @FeignClient 스프링아! 자동으로 코드 만들어주는 것!
@FeignClient(
  name ="jsonplaceholderTest", // 코드를 자동으로 만들어주는 담당자이름
  url ="https://jsonplaceholder.typicode.com"
)

public interface TestClient {

  
}
