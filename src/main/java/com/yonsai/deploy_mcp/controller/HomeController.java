package com.yonsai.deploy_mcp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonsai.deploy_mcp.client.PublicClient;
import com.yonsai.deploy_mcp.client.TestClient;
import com.yonsai.deploy_mcp.service.PublicDataService;

import tools.jackson.databind.JsonNode;

@Controller 
public class HomeController {

  @Autowired
  private TestClient 자동코드작성담당자;

  @Value("${service-key}")
  private String serviceKey;

  @Autowired
  private PublicClient 공공데이터자동코드담당자;

  @Autowired
  private PublicDataService service;

  @GetMapping("/")
  public String home() {
    System.out.println("HomeController - home()");
    
    return "index";
  }

  @GetMapping(value = "/data")
  public String publicData() {

    System.out.println("공공데이터 호출 전!");

    JsonNode 결과 = 공공데이터자동코드담당자
        .getLoan(serviceKey,
            "1",
            "10",
            "json");

    // 필요한 부분만 꺼내기(경로로 바로 접근)
    JsonNode 파싱결과 = 결과.at("/response/body/items/item");
    System.out.println("공공데이터 호출 후 !");

    String 결과정리 = "";

    for (JsonNode 상품한개 : 파싱결과) {

      결과정리 += 상품한개.get("finPrdNm").asString();
      결과정리 += " / ";
      결과정리 += "최대 한도: " + 상품한개.get("lnLmt").asString();
      결과정리 += "</br>"; // 줄바꿈 기호!
      System.out.println(결과정리);
    }

    return 결과정리;
  }

}

/*
OpenFeign
 - 자바에서 다른 서버의 API를 쉽게 호출할 수 있는 도구!
 - 원래라면 코드를 직접 작성하지만 OpenFeign 요청 주소를 적으면
   코드를 자동으로 만들어준다.
 - 자바버전 + spring ai버전 + OpenFeign 버전 확인 꼭!(호환성)
 - 자동인코딩이 되기 때문에 API_KEY를 가져올때는 인코딩이 되지 않은 디코딩키를 사용한다.

AI -> MCP 도구 호출: 대출상품 조회해줘!
MCP -> OpenFeign 호출
OpenFeign -> 공공데이터 API 요청(직접 코드 작성해서 자동으로 호출)
조회결과 -> AI에게 전달
AI -> 사용자에게 설명!

OpenFeign 사용할 때
1. main 파일에 가서 @EnableFeignClients 추가하기
2. 외부 서버에서 할 일 데이터 1개를 가져오는 코드를 작성한다.
   규칙이 바뀌면 안되기 때문에 인터페이스로 고정!

===============================
// System.out.println("실행 전");
    // List<Map<String, Object>> 결과 = 자동코드작성담당자.getPosts();

    // service.getLoan();
    // System.out.println("실행 후");

    // 맵타일을 문자로 변경해서 브라우저로 보내기!

*/