package com.whatsappconnect.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/*
 @author Ramanpreet Singh on 19/01/18
 *
 */
public class SendMessageRequest {

    private String to;

    private String client;

    private  String typeValue;

    private TypeEnum type;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("to", to)
                .append("type", type)
                .append("client", client)
                .append("typeValue", typeValue)
                .toString();
    }
}
