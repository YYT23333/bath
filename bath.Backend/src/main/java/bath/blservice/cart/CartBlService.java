package bath.blservice.cart;

import bath.entity.user.User;
import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.cart.CartItemListResponse;
import bath.response.cart.CartItemResponse;

public interface CartBlService {
    /**
     * 用户添加商品到购物车
     * @param openid
     * @param grouponId
     * @param amount
     * @return 是否成功
     */
    AddResponse addCartItem(String openid, String grouponId, int amount) throws NotExistException;
    /**
     * 用户删除购物车商品
     * @param id
     * @return 是否成功
     */
    InfoResponse deleteCartItem(int id) throws NotExistException;
    /**
     * 用户更改购物车商品数量
     * @param id
     * @param amount
     * @return 是否成功
     */
    InfoResponse updateCartItem(int id,int amount) throws NotExistException;

    /**
     * 通过id查找购物车商品
     * @param id
     * @return 购物车商品
     */
    CartItemResponse findById(int id) throws NotExistException;

    /**
     * 通过用户openid，查找用户的所有购物车商品
     * @param openid
     * @return 该用户的所有购物车商品
     */
    CartItemListResponse findByUser(String openid) throws NotExistException;
}
