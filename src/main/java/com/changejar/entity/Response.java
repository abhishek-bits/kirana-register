package com.changejar.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private String timeStamp;
    private HttpStatus status;
    private Integer statusCode;
    private String message;
    private Map<?, ?> data;
}

