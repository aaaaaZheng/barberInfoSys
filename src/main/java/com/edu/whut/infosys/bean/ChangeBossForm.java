package com.edu.whut.infosys.bean;


import lombok.Data;

/***
 *
 * @author aaaaaaa
 */
@Data
public class ChangeBossForm {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String question;
    private String answer;
}
