package com.edu.whut.infosys.bean.entity;

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
@JsonIgnoreProperties(value={"barber","hibernateLazyInitializer","handler","fieldHandler"})
public class Boss {
    @Id
    @GeneratedValue
    private Integer idboss;
    @Column(nullable = false,unique = true,updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String answer;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "idbarber",referencedColumnName = "idbarber")
    @ToString.Exclude
    private Barber barber;
}
