package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {

    @Query(value = "SELECT * FROM cart_item WHERE status ='1';", nativeQuery = true)
    List<CartItems> findAllCartItems();

    @Query(value = "SELECT * FROM cart_item where shopping_cart_id = :shoppingCartId AND status ='1' ", nativeQuery = true)
    List<CartItems> findAllByShoppingCartID(Long shoppingCartId);

    @Modifying
    @Query(value = """
    INSERT INTO cart_item (`shopping_cart_id`, `price`, `product_id`, `product_name`, `product_quantity`, `status`)
    VALUES (:#{#cartItems.shoppingCartId}, :#{#cartItems.price}, :#{#cartItems.productId},:#{#cartItems.productName}, :#{#cartItems.productQuantity}, :#{#cartItems.status})""",
            nativeQuery = true)
    Integer saveCartItems(@Param("cartItems") CartItems cartItems);

    @Modifying
    @Query(value = """
        DELETE FROM cart_item WHERE shopping_cart_id =:#{#cartItems.shoppingCartId} AND product_name = :#{#cartItems.productName}""", nativeQuery = true)
    Integer removeCartItem(@Param("cartItems")CartItems cartItem);

    @Modifying
    @Query(value = """
            DELETE FROM cart_item WHERE shopping_cart_id =:shoppingCartId""", nativeQuery = true)
    Integer DeleteByShoppingCartId(Long shoppingCartId);

    @Modifying
    @Query(value = """
            UPDATE cart_item SET status = '0' WHERE cart_item_id =:#{#cartItems.cartItemId}""", nativeQuery = true)
    Integer updateStatus(@Param("cartItems")CartItems cartItem);
}
