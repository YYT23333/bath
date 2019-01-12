package bath.response;

import com.google.gson.annotations.Expose;

public class InfoResponse extends Response {
	@Expose
	private String info;

	public InfoResponse(){
		this.info = "success";
	}

	public InfoResponse(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
