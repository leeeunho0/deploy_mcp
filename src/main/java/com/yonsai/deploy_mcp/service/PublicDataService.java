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

    return "";
  }

}
