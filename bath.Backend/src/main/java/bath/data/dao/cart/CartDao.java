package bath.data.dao.cart;

import bath.entity.cart.Cart;
import bath.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartDao extends JpaRepository<Cart, String> {
    List<Cart> findByUser(User user);
    Optional<Cart> findById(int id);
    void deleteById(int id);
}
