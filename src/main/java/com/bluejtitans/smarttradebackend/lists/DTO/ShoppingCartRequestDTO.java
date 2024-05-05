package com.bluejtitans.smarttradebackend.lists.DTO;

import java.time.LocalDate;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class ShoppingCartRequestDTO extends RequestDTO{
    private int quantity;
}
