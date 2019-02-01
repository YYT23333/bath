package bath.exception;

import bath.response.WrongResponse;

public class LowStocksException extends Exception {
    private WrongResponse response;

    public LowStocksException() {
        super("商品库存不足");
        response = new WrongResponse(10005, this.getMessage());
    }

    public WrongResponse getResponse() {
        return response;
    }
}
