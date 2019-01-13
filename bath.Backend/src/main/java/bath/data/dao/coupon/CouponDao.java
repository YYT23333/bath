package bath.data.dao.coupon;

import bath.entity.coupon.Coupon;
import bath.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponDao extends JpaRepository<Coupon, String> {
    List<Coupon> findByUser(User user);
}
