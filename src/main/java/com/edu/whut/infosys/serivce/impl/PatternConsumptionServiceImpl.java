package com.edu.whut.infosys.serivce.impl;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.PatternConsumption;
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
    @Override
    public Result addPattern(PatternConsumption patternConsumption) {
        Result result = new Result();
       if(patternConsumption.getName()==null || patternConsumption.getAmount()==null){
           result.setMessage("方案不完整");
       }else{
           patternConsumptionRepository.save(patternConsumption);
           result.setSuccessAndMsg(true, "添加成功");
       }
        return result;
    }

    @Override
    public Result findAllPattern() {
        Result result = new Result();
        List<PatternConsumption> patterns = patternConsumptionRepository.findAll();
        result.setSuccessAndMsgAndDetail(true, "查找成功",patterns);
        return result;
    }

    @Override
    public Result delPattern(PatternConsumption patternConsumption) {
        Result result = new Result();
        if(patternConsumption.getName()==null){
            result.setMessage("缺失方式名");
        }else{
            PatternConsumption a = patternConsumptionRepository.findByName(patternConsumption.getName());
            if(a!=null){
                patternConsumptionRepository.delete(a);
            }
            result.setSuccessAndMsg(true, "删除成功");
        }
        return result;
    }
}
