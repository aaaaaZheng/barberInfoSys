package com.edu.whut.infosys.serivce.impl;
import com.edu.whut.infosys.bean.ChangeBossForm;
import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.repository.BossRepository;
import com.edu.whut.infosys.serivce.BossService;

import com.edu.whut.infosys.bean.entity.Boss;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aaaaaaa
 */
@Service
public class BossServiceImpl implements BossService {
    @Autowired
    private BossRepository bossRepository;

    /**
     * @param boss
     * @return 注册结果
     */
    @Override
    public Result register(Boss boss) {
        Result result = new Result();
        if (boss.getUsername()==null){
            result.setMessage("注册失败，用户名为空");
        }else if(boss.getPassword()==null){
            result.setMessage("注册失败，密码为空");
        }else if(boss.getQuestion()==null){
            result.setMessage("注册失败，密保问题为空");
        }else if(boss.getAnswer()==null){
            result.setMessage("注册失败，密保答案为空");
        }else{
            val existBoss = bossRepository.findBossByUsername(boss.getUsername());
            if(existBoss!=null){
                result.setMessage("用户名已存在");
            } else {
                bossRepository.save(boss);
                result.setMessage("注册成功");
                result.setSuccess(true);
            }
        }
        return result;
    }

    /***
     * 登录服务事项
     * @param boss
     * @return 登录结果
     */
    @Override
    public Result login(Boss boss) {
        Result result = new Result();
        if (boss.getUsername()==null){
            result.setMessage("登陆失败，用户名为空");
        }else if(boss.getPassword()==null) {
            result.setMessage("登陆失败，密码为空");
        } else{
            System.out.println(boss);
            Boss aBoss = bossRepository.findBossByUsernameAndPassword(boss.getUsername(), boss.getPassword());
            System.out.println(aBoss);
            if(aBoss !=null){
                result.setSuccess(true);
                result.setMessage("登陆成功");
            } else {
                result.setSuccess(false);
                result.setMessage("登陆失败，账号或密码错误");
            }
        }
        return result;
    }

    /***
     * boss修改密码
     * @param bossForm
     * @return
     */
    @Override
    public Result changePassword(ChangeBossForm bossForm) {
        Result result = new Result();
        if (bossForm.getUsername()==null || bossForm.getAnswer()==null ||bossForm.getNewPassword()==null ||bossForm.getOldPassword()==null || bossForm.getQuestion()==null){
            result.setMessage("修改失败，信息不完整");
        }else{
            Boss aBoss = bossRepository.findBossByUsernameAndPassword(bossForm.getUsername(), bossForm.getOldPassword());
            if(aBoss==null){
                result.setMessage("原密码错误");
            }else{
                aBoss.setPassword(bossForm.getNewPassword());
                bossRepository.save(aBoss);
                result.setSuccess(true);
                result.setMessage("修改密码成功");
            }
        }
        return result;
    }

    @Override
    public Boss findByUsername(String username) {
        return bossRepository.findBossByUsername(username);
    }
}
