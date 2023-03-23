package com.example.ShowTime.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TheatreEntryDto {
    private String theatreName;

    private String location;
    private int standardSeats;
    private int reclinerSeats;
    private int sofaSeats;
    private int vipSeats;
}
