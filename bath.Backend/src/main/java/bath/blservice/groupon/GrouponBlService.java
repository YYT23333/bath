package bath.blservice.groupon;

import bath.exception.NotExistException;
import bath.response.AddResponse;
import bath.response.InfoResponse;
import bath.response.groupon.*;

import java.util.Date;

public interface GrouponBlService {

    /**
     * 管理员添加团购
     * @param name
     * @param originalPrice
     * @param price
     * @param takeEffectTime
     * @param loseEffectTime
     * @param putOnShelvesTime
     * @param pullOffShelvesTime
     * @param description
     * @param amount
     * @return 是否成功
     */
    AddResponse addGroupon(String name, double originalPrice, double price, Date takeEffectTime, Date loseEffectTime, Date putOnShelvesTime, Date pullOffShelvesTime, String description, int amount, String type,String image);

    /**
     * 管理员删除团购
     * @param id
     * @return
     */
    InfoResponse deleteGroupon(String id)throws NotExistException;
   /**
    * 管理员修改团购
    * @param id
    * @param name
    * @param originalPrice
    * @param price
    * @param takeEffectTime
    * @param loseEffectTime
    * @param putOnShelvesTime
    * @param pullOffShelvesTime
    * @param description
    * @param amount
    * @return 是否成功
    */
    InfoResponse updateGroupon(String id,String name, double originalPrice, double price, Date takeEffectTime,Date loseEffectTime, Date putOnShelvesTime,Date pullOffShelvesTime,String description,int amount,String type,String image)throws NotExistException;

    /**
     * 管理员上架团购
     * @param id
     * @return 是否成功
     */
    InfoResponse putOnShelves(String id)throws NotExistException;

    /**
     * 管理员下架团购
     * @param id
     * @return 是否成功
     */
    InfoResponse pullOffShelves(String id)throws NotExistException;

    /**
     * 通过id查找团购
     * @param id
     * @return 团购
     */
    GrouponResponse findById(String id)throws NotExistException;

    /**
     * 查找所有的普通团购
     * @param
     * @return 所有的普通团购
     */
    GrouponListResponse getAllOrdinary();

    /**
     * 查找所有的积分兑换团购
     * @param
     * @return 所有的积分兑换团购
     */
    GrouponListResponse getAllIntegral();

    /**
     * 查找已经上架的普通团购
     * @param
     * @return 所有已经上架的普通团购
     */
    GrouponListResponse getAvailableOrdinaryGroupon()throws NotExistException;

    /**
     * 查找已经上架的积分兑换团购
     * @param
     * @return 所有已经上架的积分兑换团购
     */
    GrouponListResponse getAvailableIntegralGroupon()throws NotExistException;
}
