package com.example.ShowTime.Service;

import com.example.ShowTime.Entities.ShowEntity;
import com.example.ShowTime.Entities.ShowSeatEntity;
import com.example.ShowTime.Entities.TicketEntity;
import com.example.ShowTime.Entities.UserEntity;
import com.example.ShowTime.EntryDtos.TicketEntryDto;
import com.example.ShowTime.Repository.ShowRepository;
import com.example.ShowTime.Repository.TicketRepository;
import com.example.ShowTime.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public ResponseEntity addTicket(TicketEntryDto ticketEntryDto) throws Exception {
        TicketEntity ticketEntity = new TicketEntity();
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());

        List<String> seatToBeBook = ticketEntryDto.getSeatToBeBook();
        int multiChecks = allocateSeats(seatToBeBook, showEntity);
        if (multiChecks == 0) throw new Exception("You have entered non existing seat number !");
        if (multiChecks == -1) throw new Exception("Some of the seats are already booked");

        String bookedSeats = "";
        for (String s : seatToBeBook) {
            if (!bookedSeats.isEmpty()) bookedSeats += ',';
            bookedSeats += s;
        }

        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setPrice(seatToBeBook.size() * showEntity.getSeatPrice());
        ticketEntity.setShowDate(showEntity.getLocalDate());
        ticketEntity.setShowTime(showEntity.getLocalTime());
        ticketEntity.setTheatreName(showEntity.getTheatreEntity().getTheatreName());

        showEntity.getTicketEntityList().add(ticketEntity);
        ticketEntity.setShowEntity(showEntity);

        UserEntity user = userRepository.findById(ticketEntryDto.getShowId()).get();
        user.getBookedTicketList().add(ticketEntity);
        ticketEntity.setUserEntity(user);
        ticketEntity.setShowEntity(showEntity);

        ticketRepository.save(ticketEntity);
        showRepository.save(showEntity);
        userRepository.save(user);

        return new ResponseEntity<>("Tickets booked successfully", HttpStatus.CREATED);
    }

    public int allocateSeats(List<String> seat, ShowEntity showEntity) {
        List<ShowSeatEntity> showEntityList = showEntity.getShowSeatEntityList();
        int count = 0;
        for(ShowSeatEntity showSeatEntity : showEntityList) {
            if(seat.contains(showSeatEntity.getSeatsNo())) {
                if(showSeatEntity.isBooked()) return -1;
                count ++;
            }
        }
        if(count != seat.size()) return 0;

        markSeatBooked(seat, showEntity);
        return 1;
    }

    private void markSeatBooked(List<String> seat, ShowEntity showEntity) {
        for(ShowSeatEntity currSeat : showEntity.getShowSeatEntityList()) {
            if(seat.contains(currSeat.getSeatsNo())) {
                currSeat.setBooked(true);
            }
        }
    }
}
