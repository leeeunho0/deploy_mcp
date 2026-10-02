package com.yonsai.deploy_mcp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yonsai.deploy_mcp.client.PublicClient;

import tools.jackson.databind.JsonNode;

@Service
public class PublicDataService {

  @Value("${SERVICE}")
  private String serviceKey;

  @Autowired
  private PublicClient 자동코드담당자;

  public String getLoan() {

    // 1. 공공 api 호출
    JsonNode 공공데이터결과 = 자동코드담당자.getLoan(
        serviceKey,
        "1",
        "10",
        "json");

    System.out.println("공공데이터" + 공공데이터결과.toString());

    // 필요한 부분만 꺼내기(경로로 바로 접근)
    JsonNode 파싱결과 = 공공데이터결과.at("/response/body/items/item");
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