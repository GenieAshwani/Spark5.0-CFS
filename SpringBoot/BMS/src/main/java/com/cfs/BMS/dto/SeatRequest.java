package com.cfs.BMS.dto;


import com.cfs.BMS.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {

    private String seatNumber;
    private String row;
    private Integer col;
    private SeatType seatType;
    private Long screenId;
}
