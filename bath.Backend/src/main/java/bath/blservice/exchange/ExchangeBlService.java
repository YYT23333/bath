package bath.blservice.exchange;

import bath.exception.IntegralDeficiencyException;
import bath.exception.LowStocksException;
import bath.exception.NotExistException;
import bath.response.InfoResponse;
import bath.response.exchangeRecords.ExchangeRecordListResponse;
import bath.response.exchangeRecords.ExchangeRecordResponse;

public interface ExchangeBlService {
    /**
     * 获取所有积分兑换记录
     * @param
     * @return 所有积分兑换记录
     */
    ExchangeRecordListResponse getAll();
    /**
     * 通过id查找兑换记录
     * @param id
     * @return 兑换记录
     */
    ExchangeRecordResponse findById(int id) throws NotExistException;
    /**
     * 用户使用积分兑换商品
     * @param openid
     * @param grouponId
     * @return 是否成功
     */
    InfoResponse exchange(String openid, String grouponId) throws NotExistException, IntegralDeficiencyException, LowStocksException;
    /**
     * 通过用户的openid，查找用户的所有积分兑换记录
     * @param openid
     * @return 该用户的所有积分兑换记录
     */
    ExchangeRecordListResponse findByUser(String openid) throws NotExistException;
}
