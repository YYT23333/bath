package bath.blservice.cart;

import bath.exception.NotExistException;
import bath.response.InfoResponse;

public interface CartBlService {
    /**
     * 用户添加商品到购物车
     * @param openid
     * @param grouponId
     * @param amount
     * @return 是否成功
     */
    InfoResponse addCartItem(String openid,String grouponId,int amount) throws NotExistException;
    /**
     * 用户删除购物车商品
     * @param id
     * @return 是否成功
     */
    InfoResponse deleteCartItem(String id) throws NotExistException;
    /**
     * 用户更改购物车商品数量
     * @param id
     * @param amount
     * @return 是否成功
     */
    InfoResponse updateCartItem(String id,int amount) throws NotExistException;
}
