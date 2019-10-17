package com.edu.whut.infosys.serivce.impl;

import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Bill;
import com.edu.whut.infosys.bean.entity.Member;
import com.edu.whut.infosys.repository.BarberRepository;
import com.edu.whut.infosys.repository.BillRepository;
import com.edu.whut.infosys.repository.MemberRepository;
import com.edu.whut.infosys.repository.PatternConsumptionRepository;
import com.edu.whut.infosys.serivce.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author aaaaaaa
 */
@Service
public class BillServiceImpl  implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    BarberRepository barberRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PatternConsumptionRepository patternConsumptionRepository;

    @Override
    public Result addBill(Integer idBarber,Integer idMemeber,Integer idpatten) {
        Result result = new Result();
        Bill bill = new Bill();
        bill.setBarber(barberRepository.findByIdbarber(idBarber));
        bill.setPatternConsumption(patternConsumptionRepository.findByIdpatternConsumption(idpatten));
        System.out.println("套餐号"+idpatten);
        System.out.println("套餐"+patternConsumptionRepository.findByIdpatternConsumption(idpatten));
        bill.setMember(memberRepository.findByIdmember(idMemeber));
        bill.setAmount(bill.getPatternConsumption().getAmount());
        if(bill.getAmount()>bill.getMember().getAmount()){
            result.setMessage("用户余额不足");
        }else{
            billRepository.save(bill);
            Member member = bill.getMember();
            member.setAmount(member.getAmount()-bill.getAmount());
            memberRepository.save(member);
            result.setSuccess(true);
            result.setMessage("添加账单成功");
        }
        return result;
    }

    @Override
    public Result countMoney(Barber barber) {
        Result result = new Result();
        if(barber.getIdbarber()==null){
            result.setMessage("理发店信息不完整");
        }else{
            List<Bill> bills = billRepository.findByBarber(barber);
            double count = 0;
            for (Bill bill : bills) {
                count+=bill.getAmount();
            }
            result.setSuccess(true);
            result.setMessage("计算成功");
            result.setDetail(count);
        }
        return result;
    }

    @Override
    public Result findBillByBarber(Barber barber) {
        Result result = new Result();
        if(barber.getIdbarber()==null){
            result.setMessage("理发店信息不完整");
        }else{
            List<Map<String, Object>> list =  new LinkedList<>();
            System.out.println("++++++++++++++++++"+barber.getIdbarber());
            List<Object> result1 = billRepository.findByidBarber(barber.getIdbarber());
            for (Object row : result1) {
                Object[] rowArray = (Object[]) row;
                Map<String, Object> mapArr = new HashMap<String, Object>();
                mapArr.put("createTime", rowArray[0]);
                mapArr.put("memberName", rowArray[1]);
                mapArr.put("BarberName", rowArray[2]);
                mapArr.put("patternName", rowArray[3]);
                mapArr.put("amount", rowArray[4]);
                list.add(mapArr);
            }
            //List<Bill> bills = billRepository.findByBarber(barber);
            result.setSuccess(true);
            result.setMessage("查询成功");
            result.setDetail(list);
        }
        return result;
    }

    @Override
    public Result findBillByMember(Member member) {
        Result result = new Result();
        if(member.getIdmember()==null){
            result.setMessage("查询信息不完整");
        }else{
            List<Map<String, Object>> list =  new LinkedList<>();
            List<Object> result1 = billRepository.findByidMember(member.getIdmember());
            for (Object row : result1) {
                Object[] rowArray = (Object[]) row;
                Map<String, Object> mapArr = new HashMap<String, Object>();
                mapArr.put("createTime", rowArray[0]);
                mapArr.put("memberName", rowArray[1]);
                mapArr.put("BarberName", rowArray[2]);
                mapArr.put("patternName", rowArray[3]);
                mapArr.put("amount", rowArray[4]);
                list.add(mapArr);
            }
            //List<Bill> bills = billRepository.findByMember(member);
            result.setSuccess(true);
            result.setMessage("查询成功");
            result.setDetail(list);
        }
        return result;
    }


}
