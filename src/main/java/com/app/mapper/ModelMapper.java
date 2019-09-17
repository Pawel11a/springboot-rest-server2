package com.app.mapper;

import com.app.dto.PlayerDto;
import com.app.dto.TeamDto;
import com.app.model.Player;
import com.app.model.Team;

import java.util.HashSet;

public interface ModelMapper {

    static TeamDto fromTeamToTeamDto(Team team) {
        return team == null ? null : TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .points(team.getPoints())
                .build();
    }

    static Team fromTeamDtoToTeam(TeamDto teamDto) {
        return teamDto == null ? null : Team.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .points(teamDto.getPoints())
                .players(new HashSet<>())
                .build();
    }

    static PlayerDto fromPlayerToPlayerDto(Player player) {
        return player == null ? null : PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .goals(player.getGoals())
                .teamDto(player.getTeam() == null ? null : fromTeamToTeamDto(player.getTeam()))
                .build();
    }

    static Player fromPlayerDtoToPlayer(PlayerDto playerDto) {
        return playerDto == null ? null : Player.builder()
                .id(playerDto.getId())
                .name(playerDto.getName())
                .goals(playerDto.getGoals())
                .team(playerDto.getTeamDto() == null ? null : fromTeamDtoToTeam(playerDto.getTeamDto()))
                .build();
    }


}
