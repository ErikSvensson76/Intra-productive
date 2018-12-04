package se.erik.lexicon.intra.utils;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {
	
	public String toLowerCase(String s) {
		return new StringBuilder(s.toLowerCase()).toString();
	}
	
	public String surroundWithSqlWildCards(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		if(sb.charAt(0) != '%' ) sb.insert(0, '%');
		if(sb.charAt(sb.length()-1) != '%') sb.append('%');
		return sb.toString();	
	}
	
}
