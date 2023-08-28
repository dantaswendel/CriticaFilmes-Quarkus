package org.acme.criticafilme.rest.dto;

import lombok.Data;
import org.acme.criticafilme.domain.domain.Critica;

import java.time.LocalDateTime;

@Data
public class CriticaResponse {

    private String filme;
    private  String critica_text;
    private LocalDateTime dateTime;


    public static CriticaResponse fromEntity (Critica critica){

        CriticaResponse response = new CriticaResponse();

        response.setFilme(critica.getFilme());
        response.setCritica_text(critica.getCritica_text());
        response.setDateTime(critica.getDateTime());

        return response;

    }
}
