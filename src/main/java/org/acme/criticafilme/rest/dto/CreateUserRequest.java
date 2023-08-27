package org.acme.criticafilme.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class CreateUserRequest {

    @NotBlank(message = "Campo Obrigatorio")
    private String name;

    @NotNull(message = "Campo Obrigatorio")
    private Integer age;


}
