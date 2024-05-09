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
public class GiftList extends ProductList{
    @OneToMany(mappedBy = "giftList", cascade = CascadeType.ALL)
    private List<PersonGift> personGifts;

    public GiftList(Client client){
        this.setClient(client);
    }
}
