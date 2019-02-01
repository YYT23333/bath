package bath.data.user;

import bath.data.dao.user.LevelDao;
import bath.dataservice.user.LevelDataService;
import bath.entity.user.Level;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelDataServiceImpl implements LevelDataService {
	private final LevelDao levelDao;

	@Autowired
	public LevelDataServiceImpl(LevelDao levelDao) {
		this.levelDao = levelDao;
	}

	@Override
	public String add(Level level) {
		return levelDao.save(level).getName();
	}

	@Override
	public Level findByName(String name) throws NotExistException {
		Optional<Level> optionalLevel = levelDao.findById(name);
		if (optionalLevel.isPresent()) {
			return optionalLevel.get();
		} else {
			throw new NotExistException("Level Name", name);
		}
	}

	@Override
	public List<Level> getAll() {
		return levelDao.findAll();
	}

	@Override
	public void update(Level level) throws NotExistException {
		Optional<Level> optionalLevel = levelDao.findById(level.getName());
		if (optionalLevel.isPresent()) {
			levelDao.save(level);
		} else {
			throw new NotExistException("Level Name", level.getName());
		}
	}

	@Override
	public void deleteByName(String name) throws NotExistException {
		if (levelDao.existsById(name)) {
			levelDao.deleteById(name);
		} else {
			throw new NotExistException("Level Name", name);
		}
	}
}
