package com.edu.whut.infosys.controller;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member;
import com.edu.whut.infosys.serivce.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author aaaaaaa
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(Member member){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        Integer idbarber = new Integer(request.getParameter("idbarber"));
        return memberService.addMember(member,idbarber);
    }
    @RequestMapping(value = "/amount",method = RequestMethod.PUT)
    public Result topUp(Integer idMember,Double num){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        if(num>=0){
            return memberService.topUp(idMember,num);
        }else{
            return memberService.returnMoney(idMember, num);
        }
    }
    @RequestMapping(method = RequestMethod.DELETE)
    public Result del(Integer idmember){
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        return memberService.delMember(idmember);
    }
    @RequestMapping(method = RequestMethod.GET)
    public Result findAllByBarber(){
        Object idbarber = request.getSession().getAttribute("idbarber");
        Object username = request.getSession().getAttribute("username");
        if (username==null){
            Result result = new Result();
            result.setMessage("请先登录");
            return result;
        }
        Barber barber = new Barber();
        barber.setIdbarber((Integer) idbarber);
        return memberService.findAllMemberByBarber(barber);
    }

}
