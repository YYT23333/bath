package bath.dataservice.user;

import bath.entity.user.Level;
import bath.exception.NotExistException;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface LevelDataService {

	String add(Level level);

	Level findByName(String name) throws NotExistException;

	List<Level> getAll();

	void update(Level level)throws NotExistException;

	void deleteByName(String name) throws NotExistException;
}
