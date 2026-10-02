package com.yonsai.deploy_mcp.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
	public String home() {
		return "index";
	}
}
/*
OpenFeign
 - 자바에서 다른 서버의 API를 쉽게 호출할 수 있는 도구!
 - 원래라면 코드를 직접 작성하지만 OpenFeign 요청 주소를 적으면
   코드를 자동으로 만들어준다.
 - 자바버전 + spring ai버전 + OpenFeign 버전 확인 꼭!(호환성)

AI -> MCP 도구 호출: 대출상품 조회해줘!
MCP -> OpenFeign 호출
OpenFeign -> 공공데이터 API 요청(직접 코드 작성해서 자동으로 호출)
조회결과 -> AI에게 전달
AI -> 사용자에게 설명!

OpenFeign 사용할 때
1. main 파일에 가서 @EnableFeignClients 추가하기
2. 외부 서버에서 할 일 데이터 1개를 가져오는 코드를 작성한다.
   규칙이 바뀌면 안되기 때문에 인터페이스로 고정!



*/