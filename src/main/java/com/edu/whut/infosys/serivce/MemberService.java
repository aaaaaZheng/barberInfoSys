package com.edu.whut.infosys.serivce;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member1;

/**
 * @author aaaaaaa
 */
public interface MemberService {
    /***
     * 添加新会员
     * @param member
     * @param idbarber
     * @return
     */
    Result addMember(Member1 member, Integer idbarber);

    /***
     * 会员充值
     * @param id
     * @param num
     * @return
     */
    Result topUp(Integer id,double num);

    /***
     * 会员退费
     * @param id
     * @param num
     * @return
     */
    Result returnMoney(Integer id,double num);

    /***
     * 删除会员
     * @param id
     * @return
     */
    Result delMember(Integer id);

    /***
     * 查找一个理发店的所有会员
     * @param barber
     * @return
     */
    Result findAllMemberByBarber(Barber barber);

    /***
     * 根据会员id查找会员
     * @param idmember
     * @return
     */
    Result findMemberById(Integer idmember);



}
