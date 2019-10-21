package com.edu.whut.infosys.controller;


import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member1;
import com.edu.whut.infosys.serivce.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aaaaaaa
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /***
     * 添加订单
     * @param idmember
     * @param idpattern
     * @return
     */
    @PostMapping()
    public Result addBill(Integer idmember,Integer idpattern){
        Object username = request.getSession().getAttribute("username");
        Object idbarber = request.getSession().getAttribute("idbarber");
        if(idbarber==null){
            Result result = new Result();
            result.setMessage("请先添加商户");
            return result;
        }
        return billService.addBill((Integer) idbarber,idmember,idpattern);
    }

    /***
     * 计算所有营业额
     * @return
     */
    @RequestMapping(value = "/turnover" , method = RequestMethod.GET)
    public Result countMoney(){
        Object username = request.getSession().getAttribute("username");
        Object idbarber = request.getSession().getAttribute("idbarber");
        Barber b = new Barber();
        b.setIdbarber((Integer) idbarber);
        return billService.countMoney(b);

    }

    /***
     * 根据店铺查询bill
     * @return
     */
    @RequestMapping(value = "/barber" , method = RequestMethod.GET)
    public Result findAllByBarber(){
        Object username = request.getSession().getAttribute("username");
        Integer id = (Integer)request.getSession().getAttribute("idbarber");
        Barber b = new Barber();
        b.setIdbarber(id);
        return billService.findBillByBarber(b);
    }

    /***
     * 根据会员查询当前店铺订单
     * @param member
     * @return
     */
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public Result findAllByMember(Member1 member){
        Object username = request.getSession().getAttribute("username");
        return billService.findBillByMember(member);
    }

}
