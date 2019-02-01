package bath.response.exchangeRecords;

import bath.entity.integral.ExchangeRecord;
import bath.response.Response;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRecordListResponse extends Response {
    private List<ExchangeRecordItem> exchangeRecordItemList;
    public ExchangeRecordListResponse(){}
    public ExchangeRecordListResponse(List<ExchangeRecord> exchangeRecords){
        this.exchangeRecordItemList=new ArrayList<>();
        if(exchangeRecords!=null && exchangeRecords.size()>0){
            for(ExchangeRecord temp:exchangeRecords){
                this.exchangeRecordItemList.add(new ExchangeRecordItem(temp));
            }
        }
    }

    public List<ExchangeRecordItem> getExchangeRecordItemList() {
        return exchangeRecordItemList;
    }

    public void setExchangeRecordItemList(List<ExchangeRecordItem> exchangeRecordItemList) {
        this.exchangeRecordItemList = exchangeRecordItemList;
    }
}
