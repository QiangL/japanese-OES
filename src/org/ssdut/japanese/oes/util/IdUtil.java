package org.ssdut.japanese.oes.util;

import java.util.UUID;

public class IdUtil {

	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        return s.substring(0,8) + s.substring(9,13) + s.substring(14,18) + s.substring(19,23) + s.substring(24); 
    } 
	
	public static void main(String[] args) {
		System.out.println(IdUtil.getUUID());
	}
}
