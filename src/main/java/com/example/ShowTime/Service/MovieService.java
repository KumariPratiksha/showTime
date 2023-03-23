package com.example.ShowTime.Service;

import com.example.ShowTime.Entities.MovieEntity;
import com.example.ShowTime.EntryDtos.MovieEntryDto;
import com.example.ShowTime.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ShowTime.Convertors.MovieEntryConverter;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto) {
        MovieEntity movieEntity = MovieEntryConverter.movieEntryConvertor(movieEntryDto);
        movieRepository.save(movieEntity);
        return "Successfully Added";
    }

}
