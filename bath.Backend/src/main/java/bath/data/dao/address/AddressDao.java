package bath.data.dao.address;

import bath.entity.address.Address;
import bath.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AddressDao extends JpaRepository<Address, String> {
    Optional<Address> findById(int id);
    void deleteById(int id);
    List<Address> findByUser(User user);
}
