package com.whatsappconnect.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*
 @author Ramanpreet Singh on 19/01/18
 *
 */
public class ServiceResponse<T> extends ResponseEntity<T> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public ServiceResponse(T data) {
        super(data, HttpStatus.OK);
    }

    public ServiceResponse(T data, HttpStatus status) {
        super(data, status);
    }

    public static ServiceResponse<JsonNode> getDefaultServiceOkResponse() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("message", "Success");
        return new ServiceResponse<>(objectNode, HttpStatus.OK);
    }

    public static ServiceResponse<JsonNode> getDefaultServiceFailedResponse(String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("message", message);
        return new ServiceResponse<>(objectNode, HttpStatus.BAD_REQUEST);
    }

    /**
     * OK response with some message
     *
     * @param message
     * @return
     */
    public static ServiceResponse<JsonNode> getDefaultServiceOkResponse(String message) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("message", message);
        return new ServiceResponse<>(objectNode, HttpStatus.OK);
    }

}
