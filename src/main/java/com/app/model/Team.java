package com.app.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer points;

    @OneToMany(mappedBy = "team")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Player> players;
}
