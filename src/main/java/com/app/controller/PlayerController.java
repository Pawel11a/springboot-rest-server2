package com.app.controller;

import com.app.dto.Info;
import com.app.dto.PlayerDto;
import com.app.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public Info<List<PlayerDto>> getAll() {
        return Info.<List<PlayerDto>>builder()
                .data(playerService.findAll())
                .build();
    }

    @GetMapping("/{id}")

    public PlayerDto getOne(@PathVariable Long id) {
        return playerService.findOne(id);
    }

    @PostMapping
    public PlayerDto add(@RequestBody PlayerDto playerDto) {
        return playerService.add(playerDto);
    }

    @PutMapping("/{id}")
    public PlayerDto update(@PathVariable Long id, @RequestBody PlayerDto playerDto) {
        return playerService.update(id, playerDto);
    }

    @PatchMapping("/{id}")
    public PlayerDto updateOnlyName(@PathVariable Long id, @RequestBody Map<String, String> params) {
        return playerService.updateOnlyName(id, params);
    }

    @DeleteMapping("/{id}")
    public PlayerDto delete(@PathVariable Long id) {
        return playerService.delete(id);
    }
}
