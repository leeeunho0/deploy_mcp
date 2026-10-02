package com.yonsai.deploy_mcp.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonsai.deploy_mcp.service.PublicDataService;

@Component 
public class LoanTool {

  @Autowired 
  private PublicDataService dataService;
  
  @Tool(description = "서민금융 대출상품 목록을 조회한다. 대출상품명,최대한도 알려준다")
  public String getLoans(){
    //1. 로그확인
    System.out.println("LoanTool - getLoans()");

    //2. 서비스 호출 (공공데이터API->문자열) Ai가 읽을거라 \n줄바꿈!
    return dataService.getLoan();
  }
}