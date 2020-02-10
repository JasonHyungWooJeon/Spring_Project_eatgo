package kr.co.fastcampus.eatgo.domain;

import java.util.List;

public interface MyMenuItemRepository {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);

}
