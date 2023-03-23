package com.example.ShowTime.Entities;

import com.example.ShowTime.Enums.Genre;
import com.example.ShowTime.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie")
public class MovieEntity {
    @Id
    private String movieName;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @JoinColumn
    @ManyToOne
    TheatreEntity theatreEntity;




    private Double rating;
    private Double duration;
}
