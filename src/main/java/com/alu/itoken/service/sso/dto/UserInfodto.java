package com.alu.itoken.service.sso.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author aluba
 * @since 2020-11-16
 */
@Data
public class UserInfodto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * name
     */
    private String name;

    /**
     * age
     */
    private Integer age;

    /**
     * address
     */
    private String address;

    /**
     * job
     */
    private String job;

    /**
     * 0-not deleted 1-deleted
     */
    private Integer deletestatus;

    /**
     * 密码
     */
    private String password;

  
    private String createBy;

    private LocalDate createDate;

    private String updateBy;

    private LocalDate updateDate;

    private String loginNo;
    
    private List uUserROle;
    
    private List uPermission;

    private String id;

}
