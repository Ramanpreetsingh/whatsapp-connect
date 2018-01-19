package com.whatsappconnect.service;

import com.whatsappconnect.model.CreateGroupRequest;
import com.whatsappconnect.model.SendMessageRequest;
import com.whatsappconnect.model.TypeEnum;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;


/*
 @author Ramanpreet Singh on 19/01/18
 *
 */

@Service
public class WhatsappSenderService {

    public String createGroup(CreateGroupRequest createGroupRequest) {

        String urlString = "http://54.254.232.208:8080/whatsapp/internal/create_group";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateGroupRequest> requestEntity = new HttpEntity<CreateGroupRequest>(createGroupRequest, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(urlString, requestEntity, String.class);
        return result;
    }

    public String sendMessage(SendMessageRequest sendMessageRequest){

        String urlString = "http://54.254.232.208:8080/whatsapp/internal/send";
        LinkedMultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        if(sendMessageRequest.getType().getType().equals(TypeEnum.FILE.getType())){

            File file = new File("/Users/raman/Repos/whatsapp-connect/src/main/resources/" + sendMessageRequest.getTypeValue());
            FileSystemResource value = new FileSystemResource(file);

            request.add("file",value);
            request.add("to",sendMessageRequest.getTo());
            request.add("client",sendMessageRequest.getClient());
            request.add("eId","dsdfsesf");
            request.add("type","Document");

            HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                    new HttpEntity<>(request, headers);
            RestTemplate restTemplate = new RestTemplate();

            String result = null;
            try{
                result = restTemplate.postForObject(urlString, requestEntity, String.class);
            }catch (Exception e){
                result = "false";
            }

            return result;


        }



        return null;
    }


}
