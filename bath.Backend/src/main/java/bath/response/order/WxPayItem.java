package bath.response.order;

public class WxPayItem {
	private String orderId;
	private String timeStamp;
	private String nonceStr;
	private String packageContent; //此处微信命名package与Java关键字冲突，改为packageContent
	private String signType;
	private String paySign;

	public WxPayItem(String orderId, String timeStamp, String nonceStr, String packageContent, String signType, String paySign) {
		this.orderId = orderId;
		this.timeStamp = timeStamp;
		this.nonceStr = nonceStr;
		this.packageContent = packageContent;
		this.signType = signType;
		this.paySign = paySign;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageContent() {
		return packageContent;
	}

	public void setPackageContent(String packageContent) {
		this.packageContent = packageContent;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
