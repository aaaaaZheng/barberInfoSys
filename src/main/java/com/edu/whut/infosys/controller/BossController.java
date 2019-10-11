package com.edu.whut.infosys.controller;


import com.edu.whut.infosys.bean.ChangeBossForm;
import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Boss;
import com.edu.whut.infosys.serivce.BarberService;
import com.edu.whut.infosys.serivce.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(method = RequestMethod.GET)
    public Result login(Boss boss, HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
       /*Object isLogin = session.getAttribute("isLogin");
        if(isLogin!=null) {
            boolean attribute = (boolean) session.getAttribute("isLogin");
            if (attribute == true) {
                Result result = new Result();
                result.setMsg("用户已登录");
                return result;
            }
        }*/
        Result loginResult = bossService.login(boss);
        if(loginResult.isSuccess()){
            session.setAttribute("isLogin",true);
            session.setAttribute("username",boss.getUsername());
            System.out.println(barberService.findIdBarberByBossUsername(boss.getUsername()));
            Integer idBarberByBossUsername = barberService.findIdBarberByBossUsername(boss.getUsername());
            if(idBarberByBossUsername!=null){
                session.setAttribute("idbarber",idBarberByBossUsername);
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
        if(isLogin!=null && (boolean)isLogin ==true) {
            changeBossForm.setUsername((String) session.getAttribute("username"));
            return bossService.changePassword(changeBossForm);
        }else{
            Result result = new Result();
            result.setMessage("请登录");
            return result;
        }

    }
}
