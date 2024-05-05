package com.bluejtitans.smarttradebackend.lists.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bluejtitans.smarttradebackend.lists.model.*;
import java.util.Optional;

@Repository
public interface ListRepository extends CrudRepository<ProductList, Long>{
    Optional<Wishlist> findWishlistByClientId(String email);
    Optional<SavedForLater> findSavedForLaterByClientId(String email);
    Optional<GiftList> findGiftListByClientId(String email);
    Optional<ShoppingCart> findShoppingCartByClientId(String email);
}