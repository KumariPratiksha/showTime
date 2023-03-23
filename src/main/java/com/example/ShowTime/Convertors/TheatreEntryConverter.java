package com.example.ShowTime.Convertors;

import com.example.ShowTime.Entities.TheatreEntity;
import com.example.ShowTime.EntryDtos.TheatreEntryDto;

public class TheatreEntryConverter {
    public static TheatreEntity entryConverter(TheatreEntryDto theatreEntryDto) {
        return TheatreEntity.builder().theatreName(theatreEntryDto.getTheatreName()).location(theatreEntryDto.getLocation()).build();
    }
}