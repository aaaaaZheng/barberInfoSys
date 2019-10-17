package com.edu.whut.infosys.serivce.impl;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Boss;
import com.edu.whut.infosys.repository.BarberRepository;
import com.edu.whut.infosys.repository.BossRepository;
import com.edu.whut.infosys.serivce.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aaaaaaa
 */
@Service
public class BarberServiceImpl implements BarberService {
    @Autowired
    BarberRepository barberRepository;
    @Autowired
    BossRepository bossRepository;
    /***
     * 添加理发店
     * @param barber
     * @return
     */
    @Override
    public Result addBarber(Barber barber,String bossName) {
        Result result = new Result();
        if(barber.getName()==null || barber.getAddress()==null  || bossName==null){
            result.setMessage("添加失败，信息不完整");
        }else {
            Boss bossByUsername = bossRepository.findBossByUsername(bossName);
            Barber byName = barberRepository.findByBoss(bossByUsername);
            if(byName!=null){
                result.setMessage("只能添加一个店铺");
            }else{
                barber.setBoss(bossByUsername);
                Barber newBarber = barberRepository.save(barber);
                bossByUsername.setBarber(newBarber);
                bossRepository.save(bossByUsername);
                result.setSuccess(true);
                result.setMessage("添加成功");
            }
        }
        return result;
    }

    @Override
    public Integer findIdBarberByBossUsername(String username) {
        Boss boss = bossRepository.findBossByUsername(username);
        if(boss==null){
            return null;
        }else {
            Barber aBarber = barberRepository.findByBoss(boss);
            if(aBarber==null){
                return null;
            }else{
                return aBarber.getIdbarber();
            }
        }

    }

    @Override
    public Barber findBarberByidbarber(Integer idbarber) {
        return barberRepository.findByIdbarber(idbarber);
    }
}
