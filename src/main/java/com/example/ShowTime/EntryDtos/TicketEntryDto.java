package com.example.ShowTime.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class TicketEntryDto {
    private int price;
    private int showId;
    // 1Rec, 2Rec
    private List<String> seatToBeBook;

    private int userId;
}

