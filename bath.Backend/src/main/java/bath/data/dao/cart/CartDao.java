package bath.data.dao.cart;

import bath.entity.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, String> {
}
