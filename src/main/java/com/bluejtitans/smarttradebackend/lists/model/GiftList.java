package com.bluejtitans.smarttradebackend.lists.model;

import java.util.ArrayList;
import java.util.List;

import com.bluejtitans.smarttradebackend.users.model.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
@Component
@Table(name = "Gift")
public class GiftList extends ProductList{
    @OneToMany(mappedBy = "giftList", cascade = CascadeType.ALL)
    private List<PersonGift> personGifts;

    public GiftList() {

    }

    public GiftList(Client client){
        this.setClient(client);
        personGifts = new ArrayList<PersonGift>();
    }
}
