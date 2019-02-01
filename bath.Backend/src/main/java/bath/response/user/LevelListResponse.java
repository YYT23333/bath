package bath.response.user;

import bath.entity.user.Level;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class LevelListResponse extends Response {
	private List<LevelItem> levels;

	public LevelListResponse() {
	}

	public LevelListResponse(List<Level> levels) {
		this.levels=new ArrayList<>();
		if(levels!=null && levels.size()>0){
			for(Level temp:levels){
				this.levels.add(new LevelItem(temp));
			}
		}
	}

	public List<LevelItem> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelItem> levels) {
		this.levels = levels;
	}
}
