package com.example.ShowTime.EntryDtos;

import com.example.ShowTime.Enums.Genre;
import com.example.ShowTime.Enums.Language;
import lombok.Data;

@Data
public class MovieEntryDto {
    private String movieName;

    private Language language;

    private Genre genre;

    private Double rating;

    private Double duration;
}
