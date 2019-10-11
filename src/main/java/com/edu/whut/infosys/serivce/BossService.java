package com.edu.whut.infosys.serivce;

import com.edu.whut.infosys.bean.ChangeBossForm;
import com.edu.whut.infosys.bean.Result;
import com.edu.whut.infosys.bean.entity.Boss;

/**
 * @author aaaaaaa
 */
public interface BossService {
    /**
     * 注册服务
     * @param boss
     * @return 返回注册结果
     */
    Result register(Boss boss);

    /***
     * 登录服务
     * @param boss
     * @return 登录结果
     */
    Result login(Boss boss);

    /***
     *修改密码
     * @param bossForm
     * @return
     */
    Result changePassword(ChangeBossForm bossForm);

    /***
     * 通过用户名查找boss
     * @param username
     * @return
     */
    Boss findByUsername(String username);

}
