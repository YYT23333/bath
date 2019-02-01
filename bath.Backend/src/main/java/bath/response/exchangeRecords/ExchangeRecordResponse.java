package bath.response.exchangeRecords;

import bath.entity.integral.ExchangeRecord;
import bath.response.Response;

public class ExchangeRecordResponse extends Response {
    private ExchangeRecordItem exchangeRecordItem;
    public ExchangeRecordResponse(){}
    public ExchangeRecordResponse(ExchangeRecord exchangeRecord){
        this.exchangeRecordItem=new ExchangeRecordItem(exchangeRecord);
    }

    public ExchangeRecordItem getExchangeRecordItem() {
        return exchangeRecordItem;
    }

    public void setExchangeRecordItem(ExchangeRecordItem exchangeRecordItem) {
        this.exchangeRecordItem = exchangeRecordItem;
    }
}
