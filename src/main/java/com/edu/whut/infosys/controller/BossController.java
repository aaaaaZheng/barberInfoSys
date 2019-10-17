package com.edu.whut.infosys.controller;


import com.edu.whut.infosys.bean.ChangeBossForm;
import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Boss;
import com.edu.whut.infosys.serivce.BarberService;
import com.edu.whut.infosys.serivce.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author aaaaaaa
 */
@RestController
@RequestMapping("/boss")
public class BossController {
    @Autowired
    private BossService bossService;
    @Autowired
    private BarberService barberService;

    /***
     * 相应注册请求，返回注册结果json格式
     * @param boss
     * @return
     */
    @PostMapping()
    public Result register(Boss boss){
        return bossService.register(boss);
    }

    /***
     * 处理登录请求，返回登录结果
     * @param boss
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(Boss boss, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Result loginResult = bossService.login(boss);
        if(loginResult.isSuccess()){
            session.setAttribute("isLogin",true);
            System.out.println("登陆"+request.getSession().getId());
            System.out.println(boss.getUsername());
            session.setAttribute("username",boss.getUsername());
            System.out.println(barberService.findIdBarberByBossUsername(boss.getUsername()));
            Barber barber = bossService.findByUsername(boss.getUsername()).getBarber();
            if(barber!=null){
                session.setAttribute("idbarber",barber.getIdbarber());
            }

            Cookie cookie = new Cookie("sessionId", session.getId());
            response.addCookie(cookie);
        }
        return loginResult;
    }

    /**
     * 处理修改密码请求，返回请求结果
     * @param changeBossForm
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/password",method = RequestMethod.PUT)
    public Result changePassword(ChangeBossForm changeBossForm, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object isLogin = session.getAttribute("isLogin");
        changeBossForm.setUsername((String) session.getAttribute("username"));
        return bossService.changePassword(changeBossForm);
        /*if(isLogin!=null && (boolean)isLogin ==true) {

        }else{
            Result result = new Result();
            result.setMessage("请登录");
            return result;
        }*/

    }
}
