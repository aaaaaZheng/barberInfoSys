package com.edu.whut.infosys.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
/**
 * @author aaaaaaa
 */
@Data
@Entity
@JsonIgnoreProperties(value={"idmemeber","idbarber","idpatternConsumption","patternConsumption","member","barber","hibernateLazyInitializer","handler","fieldHandler"})
public class Bill {
    @Id
    @GeneratedValue
    private Integer idbill;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "idpatternConsumption")
    @JSONField(serialize = false)
    private PatternConsumption patternConsumption;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "idmemeber")
    @JSONField(serialize = false)
    @ToString.Exclude
    private Member member;
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "idbarber")
    @JSONField(serialize = false)
    @ToString.Exclude
    private Barber barber;
    @Column(nullable = false)
    private Float amount;
    @Column(name="CREATE_TIME",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp consumptionTime;
}
