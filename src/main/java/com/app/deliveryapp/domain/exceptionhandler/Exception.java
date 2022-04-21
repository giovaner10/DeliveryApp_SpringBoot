package com.app.deliveryapp.domain.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Exception  {
    private Integer status;
    private String type;
    private  String title;
    private String detail;
    private OffsetDateTime dataOcourence;


    private List<Field> fields;


    @Data
    @AllArgsConstructor
    @Builder
    public static class Field{
        private String name;
        private String UserMesage;


    }
}
