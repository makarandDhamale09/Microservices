package com.myproject.team_app;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
public class TeamController {

    private static final Logger log = LoggerFactory.getLogger(TeamController.class);
    private RestClient restClient;

    public TeamController(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8081").build();
    }

    @GetMapping("/")
    public List<String> getPlayersForTeam() {
        log.info("Running on {}", Thread.currentThread());
        return restClient.get().uri("/players").retrieve().toEntity(List.class).getBody();
    }

}
