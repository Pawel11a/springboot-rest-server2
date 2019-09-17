package com.app.controller;

import com.app.dto.Info;
import com.app.dto.TeamDto;
import com.app.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public Info<List<TeamDto>> getAll() {
        return Info.<List<TeamDto>>builder()
                .data(teamService.findAll())
                .build();
    }

    @GetMapping("/{id}")
    public TeamDto getOne(@PathVariable Long id) {
        return teamService.findOne(id);
    }

    @PostMapping
    public TeamDto add(@RequestBody TeamDto teamDto) {
        return teamService.add(teamDto);
    }

    @PutMapping("/{id}")
    public TeamDto update(@PathVariable Long id, @RequestBody TeamDto teamDto) {
        return teamService.update(id, teamDto);
    }

    @PatchMapping("/{id}")
    public TeamDto updateOnlyName(@PathVariable Long id, @RequestBody Map<String, String> params) {
        return teamService.updateOnlyName(id, params);
    }

    @DeleteMapping("/{id}")
    public TeamDto delete(@PathVariable Long id) {
        return teamService.delete(id);
    }



}
