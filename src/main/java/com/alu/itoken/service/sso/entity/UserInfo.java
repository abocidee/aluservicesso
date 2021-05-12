package com.alu.itoken.service.sso.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import lombok.*;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfo implements Serializable {

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

    @TableField("createBy")
    private String createBy;

    @TableField("createDate")
    private LocalDate createDate;

    @TableField("updateBy")
    private String updateBy;

    @TableField("updateDate")
    private LocalDate updateDate;

    private String loginNo;


    private String id;

}
