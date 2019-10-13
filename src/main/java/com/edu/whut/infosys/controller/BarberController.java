package com.edu.whut.infosys.controller;


import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.serivce.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aaaaaaa
 */
@RestController
@RequestMapping("/barber")
public class BarberController {
    @Autowired
    BarberService barberService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    /***
     * 添加理发店
     * @param barber
     * @return
     * value = "/"
     */
    @PostMapping()
    public Result add(Barber barber){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        Result result1 = barberService.addBarber(barber,(String) username);
        if(result1.isSuccess()==true){
            Integer idBarberByBossUsername = barberService.findIdBarberByBossUsername((String) username);
            request.getSession().setAttribute("idbarber", idBarberByBossUsername);
        }
        return result1;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getBarber(){
        Object username = request.getSession().getAttribute("username");
        Object idbarber = request.getSession().getAttribute("idbarber");
        Result result = new Result();
        System.out.println(username);
        System.out.println(request.getSession().getAttribute("isLogin"));
        System.out.println("获取barber"+request.getSession().getId());

        if (username==null){
            result.setMessage("请先登录");
            return result;
        }
        if(idbarber==null){
            result.setMessage("还未添加店铺信息");
            return result;
        }
        Barber barber = barberService.findBarberByidbarber((Integer) idbarber);
        result.setSuccess(true);
        result.setMessage("获得理发店信息成功");
        result.setDetail(barber);
        return result;
    }

}
