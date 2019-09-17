package com.app;

import com.app.model.Player;
import com.app.model.Team;
import com.app.repository.PlayerRepository;
import com.app.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringbootRestServerApplication implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var t1 = Team.builder().name("REAL").points(20).build();
        var t2 = Team.builder().name("PSG").points(30).build();

        var p1 = Player.builder().name("ADAM").goals(30).team(t1).build();
        var p2 = Player.builder().name("PAWEL").goals(40).team(t1).build();
        var p3 = Player.builder().name("IGOR").goals(35).team(t2).build();
        var p4 = Player.builder().name("EDWARD").goals(31).team(t2).build();

        playerRepository.saveAll(List.of(p1, p2, p3, p4));
    }
}
