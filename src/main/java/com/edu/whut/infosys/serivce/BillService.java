package com.edu.whut.infosys.serivce;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member1;

/**
 * @author aaaaaaa
 */
public interface BillService {
    /***
     * 添加新订单
     * @param idBarber
     * @param idMemeber
     * @param idPatternConsumption
     * @return
     */
    Result addBill(Integer idBarber,Integer idMemeber,Integer idPatternConsumption);

    /***
     * 统计一个理发店的所有金额
     * @param barber
     * @return
     */
    Result countMoney(Barber barber);

    /***
     * 查找一个理发店的所有账单
     * @param barber
     * @return
     */
    Result findBillByBarber(Barber barber);

    /***
     * 查找一个会员的所有账单
     * @param member
     * @return
     */
    Result findBillByMember(Member1 member);
}
