package com.bluejtitans.smarttradebackend.lists.DTO;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiftRequestDTO extends RequestDTO{
    private String receiver;
    private LocalDate reminderDate;
}
