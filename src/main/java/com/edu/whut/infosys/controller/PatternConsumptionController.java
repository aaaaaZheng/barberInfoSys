package com.edu.whut.infosys.controller;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.PatternConsumption;
import com.edu.whut.infosys.serivce.PatternConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aaaaaaa
 */
@RestController
@RequestMapping("/pattern")
public class PatternConsumptionController {
    @Autowired
    PatternConsumptionService patternConsumptionService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /***
     * 添加消费套餐
     * @param patternConsumption
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(PatternConsumption patternConsumption){
        Object username = request.getSession().getAttribute("username");
        /*if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }*/
        Object idbarber = request.getSession().getAttribute("idbarber");
        if(idbarber==null){
            Result result = new Result();
            result.setMessage("还未添加店铺信息");
            return result;
        }
        return patternConsumptionService.addPattern(patternConsumption,(Integer) idbarber);
    }

    /***
     * 查找所有消费套餐
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        Object username = request.getSession().getAttribute("username");
        /*if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }*/
        Object idbarber = request.getSession().getAttribute("idbarber");
        if(idbarber==null){
            Result result = new Result();
            result.setMessage("还未添加店铺信息");
            return result;
        }
        return patternConsumptionService.findAllPattern((Integer) idbarber);
    }

    /***
     * 删除消费套餐
     * @param patternConsumption
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    private Result del(PatternConsumption patternConsumption){
        Object username = request.getSession().getAttribute("username");
        /*if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }*/
        Object idbarber = request.getSession().getAttribute("idbarber");
        if(idbarber==null){
            Result result = new Result();
            result.setMessage("还未添加店铺信息");
            return result;
        }
        return patternConsumptionService.delPattern(patternConsumption,(Integer) idbarber);
    }
}
