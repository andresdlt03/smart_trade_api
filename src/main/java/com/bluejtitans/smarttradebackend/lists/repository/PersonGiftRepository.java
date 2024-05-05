package com.bluejtitans.smarttradebackend.lists.repository;

import com.bluejtitans.smarttradebackend.lists.model.PersonGift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonGiftRepository extends CrudRepository<PersonGift, Long> {
}
