package bath.exception;

import bath.response.WrongResponse;

public class IntegralDeficiencyException extends Exception{
    private WrongResponse response;

    public IntegralDeficiencyException() {
        super("用户积分不足");
        response = new WrongResponse(10005, this.getMessage());
    }

    public WrongResponse getResponse() {
        return response;
    }
}
