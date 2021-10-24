package com.api.padma_public.rest_controllers;

import com.api.padma_public.entities.AccountRequest;
import com.api.padma_public.entities.AccountResponse;
import com.api.padma_public.entities.AccountStatementResponse;
import com.api.padma_public.entities.Transactions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountStatementController {

    @Value("${cbs.uri}")
    private String uri;

    @PostMapping("getSingleAccountStatement")
    public ResponseEntity<AccountStatementResponse> getSingleAccountStatement(@RequestBody AccountRequest accountRequest)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map<String, String> map = new HashMap<>();
        map.put("outSource", "PadmaCLK");
        map.put("requestFlag", "47");
        map.put("accountNumber", accountRequest.getAccountNumber());

        JSONObject jsonObject = new JSONObject(map);

        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        System.out.println(request);

        ResponseEntity<String> res = restTemplate.postForEntity(uri,request,String.class);

        //return res;
        return sendResponseAccStmtCheck(res);
    }

    private ResponseEntity<AccountStatementResponse> sendResponseAccStmtCheck(HttpEntity<String> request)
    {
        String body = request.getBody();
        JSONObject accResBody = new JSONObject(body);

        String res = (String)accResBody.get("outputOFSResponse");
        String resId = (String)accResBody.get("responseId");
        String resStatus = (String)accResBody.get("status");

        String[] resArray = res.split("#");



        int numOfResponse = resArray.length;
        System.out.println(numOfResponse);

        AccountStatementResponse accountStatementResponse
                = new AccountStatementResponse();
        ArrayList<Transactions> trRows = new ArrayList<>();

        for(int i=1;i<numOfResponse;++i){
            if(i==1){
                String[] tmp = resArray[i].split("\\|");
                //System.out.println(tmp.length);

                if(tmp.length>4){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountStatementResponse);
                }
                accountStatementResponse.setOpeningBalance(tmp[3]);

            }
            else if(i<(numOfResponse-2) ){
                String[] tmp = resArray[i].split("\\|");
                //System.out.println(tmp.length);

                Transactions tmpTrRow = new Transactions(
                        tmp[1],
                        tmp[2],
                        tmp[3],
                        tmp[4],
                        tmp[5]
                );

                trRows.add(tmpTrRow);
            }
            else if(i==numOfResponse-2){
                accountStatementResponse.setTransactions(trRows);

                String[] tmp = resArray[i].split("\\|");
                //System.out.println(tmp.length);

                accountStatementResponse.setClosingBalance(tmp[2]);

            }


        }

        return ResponseEntity.status(HttpStatus.OK).body(accountStatementResponse);



    }

}
