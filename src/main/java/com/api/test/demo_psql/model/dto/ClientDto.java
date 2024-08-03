package com.api.test.demo_psql.model.dto;

import java.io.Serializable;
import java.sql.SQLData;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {
    private Integer clientId;
    private String name;
    private Date registrationDate;
    private String email;

}
