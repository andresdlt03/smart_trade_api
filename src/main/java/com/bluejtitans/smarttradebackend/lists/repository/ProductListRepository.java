package com.bluejtitans.smarttradebackend.lists.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.bluejtitans.smarttradebackend.lists.model.*;
import java.util.Optional;

@Repository
public interface ProductListRepository extends CrudRepository<ProductList, Long>{
    Optional<Wishlist> findWishlistByClientId(String clientEmail);
    Optional<SavedForLater> findSavedForLaterByClientId(String clientEmail);
    Optional<GiftList> findGiftListByClientId(String clientEmail);
    Optional<ShoppingCart> findShoppingCartByClientId(String clientEmail);
}