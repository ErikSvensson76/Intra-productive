package se.erik.lexicon.intra.components;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.erik.lexicon.intra.utils.StringUtil;
import org.junit.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class StringUtilTest {
	
	@TestConfiguration
	static class StringUtilTestConfig{
		@Bean	
		public StringUtil stringUtil() {
			return new StringUtil();
		}
	}
	
	@Autowired
	private StringUtil stringUtil;
	
	@Test
	public void test_toLowerCase_return_given_String_to_lowerCase() {
		String given = "AbCdEFg";
		String expected = "abcdefg";
		
		String actual = stringUtil.toLowerCase(given);
		assertEquals(expected, actual);
	}
	
	@Test
	public void test_surroundWithSqlWildCards_surround_given_string(){
		String given = "Testing";
		String expected = "%Testing%";
		
		assertEquals(expected, stringUtil.surroundWithSqlWildCards(given));
	}
	
	@Test
	public void test_surroundWithSqlWildCards_put_wildcard_at_the_beginning() {
		String given = "Testing%";
		String expected = "%Testing%";
		assertEquals(expected, stringUtil.surroundWithSqlWildCards(given));
	}
	
	@Test
	public void test_surroundWithSqlWildCards_put_wildcard_at_the_end() {
		String given = "%Testing";
		String expected = "%Testing%";
		assertEquals(expected,stringUtil.surroundWithSqlWildCards(given));
	}
	

}
