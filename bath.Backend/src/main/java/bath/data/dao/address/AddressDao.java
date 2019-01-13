package bath.data.dao.address;

import bath.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, String> {
}
