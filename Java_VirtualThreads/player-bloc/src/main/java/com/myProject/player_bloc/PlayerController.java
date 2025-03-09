package com.myProject.player_bloc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    @GetMapping("/players")
    public List<String> getPlayers() throws InterruptedException {
        Thread.sleep(2000);
        return List.of("MSD", "Virat Kohli", "ABD");
    }
}
