package org.acme.criticafilme.rest.dto;

import lombok.Data;
import org.acme.criticafilme.domain.domain.Follower;

import java.util.List;

@Data
public class FollowersPerUserResponse {

    private Integer followersaCount;
    private List<FollowerResponse> content;

}
