package com.edu.whut.infosys.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * @author aaaaaaa
 */
@Data
@Entity
@JsonIgnoreProperties(value={"idbarber","billList","barber","hibernateLazyInitializer","handler","fieldHandler"})
public class Member {
    @Id
    @GeneratedValue
    private Integer idmember;
    @Column(nullable = false)
    private String name;
    private String sex;
    private Double amount = 0.0;
    private String phone;
    //@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
    //@JoinColumn(name = "idbarber",referencedColumnName = "idbarber")
    /*@ManyToOne
    @JoinColumn(name = "idbarber", referencedColumnName = "idbarber")
    @JsonIgnoreProperties("members")
    @JSONField(serialize = false)*/
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="idbarber")
    @JSONField(serialize = false)
    @ToString.Exclude
    private Barber barber;
    private Date birthday;
    @OneToMany(mappedBy = "member",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @ToString.Exclude
    private List<Bill> billList;
}
