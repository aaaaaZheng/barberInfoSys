package com.edu.whut.infosys.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author aaaaaaa
 */
@Data
@Entity
@JsonIgnoreProperties(value={"barber","billList","hibernateLazyInitializer","handler","fieldHandler"})
public class PatternConsumption {
    @Id
    @GeneratedValue
    private Integer idpatternConsumption;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private Float amount;
    @OneToMany(mappedBy = "patternConsumption",cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @JSONField(serialize = false)
    @ToString.Exclude
    private List<Bill> billList;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "idbarber")
    @JSONField(serialize = false)
    @ToString.Exclude
    private Barber barber;
}
