package com.api.test.demo_psql.model.payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ResponseMessage implements Serializable {
    private String message;
    private Object object;
}