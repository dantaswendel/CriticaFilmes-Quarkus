package org.acme.criticafilme.rest.dto;


import lombok.Data;

@Data
public class CreateCriticaRequest {

    private String filme;

    private  String texto;
}
