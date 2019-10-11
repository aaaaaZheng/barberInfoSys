package com.edu.whut.infosys.serivce.impl;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member;
import com.edu.whut.infosys.repository.BarberRepository;
import com.edu.whut.infosys.repository.MemberRepository;
import com.edu.whut.infosys.serivce.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaaaaaa
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BarberRepository barberRepository;

    @Override
    public Result addMember(Member member,Integer idbarber) {
        Result result = new Result();
        if(member.getName()==null){
            result.setMessage("用户名缺失");
        }else{
            Barber aBarber = barberRepository.findByIdbarber(idbarber);
            if(aBarber==null){
                result.setMessage("此店铺不存在");
            }else{
                member.setBarber(aBarber);
                System.out.println("打印会员信息"+member);
                memberRepository.save(member);
                result.setSuccessAndMsg(true, "添加成功");
            }
        }
        return result;
    }

    @Override
    public Result topUp(Integer id, double num) {
        Result result = new Result();
        if(id==null){
            result.setMessage("id缺失");
        }else{
            Member aMember = memberRepository.findByIdmember(id);
            if(aMember==null){
                result.setMessage("用户未找到");
            }else{
                aMember.setAmount(aMember.getAmount()+num);
                memberRepository.save(aMember);
                result.setMessage("充值成功");
                result.setSuccess(true);
            }
        }
        return result;
    }

    @Override
    public Result returnMoney(Integer id, double num) {
        Result result = new Result();
        if(id==null){
            result.setMessage("id缺失");
        }else{
            Member aMember = memberRepository.findByIdmember(id);
            if(aMember==null){
                result.setMessage("用户未找到");
            }else{
                if((aMember.getAmount()-num)<0){
                    result.setMessage("退款额度大于余额退款失败");
                }else{

                    aMember.setAmount(aMember.getAmount()-num);
                    memberRepository.save(aMember);
                    result.setMessage("退费成功");
                    result.setSuccess(true);
                }
            }
        }
        return result;
    }

    @Override
    public Result delMember(Integer id) {
        Result result = new Result();
        if(id==null){
            result.setMessage("id缺失");
        }else{
            Member aMember = memberRepository.findByIdmember(id);
            if (aMember != null) {
                memberRepository.delete(aMember);
            }
            result.setMessage("删除成功");
            result.setSuccess(true);
        }
        return result;
    }

    @Override
    public Result findAllMemberByBarber(Barber barber) {
        Result result = new Result();
        if(barber.getIdbarber()==null){
            result.setMessage("id缺失");
        }else{
            List<Member> members = memberRepository.findAllByBarber(barberRepository.findByIdbarber(barber.getIdbarber()));
            result.setSuccess(true);
            result.setMessage("查找成功");
            result.setDetail(members);
        }
        return result;
    }
}
