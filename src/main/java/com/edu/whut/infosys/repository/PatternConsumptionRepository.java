package com.edu.whut.infosys.repository;

import com.edu.whut.infosys.bean.entity.PatternConsumption;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author aaaaaaa
 */
@org.springframework.stereotype.Repository
public interface PatternConsumptionRepository extends Repository<PatternConsumption,Integer> {


    /***
     * 根据名称寻找消费方式
     * @param id
     * @return
     */
    PatternConsumption findByIdpatternConsumption(Integer id);
    /***
     * 根据名称寻找消费方式
     * @param Name
     * @return
     */
    PatternConsumption findByName(String Name);
    /***
     * 查找所有消费方式
     * @return
     */
    List<PatternConsumption> findAll();
    /***
     * 保存消费方式
     * @param patternConsumption
     * @return
     */
    PatternConsumption save(PatternConsumption patternConsumption);

    /***
     * 根据名称删除
     * @param name
     * @return
     */
    PatternConsumption deleteByName(String name);

    /**
     * 删除名称
     * @param patternConsumption
     * @return
     */
    PatternConsumption delete(PatternConsumption patternConsumption);

}
