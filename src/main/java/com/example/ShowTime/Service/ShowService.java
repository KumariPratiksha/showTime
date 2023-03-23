package com.example.ShowTime.Service;

import com.example.ShowTime.Convertors.ShowEntryConverter;
import com.example.ShowTime.Entities.*;
import com.example.ShowTime.EntryDtos.ShowEntryDto;
import com.example.ShowTime.Repository.MovieRepository;
import com.example.ShowTime.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    MovieRepository movieRepository;

    public ResponseEntity addShow(ShowEntryDto showEntryDto) throws Exception {

        ShowEntity showEntity = ShowEntryConverter.entryConverter(showEntryDto);

        boolean toCheckShowOverlap = toCheckShowOverlap(showEntryDto);
        if (!toCheckShowOverlap) throw new Exception("Cannot enter show, conflict with other show!!");

        TheatreEntity theatreEntity = theatreRepository.findById(showEntryDto.getTheatreId()).get();
        theatreEntity.getShowEntityList().add(showEntity);

        MovieEntity movie = movieRepository.findById(showEntryDto.getMovieId()).get();

        showEntity.setMovieEntity(movie);
        showEntity.setTheatreEntity(theatreEntity);

        List<TheatreSeatEntity> theatreSeatEntityList = theatreEntity.getTheatreSeatEntityList();
        List<ShowSeatEntity> showSeatEntityList = createShowSeats(theatreSeatEntityList, showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);

        theatreRepository.save(theatreEntity);
        return new ResponseEntity<>("Show added successfully", HttpStatus.CREATED);





    }

    private boolean toCheckShowOverlap(ShowEntryDto showEntity) {
        LocalTime start = showEntity.getLocalTime();
        LocalTime end = start.plusHours(3);

        TheatreEntity theatreEntity = theatreRepository.findById(showEntity.getTheatreId()).get();
        List<ShowEntity> listOfShows = theatreEntity.getShowEntityList();
        if(listOfShows.isEmpty()) return true;

        for(ShowEntity show : listOfShows) {
            LocalTime newStart = show.getLocalTime();
            LocalTime newEnd = newStart.plusHours(3);
            if(newStart.isAfter(start) && newStart.isBefore(end) || newEnd.isAfter(start) && newEnd.isBefore(end))
                return false;
        }

        return true;
    }

    private List<ShowSeatEntity> createShowSeats(List<TheatreSeatEntity> theatreSeatEntityList, ShowEntity show) {
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for(TheatreSeatEntity theatreSeatEntity : theatreSeatEntityList) {
            ShowSeatEntity seat = ShowSeatEntity.builder().seatsNo(theatreSeatEntity.getSeatNo()).seatTypes(theatreSeatEntity.getSeatType()).theatreSeatEntity(theatreSeatEntity).seatPrice(show.getSeatPrice()).showEntity(show).isBooked(false).build();
            showSeatEntityList.add(seat);
        }

        return showSeatEntityList;
    }



}
