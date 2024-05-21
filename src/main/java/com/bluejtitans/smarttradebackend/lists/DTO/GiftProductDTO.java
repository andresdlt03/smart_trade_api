package com.bluejtitans.smarttradebackend.lists.DTO;

import com.bluejtitans.smarttradebackend.products.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftProductDTO {
    private String receiver;
    private List<PersonGiftsDTO> personGifts;
    public GiftProductDTO(String receiver){
        this.receiver = receiver;
        this.personGifts = new ArrayList<>();
    }
}
