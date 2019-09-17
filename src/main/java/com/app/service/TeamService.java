package com.app.service;

import com.app.dto.TeamDto;
import com.app.exception.AppException;
import com.app.mapper.ModelMapper;
import com.app.model.Team;
import com.app.repository.PlayerRepository;
import com.app.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamDto add( TeamDto teamDto ) {
        if ( teamDto == null ) {
            throw new AppException("team object is null");
        }

        Team team = ModelMapper.fromTeamDtoToTeam(teamDto);
        return ModelMapper.fromTeamToTeamDto(teamRepository.save(team));
    }

    public List<TeamDto> findAll() {
        return teamRepository
                .findAll()
                .stream()
                .map(ModelMapper::fromTeamToTeamDto)
                .collect(Collectors.toList());
    }

    public TeamDto findOne(Long id) {

        if ( id == null ) {
            throw new AppException("id is null");
        }

        return teamRepository
                .findById(id)
                .map(ModelMapper::fromTeamToTeamDto)
                .orElseThrow(() -> new AppException("no team for id " + id));

    }

    public TeamDto update( Long id, TeamDto teamDto ) {
        if ( id == null ) {
            throw new AppException("id is null");
        }

        if ( teamDto == null ) {
            throw new AppException("team is null");
        }

        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no team with id " + id));

        team.setName(teamDto.getName() == null ? team.getName() : teamDto.getName());
        team.setPoints(teamDto.getPoints() == null ? team.getPoints() : teamDto.getPoints());
        return ModelMapper.fromTeamToTeamDto(teamRepository.save(team));
    }

    public TeamDto updateOnlyName(Long id, Map<String, String> params) {
        if ( id == null ) {
            throw new AppException("id is null");
        }

        if ( params == null ) {
            throw new AppException("params map is null");
        }

        if ( !params.containsKey("name") ) {
            throw new AppException("params map doesn't contain name key");
        }

        String newName = params.get("name");

        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no team with id " + id));

        team.setName(newName == null ? team.getName() : newName);
        return ModelMapper.fromTeamToTeamDto(teamRepository.save(team));
    }

    public TeamDto delete ( Long id ) {

        if ( id == null ) {
            throw new AppException("id is null");
        }

        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new AppException("no team with id " + id));

        playerRepository
                .findAllByTeam_Id(team.getId())
                .forEach(p -> p.setTeam(null));

        teamRepository.delete(team);
        return ModelMapper.fromTeamToTeamDto(team);
    }


}
