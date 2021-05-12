package com.alu.itoken.service.sso.entity;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * url地址
     */
    private String url;

    /**
     * url描述
     */
    private String name;


}
