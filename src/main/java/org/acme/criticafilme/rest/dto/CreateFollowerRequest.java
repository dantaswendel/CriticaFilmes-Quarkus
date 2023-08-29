package org.acme.criticafilme.rest.dto;


import lombok.Data;
import org.acme.criticafilme.domain.domain.User;

@Data
public class CreateFollowerRequest {

    private User follower;
    private User profile;
}
