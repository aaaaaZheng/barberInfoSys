package com.edu.whut.infosys.controller;


import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member;
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

    @PostMapping("")
    public Result addBill(Integer idBarber,Integer idMemeber,Integer idPatternConsumption){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        return billService.addBill(idBarber,idMemeber,idPatternConsumption);
    }
    @RequestMapping(value = "/turnover" , method = RequestMethod.GET)
    public Result countMoney(){
        Object idbarber = request.getSession().getAttribute("idbarber");
        if (idbarber==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }else {
            Barber b = new Barber();
            b.setIdbarber((Integer) idbarber);
            return billService.countMoney(b);
        }
    }

    @RequestMapping(value = "/barber" , method = RequestMethod.GET)
    public Result findAllByBarber(){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        Integer id = (Integer)request.getSession().getAttribute("idbarber");
        Barber b = new Barber();
        b.setIdbarber(id);
        return billService.findBillByBarber(b);
    }
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public Result findAllByMember(Member member){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        return billService.findBillByMember(member);
    }

}
