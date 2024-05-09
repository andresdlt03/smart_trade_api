package com.bluejtitans.smarttradebackend.lists.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bluejtitans.smarttradebackend.lists.model.*;
import java.util.Optional;

@Repository
public interface ProductListRepository extends CrudRepository<ProductList, Long>{
    Optional<Wishlist> findWishlistByClientEmail(String clientEmail);
    Optional<SavedForLater> findSavedForLaterByClientEmail(String clientEmail);
    Optional<GiftList> findGiftListByClientEmail(String clientEmail);
    Optional<ShoppingCart> findShoppingCartByClientEmail(String clientEmail);
}