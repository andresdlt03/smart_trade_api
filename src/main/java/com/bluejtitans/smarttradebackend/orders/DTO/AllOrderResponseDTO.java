package com.bluejtitans.smarttradebackend.orders.DTO;

import java.util.List;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class AllOrderResponseDTO {
    List<OrderResponseDTO> allOrders;
}
