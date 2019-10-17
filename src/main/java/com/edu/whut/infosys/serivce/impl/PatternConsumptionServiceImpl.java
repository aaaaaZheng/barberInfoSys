package com.edu.whut.infosys.serivce.impl;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.PatternConsumption;
import com.edu.whut.infosys.repository.BarberRepository;
import com.edu.whut.infosys.repository.PatternConsumptionRepository;
import com.edu.whut.infosys.serivce.PatternConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaaaaaa
 */
@Service
public class PatternConsumptionServiceImpl implements PatternConsumptionService {
    @Autowired
    PatternConsumptionRepository patternConsumptionRepository;
    @Autowired
    BarberRepository barberRepository;
    @Override
    public Result addPattern(PatternConsumption patternConsumption,Integer idbarber) {
        Result result = new Result();
       if(patternConsumption.getName()==null || patternConsumption.getAmount()==null){
           result.setMessage("方案不完整");
       }else{
           Barber a = barberRepository.findByIdbarber(idbarber);
           PatternConsumption byName = patternConsumptionRepository.findByNameAndBarber(patternConsumption.getName(),a);
           if(byName!=null){
               result.setMessage("套餐名重复");
           }else{
               patternConsumption.setBarber(a);
               patternConsumptionRepository.save(patternConsumption);
               result.setSuccessAndMsg(true, "添加成功");
           }

       }
        return result;
    }

    @Override
    public Result findAllPattern(Integer idbarber) {
        Result result = new Result();
        Barber a = barberRepository.findByIdbarber(idbarber);
        List<PatternConsumption> patterns = patternConsumptionRepository.findAllByBarber(a);
        result.setSuccessAndMsgAndDetail(true, "查找成功",patterns);
        return result;
    }

    @Override
    public Result delPattern(PatternConsumption patternConsumption,Integer idbarber) {
        Result result = new Result();
        if(patternConsumption.getName()==null){
            result.setMessage("缺失方式名");
        }else{
            Barber aBarber = barberRepository.findByIdbarber(idbarber);
            PatternConsumption a = patternConsumptionRepository.findByNameAndBarber(patternConsumption.getName(), aBarber);
            if(a!=null){
                patternConsumptionRepository.delete(a);
            }
            result.setSuccessAndMsg(true, "删除成功");
        }
        return result;
    }
}
