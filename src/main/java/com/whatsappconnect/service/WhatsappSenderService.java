package com.whatsappconnect.service;

import com.whatsappconnect.model.CreateGroupRequest;
import com.whatsappconnect.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Random;


/*
 @author Ramanpreet Singh on 19/01/18
 *
 */

@Service
public class WhatsappSenderService {

    private static Logger LOGGER = LoggerFactory.getLogger(WhatsappSenderService.class);

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

        //TODO: configure url
        String urlString = "http://54.254.232.208:8080/whatsapp/internal/send";
        LinkedMultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        request.add("to",sendMessageRequest.getTo());
        request.add("client",sendMessageRequest.getClient());

        String eId = getSaltString();
        request.add("eId",eId );
        LOGGER.info("Unique EID generated is {}",eId);

        //TODO: configure file path

        switch(sendMessageRequest.getType()){
            case FILE:
                File file = new File("/Users/raman/Repos/whatsapp-connect/src/main/resources/" + sendMessageRequest.getTypeValue());
                FileSystemResource value = new FileSystemResource(file);

                request.add("file",value);
                request.add("type","Document");
                break;

            case TEXT:
                request.add("text",sendMessageRequest.getTypeValue());
                request.add("type","Text");
                break;

            case CAPTION:
                File imageFile = new File("/Users/raman/Repos/whatsapp-connect/src/main/resources/" + sendMessageRequest.getTypeValue());
                FileSystemResource imageValue = new FileSystemResource(imageFile);

                request.add("file",imageValue);
                request.add("type","Image");
                break;

        }

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(request, headers);
        RestTemplate restTemplate = new RestTemplate();

        String result = null;
        try{
            result = restTemplate.postForObject(urlString, requestEntity, String.class);
        }catch (Exception e){
            LOGGER.error("Exception while sending message to {}",sendMessageRequest.getTo());
            result = "Exception while sending message to" + sendMessageRequest.getTo();
        }

        return result;
    }


    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


}
