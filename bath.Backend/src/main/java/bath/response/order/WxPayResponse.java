package bath.response.order;

import bath.response.Response;

public class WxPayResponse extends Response {
	private WxPayItem wxBuyCredit;

	public WxPayResponse(WxPayItem wxBuyCredit) {
		this.wxBuyCredit = wxBuyCredit;
	}

	public WxPayItem getWxBuyCredit() {
		return wxBuyCredit;
	}

	public void setWxBuyCredit(WxPayItem wxBuyCredit) {
		this.wxBuyCredit = wxBuyCredit;
	}
}
