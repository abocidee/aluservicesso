package com.alu.itoken.service.sso.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class NoUtil {
	private static long orderNum = 0l;  
    private static String date ; 
    /** 
     * 生成订单编号 
     * @return 
     */  
    public static synchronized String generateOrderNo() {
    	String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());  
        if(date==null||!date.equals(str)){  
            date = str;  
            orderNum  = 0l;  
        }  
        orderNum ++;  
        long orderNo = Long.parseLong((date)) * 10000;  
        orderNo += orderNum;;  
        return orderNo+"";  
    }

    /**
     * 生成vipNo
     * @return
     */
    public static synchronized  String generateVipNo() {
        TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = gen.generate();
        return "V"+uuid.toString().replaceAll("-", "");
    }

    /**
     * 生成GoodsNo
     * @return
     */
    public static synchronized  String generateGoodsNo() {
        TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = gen.generate();
        return "G"+uuid.toString().replaceAll("-", "");
    }

    /**
     * 生成ServiceNo
     * @return
     */
    public static synchronized  String generateServiceNo() {
        TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = gen.generate();
        return "S"+uuid.toString().replaceAll("-", "");
    }

    /**
     * 生成petLiveNo
     * @return
     */
    public static synchronized  String generatePetLiveNo() {
        TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = gen.generate();
        return "P"+uuid.toString().replaceAll("-", "");
    }

    /**
     * 生成fostercareno
     * @return
     */
    public static synchronized  String generateFosterCareNo() {
        TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = gen.generate();
        return "F"+uuid.toString().replaceAll("-", "");
    }

    public static synchronized  String generateUserNo(){
        TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
        UUID uuid = gen.generate();
        return "U"+uuid.toString().replaceAll("-", "");
    }
}
