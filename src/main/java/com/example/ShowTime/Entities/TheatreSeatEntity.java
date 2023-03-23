package com.example.ShowTime.Entities;

import com.example.ShowTime.Enums.SeatTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "theatreSeat")
public class TheatreSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatTypes seatType;

    private String seatNo;

    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;


}
