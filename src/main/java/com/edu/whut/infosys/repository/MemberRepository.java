package com.edu.whut.infosys.repository;

import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author aaaaaaa
 */
@org.springframework.stereotype.Repository
public interface MemberRepository extends Repository<Member,Integer> {


    /***
     * 根据id找会员
     * @param id
     * @return
     */
    Member findByIdmember(Integer id);
    /***
     * 根据姓名找会员
     * @param name
     * @return
     */
    List<Member> findAllByName(String name);

    /***
     * 查找一个理发店的所有会员
     * @param barber
     * @return
     */
    List<Member> findAllByBarber(Barber barber);
    /**
     * 保存会员
     * @param member
     * @return
     */
    Member save(Member member);

    /***
     * 根据会员姓名删除会员
     * @param name
     * @return
     */
    Member deleteByName(String name);

    /***
     * 删除会员
     * @param member
     * @return
     */
    Member delete(Member member);
}
