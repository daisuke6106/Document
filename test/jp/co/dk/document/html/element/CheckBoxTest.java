package jp.co.dk.document.html.element;

import static org.junit.Assert.*;
import jp.co.dk.document.foundation.TestDocumentFoundation;
import jp.co.dk.document.html.HtmlElement;
import jp.co.dk.document.html.constant.HtmlAttributeName;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class CheckBoxTest extends TestDocumentFoundation {
	
	@Mocked
	private HtmlElement htmlElement;
	
	@Test
	public void isChecked() {
		
		// Html要素にchecked属性が設定されていた場合、trueが返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = new Boolean(true);
		}};
		CheckBox checkbox1 = new CheckBox(htmlElement);
		assertTrue(checkbox1.isChecked());
		
		// Html要素にchecked属性が設定されていた場合、falseが返却されること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = new Boolean(false);
		}};
		CheckBox checkbox2 = new CheckBox(htmlElement);
		assertFalse(checkbox2.isChecked());
		
	}
	
	@Test
	public void checked() {
		
		// Html要素にchecked属性が設定されていた場合、チェック状態へfalseを設定した場合
		// チェック状態がfalse、デフォルトのチェック状態がtrueであること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = new Boolean(true);
		}};
		CheckBox checkbox1 = new CheckBox(htmlElement);
		checkbox1.checked(false);
		assertFalse(checkbox1.isChecked());
		assertTrue(checkbox1.getDefaltChecked());
		
		// Html要素にchecked属性が設定されていなかった場合、チェック状態へtrueを設定した場合
		// チェック状態がtrue、デフォルトのチェック状態がfalseであること
		new NonStrictExpectations() {{
			htmlElement.hasAttribute(HtmlAttributeName.CHECKED.getName());
			result = new Boolean(false);
		}};
		CheckBox checkbox2 = new CheckBox(htmlElement);
		checkbox2.checked(true);
		assertTrue(checkbox2.isChecked());
		assertFalse(checkbox2.getDefaltChecked());
		
	}

}
