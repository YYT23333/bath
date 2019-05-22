package bath.data.dao.groupon;

import bath.entity.groupon.Groupon;
import bath.publicdatas.grouponType.GrouponType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrouponDao extends JpaRepository<Groupon, String> {
    List<Groupon> findByType(GrouponType type);
    List<Groupon> findByName(String name);
}
