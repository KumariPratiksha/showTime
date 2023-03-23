package com.example.ShowTime.Convertors;

import com.example.ShowTime.Entities.MovieEntity;
import com.example.ShowTime.EntryDtos.MovieEntryDto;

public class MovieEntryConverter {

    public  static MovieEntity movieEntryConvertor(MovieEntryDto movieDto) {
        return MovieEntity.builder().movieName(movieDto.getMovieName()).duration(movieDto.getDuration()).genre(movieDto.getGenre()).language(movieDto.getLanguage()).rating(movieDto.getRating()).genre(movieDto.getGenre()).build();
    }

}
