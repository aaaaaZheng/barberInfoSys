package com.edu.whut.infosys.controller;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member1;
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

    /***
     * 添加会员
     * @param member
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(Member1 member){
        Object username = request.getSession().getAttribute("username");
        Integer idbarber =  (Integer) request.getSession().getAttribute("idbarber");
        return memberService.addMember(member,idbarber);
    }

    /***
     * 充值退费
     * @param idmember
     * @param num
     * @return
     */
    @RequestMapping(value = "/amount",method = RequestMethod.POST)
    public Result topUp(Integer idmember,Double num){
        Object username = request.getSession().getAttribute("username");
        if(num>=0){
            return memberService.topUp(idmember,num);
        }else{
            return memberService.returnMoney(idmember, num);
        }
    }

    /***
     * 删除会员
     * @param idmember
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public Result del(Integer idmember){
        Object username = request.getSession().getAttribute("username");
        return memberService.delMember(idmember);
    }

    /***
     * 查询当前店铺所有会员
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAllByBarber(){
        Object idbarber = request.getSession().getAttribute("idbarber");
        Object username = request.getSession().getAttribute("username");
        Barber barber = new Barber();
        barber.setIdbarber((Integer) idbarber);
        return memberService.findAllMemberByBarber(barber);
    }
    @RequestMapping(value = "/id",method = RequestMethod.GET)
    public  Result findById(Integer idmember){
        return memberService.findMemberById(idmember);
    }

}
