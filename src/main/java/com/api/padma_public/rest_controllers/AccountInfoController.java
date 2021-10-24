package com.api.padma_public.rest_controllers;

import com.api.padma_public.entities.AccountRequest;
import com.api.padma_public.entities.AccountResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountInfoController {

    @Value("${cbs.uri}")
    private String uri;

    @Value("${response.accInfoNum}")
    private int accInfoNum;

    @PostMapping("getSingleAccountInfo")
    public ResponseEntity<AccountResponse> getSingleAccountInfo(@RequestBody AccountRequest accountRequest)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map<String, String> map = new HashMap<>();
        map.put("outSource", "PadmaCLK");
        map.put("requestFlag", "41");
        map.put("accountNumber", accountRequest.getAccountNumber());

        JSONObject jsonObject = new JSONObject(map);

        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);
        System.out.println(request);

        ResponseEntity<String> res = restTemplate.postForEntity(uri,request,String.class);

        return sendResponseAccCheck(res);
    }

    private ResponseEntity<AccountResponse> sendResponseAccCheck(HttpEntity<String> request){
        String body = request.getBody();
        JSONObject accResBody = new JSONObject(body);

        String res = (String)accResBody.get("outputOFSResponse");
        String resId = (String)accResBody.get("responseId");
        String resStatus = (String)accResBody.get("status");

        String[] resArray = res.split("\\|");



        int numOfResponse = resArray.length;
        System.out.println(numOfResponse);

        AccountResponse accountResponse;

        if(accInfoNum == numOfResponse) {
            accountResponse = new AccountResponse(
                    resArray[0],
                    resArray[1],
                    resArray[2],
                    resArray[3],
                    resArray[4],
                    resArray[5],
                    resArray[6],
                    resArray[7]
            );
            return ResponseEntity.ok().body(accountResponse);
        }
        else {
            accountResponse = new AccountResponse();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(accountResponse);
        }



    }

}
