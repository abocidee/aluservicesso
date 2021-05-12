package com.alu.itoken.service.sso.entity;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;

@ApiModel("全局返回统一结果")
public class R {
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回\ud83d\udc34")
    private Integer code;
    @ApiModelProperty("返回状态")
    private boolean state;
    @ApiModelProperty("返回状态")
    private Long total;
    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap();

    private R() {
    }

    public static R ok() {
        R r = new R();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        r.setState(ResultCodeEnum.SUCCESS.getSuccess());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        r.setState(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        return r;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    public R data(String key, Object o) {
        this.data.put(key, o);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R success(Boolean success) {
        this.setState(success);
        return this;
    }

    public R totoal(Long total) {
        this.setTotal(total);
        return this;
    }

    public static R EnumResults(ResultCodeEnum resultCodeEnum) {
        R r = new R();
        r.setMessage(resultCodeEnum.getMessage());
        r.setState(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        return r;
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public boolean isState() {
        return this.state;
    }

    public Long getTotal() {
        return this.total;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setState(final boolean state) {
        this.state = state;
    }

    public void setTotal(final Long total) {
        this.total = total;
    }

    public void setData(final Map<String, Object> data) {
        this.data = data;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof R)) {
            return false;
        } else {
            R other = (R)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label63: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label63;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label63;
                    }

                    return false;
                }

                Object this$code = this.getCode();
                Object other$code = other.getCode();
                if (this$code == null) {
                    if (other$code != null) {
                        return false;
                    }
                } else if (!this$code.equals(other$code)) {
                    return false;
                }

                if (this.isState() != other.isState()) {
                    return false;
                } else {
                    Object this$total = this.getTotal();
                    Object other$total = other.getTotal();
                    if (this$total == null) {
                        if (other$total != null) {
                            return false;
                        }
                    } else if (!this$total.equals(other$total)) {
                        return false;
                    }

                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data != null) {
                            return false;
                        }
                    } else if (!this$data.equals(other$data)) {
                        return false;
                    }

                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof R;
    }


    public String toString() {
        return "R(message=" + this.getMessage() + ", code=" + this.getCode() + ", state=" + this.isState() + ", total=" + this.getTotal() + ", data=" + this.getData() + ")";
    }
}
