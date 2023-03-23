package com.example.ShowTime.Convertors;

import com.example.ShowTime.Entities.ShowEntity;
import com.example.ShowTime.EntryDtos.ShowEntryDto;

public class ShowEntryConverter {
    public static ShowEntity entryConverter(ShowEntryDto showEntryDto) {
        return ShowEntity.builder().localTime(showEntryDto.getLocalTime()).localDate(showEntryDto.getLocalDate()).showType(showEntryDto.getShowType()).seatPrice(showEntryDto.getSeatPrice()).build();
    }
}