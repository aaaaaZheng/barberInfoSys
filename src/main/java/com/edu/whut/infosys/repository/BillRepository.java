package com.edu.whut.infosys.repository;

import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Bill;
import com.edu.whut.infosys.bean.entity.Member1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author aaaaaaa
 */
@org.springframework.stereotype.Repository
public interface BillRepository extends Repository<Bill,Integer> {

    /***
     * 找到所有账单
     *
     * @return
     */
    List<Bill> findAll();
    /***
     * 查找一个理发店的所有账单
     * @param barber
     * @return
     */
    List<Bill> findByBarber(Barber barber);
    /***
     * 查找一个会员的所有账单
     * @param member
     * @return
     */
    List<Bill> findByMember(Member1 member);
    /***
     * 保存账单
     * @param bill
     * @return
     */
    Bill save(Bill bill);

    /***
     * 使用sql根据barberid查询
     * @param id
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT CREATE_TIME,member1.name memberName,barber.name barberName,pattern_consumption.name patternName,bill.amount FROM ((bill  join barber on bill.idbarber = barber.idbarber)  join member1 on bill.idmemeber = member1.idmember )  join pattern_consumption on bill.idpattern_consumption = pattern_consumption.idpattern_consumption WHERE bill.idbarber = ?1 ")
    List<Object> findByidBarber(Integer id);

    /**
     * 使用sql根据memberid查询
     * @param id
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT CREATE_TIME,member1.name memberName,barber.name barberName,pattern_consumption.name patternName,bill.amount FROM ((bill  join barber on bill.idbarber = barber.idbarber)  join member1 on bill.idmemeber = member1.idmember )  join pattern_consumption on bill.idpattern_consumption = pattern_consumption.idpattern_consumption WHERE bill.idmemeber = ?1 ")
    List<Object> findByidMember(Integer id);
}
