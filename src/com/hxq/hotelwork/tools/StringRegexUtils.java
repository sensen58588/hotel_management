package com.hxq.hotelwork.tools;

public class StringRegexUtils {
	/**
	 * ƥ�����֤ <br>
	 * 
	 * ��ʽΪ: XXXXXXXXXX(10λ) �� XXXXXXXXXXXXX(13λ) �� XXXXXXXXXXXXXXX(15λ) ��
	 * XXXXXXXXXXXXXXXXXX(18λ) <br>
	 * 
	 * ƥ�� : 0123456789123 <br>
	 * 
	 * ��ƥ��: 0123456
	 * 
	 */
	public static final String ID_card_regexp = "^\\d{18}$";
	
	
	//ƥ����Ʒ����ǲ���4λ����
	public static final String goodscode_regexp="^\\d{4}$";
	
	//ƥ������Ա������ǲ���3λ������
	public static final String workercode_regexp="^\\d{3}$";

	public static boolean Validate(String source, String regexp) {
		return source.matches(regexp);
	}
}
