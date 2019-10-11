package com.edu.whut.infosys.serivce;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.PatternConsumption;

/**
 * @author aaaaaaa
 */
public interface PatternConsumptionService {

    /***
     * 添加消费方式
     * @param patternConsumption
     * @return
     */
    Result addPattern(PatternConsumption patternConsumption);

    /***
     * 查找所有消费方式
     * @return
     */
    Result findAllPattern();

    /***
     * 删除消费方式
     * @param patternConsumption
     * @return
     */
    Result delPattern(PatternConsumption patternConsumption);

}
