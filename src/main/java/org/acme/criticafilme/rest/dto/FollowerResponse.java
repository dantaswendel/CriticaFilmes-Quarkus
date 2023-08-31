package org.acme.criticafilme.rest.dto;

import lombok.Data;
import org.acme.criticafilme.domain.domain.Follower;

@Data
public class FollowerResponse {


    private  Long id;

    private String name;

    public FollowerResponse() {
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FollowerResponse(Follower follower) {
       this(follower.getId(), follower.getFollower().getName());
    }

}
