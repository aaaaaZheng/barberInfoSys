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
     * @param idbarber
     * @return
     */
    Result addPattern(PatternConsumption patternConsumption,Integer idbarber);

    /***
     * 查找所有消费方式
     * @param idbarber
     * @return
     */
    Result findAllPattern(Integer idbarber);

    /***
     * 删除消费方式
     * @param patternConsumption
     * @param idbarber
     * @return
     */
    Result delPattern(PatternConsumption patternConsumption,Integer idbarber);

}
