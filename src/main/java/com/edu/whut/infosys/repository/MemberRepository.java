package com.edu.whut.infosys.repository;

import com.edu.whut.infosys.bean.entity.Barber;
import com.edu.whut.infosys.bean.entity.Member1;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author aaaaaaa
 */
@org.springframework.stereotype.Repository
public interface MemberRepository extends Repository<Member1,Integer> {


    /***
     * 根据id找会员
     * @param idmember
     * @return
     */
    Member1 findMemberByIdmember(Integer idmember);
    /***
     * 根据姓名找会员
     * @param name
     * @return
     */
    List<Member1> findAllByName(String name);

    /***
     * 查找一个理发店的所有会员
     * @param barber
     * @return
     */
    List<Member1> findAllByBarber(Barber barber);
    /**
     * 保存会员
     * @param member
     * @return
     */
    Member1 save(Member1 member);

    /***
     * 根据会员姓名删除会员
     * @param name
     * @return
     */
    Member1 deleteByName(String name);

    /***
     * 删除会员
     * @param member
     * @return
     */
    Member1 delete(Member1 member);
}
