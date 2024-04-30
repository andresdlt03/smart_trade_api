package com.bluejtitans.smarttradebackend.lists.model;

import java.util.ArrayList;
import java.util.List;
import com.bluejtitans.smarttradebackend.users.model.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Gift")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "gift")
    private Client client;

    @OneToMany(mappedBy = "gift")
    private List<PersonGift> personGifts = new ArrayList<>();
}
