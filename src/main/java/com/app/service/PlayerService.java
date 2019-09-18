package com.app.service;

import com.app.dto.PlayerDto;
import com.app.dto.TeamDto;
import com.app.exception.AppException;
import com.app.mapper.ModelMapper;
import com.app.model.Player;
import com.app.repository.PlayerRepository;
import com.app.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerDto add(PlayerDto playerDto) {

        if (playerDto == null) {
            throw new AppException("player object is null");
        }

        Player player = ModelMapper.fromPlayerDtoToPlayer(playerDto);
        return ModelMapper.fromPlayerToPlayerDto(player);
    }

    public List<PlayerDto> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(ModelMapper::fromPlayerToPlayerDto)
                .collect(Collectors.toList());
    }

    public PlayerDto findOne(Long id) {

        if (id == null) {
            throw new AppException("id is null");
        }
        return playerRepository.findById(id)
                .map(ModelMapper::fromPlayerToPlayerDto)
                .orElseThrow(() -> new AppException("no player for id " + id));
    }

    public PlayerDto update(Long id, PlayerDto playerDto) {

        if (id == null) {
            throw new AppException("id is null");
        }

        if (playerDto == null) {
            throw new AppException("player is null");
        }

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new AppException("no player for id " + id));

        player.setName(playerDto.getName() != null ? playerDto.getName() : null);
        player.setGoals(playerDto.getGoals() != null ? playerDto.getGoals() : null);
        player.setTeam(playerDto.getTeamDto() != null ? ModelMapper.fromTeamDtoToTeam(playerDto.getTeamDto()) : null);

        return ModelMapper.fromPlayerToPlayerDto(playerRepository.save(player));

    }

    public PlayerDto updateOnlyName(Long id, Map<String, String> params) {

        if (id == null) {
            throw new AppException("id is null");
        }

        if (params == null) {
            throw new AppException("params map is null");
        }
        if (!params.containsKey("name")) {
            throw new AppException("params map doesn't contain name key");
        }

        String newName = params.get("name");
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new AppException("no player with id " + id));
        player.setName(newName == null ? player.getName() : newName);
        return ModelMapper.fromPlayerToPlayerDto(player);
    }
    public PlayerDto delete ( Long id ) {

        if(id == null){
            throw new AppException("id is null");
        }

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new AppException("no player with id " + id));

//        playerRepository
//                .findAllByTeam_Id(player.getId())
//                .forEach(p -> p.setTeam(null));

        playerRepository.delete(player);
        return ModelMapper.fromPlayerToPlayerDto(player);
    }

}
