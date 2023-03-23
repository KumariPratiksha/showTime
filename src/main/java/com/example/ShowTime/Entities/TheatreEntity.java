package com.example.ShowTime.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "theatre")
public class TheatreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String theatreName;

    private String location;

    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    private List<TheatreSeatEntity> theatreSeatEntityList;

    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    List<MovieEntity> movieEntityList;

    @OneToMany(mappedBy = "theatreEntity", cascade = CascadeType.ALL)
    List<ShowEntity> showEntityList;




}
