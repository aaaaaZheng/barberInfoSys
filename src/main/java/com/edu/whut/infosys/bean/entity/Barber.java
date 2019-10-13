package com.edu.whut.infosys.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author aaaaaaa
 */
@ToString
@Data
@Entity
@JsonIgnoreProperties(value={"memberList","boss","billList","idmemeber","idbarber","hibernateLazyInitializer","handler","fieldHandler"})
public class Barber {
    @Id
    @GeneratedValue
    private Integer idbarber;
    @Column(nullable = false)
    private String name;
    //@OneToOne(mappedBy = "barber", cascade = {CascadeType.MERGE, CascadeType.REFRESH})//, optional = false
    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="idboss",referencedColumnName="idboss",nullable = false)
    @ToString.Exclude
    private Boss boss;
    @Column(nullable = false)
    private String address;
    /*@OneToMany(mappedBy = "barber")
    @JsonIgnoreProperties("barber")
    @JSONField(serialize = false)*/
    //@JoinColumn(name = "idmember",referencedColumnName = )
    @OneToMany(cascade={CascadeType.ALL},fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Member> memberList;
    @OneToMany(mappedBy = "barber",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("barber")
    @JSONField(serialize = false)
    @ToString.Exclude
    private List<Bill> billList;

}
