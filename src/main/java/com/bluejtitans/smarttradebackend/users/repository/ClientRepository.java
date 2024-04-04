package com.bluejtitans.smarttradebackend.users.repository;

import com.bluejtitans.smarttradebackend.users.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String> {
}
