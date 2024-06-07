package com.bluejtitans.smarttradebackend.orders.DTO;

import com.bluejtitans.smarttradebackend.products.model.ProductAvailability;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class ProductReviewDTO {
    String sellerId;

    String productId;

    double valoration;

    String comment;
}
