package com.edu.whut.infosys.controller;


import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.serivce.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
