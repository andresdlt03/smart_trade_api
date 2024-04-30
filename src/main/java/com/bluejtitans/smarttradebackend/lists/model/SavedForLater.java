package com.bluejtitans.smarttradebackend.lists.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Saved_For_Later")
public class SavedForLater extends ProductList{

}
