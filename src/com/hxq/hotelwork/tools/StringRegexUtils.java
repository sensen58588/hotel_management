package com.hxq.hotelwork.tools;

public class StringRegexUtils {
	/**
	 * 匹配身份证 <br>
	 * 
	 * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或
	 * XXXXXXXXXXXXXXXXXX(18位) <br>
	 * 
	 * 匹配 : 0123456789123 <br>
	 * 
	 * 不匹配: 0123456
	 * 
	 */
	public static final String ID_card_regexp = "^\\d{18}$";
	
	
	//匹配商品编号是不是4位数字
	public static final String goodscode_regexp="^\\d{4}$";
	
	//匹配新增员工编号是不是3位工作号
	public static final String workercode_regexp="^\\d{3}$";

	public static boolean Validate(String source, String regexp) {
		return source.matches(regexp);
	}
}
