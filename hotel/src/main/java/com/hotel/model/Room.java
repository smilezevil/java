package com.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private Long id;
    private Integer number;
    private String type;
    private Double price;
    @JsonProperty("available")
    private Boolean available;
}