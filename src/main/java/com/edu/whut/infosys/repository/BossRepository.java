package com.edu.whut.infosys.repository;

import com.edu.whut.infosys.bean.entity.Boss;
import org.springframework.data.repository.Repository;

/**
 * @author aaaaaaa
 */
@org.springframework.stereotype.Repository
public interface BossRepository extends Repository<Boss,Integer> {
    /***
     * 根据姓名查找
     * @param username
     * @return
     */
    Boss findBossByUsername(String username);

    /***
     * 通过id查找boss
     * @param idboss
     * @return
     */
    Boss findByIdboss(Integer idboss);

    /***
     * 根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    Boss findBossByUsernameAndPassword(String username,String password);

    /***
     * 保存对象
     * @param boss
     * @return
     */
    Boss save(Boss boss);

    /***
     * 根据姓名删除boss
     * @param name
     * @return
     */
    Boss deleteByUsername(String name);

}
