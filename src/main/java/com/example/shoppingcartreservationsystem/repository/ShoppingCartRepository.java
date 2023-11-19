package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.CartInfo;
import com.example.shoppingcartreservationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartInfo, Long> {

//    @Query(value = "SELECT * FROM shopping_cart WHERE cart_id = :cart_id", nativeQuery = true)
    List<CartInfo> findAll();

    @Query(value = "SELECT * FROM shopping_cart WHERE cart_id = :cart_id", nativeQuery = true)
    CartInfo findByShoppingCartID(Long cart_id);


    @Query(value = """
            SELECT s.cart_id FROM shopping_cart s
            INNER JOIN users u ON s.user_id = u.user_id and u.user_name = :userName""", nativeQuery = true)
    Long findByUserName(String userName);

    @Modifying
    @Query(value = """
            INSERT INTO `shoppingcartdb`.`shopping_cart` (`status`, `user_id`)
            VALUES (:#{#cartInfo.status}, :userId)""",
            nativeQuery = true)
    Integer saveCartInfo(@Param("cartInfo") CartInfo cartInfo, @Param("userId") Long userId);

    @Modifying
    @Query(value = """
            UPDATE shopping_cart SET status =:#{#cartInfo.status} WHERE cart_id = :#{#cartInfo.cartId}""",
            nativeQuery = true)
    Integer updateCartStatus(CartInfo cartInfo);

    @Modifying
    @Query(value = """
                      DELETE FROM shopping_cart WHERE cart_id = :cartId""",
            nativeQuery = true)
    Integer deleteCartInfo(Long cartId);

    List<CartInfo> findByCartId(Long CartId);

//    List<User> findByUser(User user);


    List<CartInfo> findByUser(User user);


}