package com.edu.whut.infosys.repository;


import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Boss;
import org.springframework.data.repository.Repository;

/**
 * @author aaaaaaa
 */
@org.springframework.stereotype.Repository
public interface BarberRepository extends Repository<Barber,Integer>{
    /***
     * 根据boss查找barber
     * @param boss
     * @return
     */
    Barber findByBoss(Boss boss);

    /***
     * 根据ID查找barber
     * @param id
     * @return
     */
    Barber findByIdbarber(Integer id);
    /***
     * 保存barber信息
     * @param barber
     * @return
     */
    Barber save(Barber barber);

    /***
     * 根据名字查找
     * @param name
     * @return
     */
    Barber findByName(String name);

}
