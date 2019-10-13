package com.edu.whut.infosys.serivce;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;

/**
 * @author aaaaaaa
 */
public interface BarberService {
    /***
     * 添加新barber
     * @param barber
     * @param bossName
     * @return
     */
    Result addBarber(Barber barber,String bossName);

    /***
     * 根据老板命查找barberid
     * @param username
     * @return
     */
    Integer findIdBarberByBossUsername(String username);

    /***
     * 通过idbarber查找理发店
     * @param idbarber
     * @return
     */
    Barber findBarberByidbarber(Integer idbarber);

}
