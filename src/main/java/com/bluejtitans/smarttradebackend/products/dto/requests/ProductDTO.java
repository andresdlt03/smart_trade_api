package com.bluejtitans.smarttradebackend.products.dto.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    public InfoDTO info;
    public AvailabilityDTO availability;
}