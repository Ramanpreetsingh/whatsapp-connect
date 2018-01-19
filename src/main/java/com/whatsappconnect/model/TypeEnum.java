package com.whatsappconnect.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/*
 @author Ramanpreet Singh on 19/01/18
 *
 */
public enum TypeEnum {

    TEXT("text"),FILE("file"),CAPTION("caption");

    String type;

    TypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .toString();
    }
}
