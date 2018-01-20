package com.whatsappconnect.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsappconnect.model.CreateGroupRequest;
import com.whatsappconnect.model.SendMessageRequest;
import com.whatsappconnect.model.ServiceResponse;
import com.whatsappconnect.service.WhatsappSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/*
 @author Ramanpreet Singh on 19/01/18
 *
 */

@RestController
@RequestMapping("/whatsapp")
public class WhatsappSenderController {


    @Autowired
    WhatsappSenderService whatsappSenderService;

    private static Logger LOGGER = LoggerFactory.getLogger(WhatsappSenderController.class);

    @RequestMapping(value = "/createGroup", method = RequestMethod.POST)
    public ServiceResponse<String> createGroup(@RequestBody String requestBody) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        CreateGroupRequest createGroupRequest = mapper.readValue(requestBody, CreateGroupRequest.class);

        String result = whatsappSenderService.createGroup(createGroupRequest);
        return new ServiceResponse<String>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public ServiceResponse<String> sendMessage(@RequestBody String requestBody) throws IOException {

     //   Process process = Runtime.getRuntime().exec("python /Users/raman/Repos/whatsapp-connect/src/main/resources/spreadsheet.py");

        ObjectMapper mapper = new ObjectMapper();
        SendMessageRequest sendMessageRequest = mapper.readValue(requestBody, SendMessageRequest.class);

        String result = whatsappSenderService.sendMessage(sendMessageRequest);
        return new ServiceResponse<String>(result, HttpStatus.OK);
    }





}
