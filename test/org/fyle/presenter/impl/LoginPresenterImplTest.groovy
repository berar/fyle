package org.fyle.presenter.impl

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

import spock.lang.Specification
import org.fyle.view.LoginView
import org.fyle.view.impl.LoginViewImpl
import org.fyle.localization.Localization
import org.fyle.validation.impl.LRValidatorImpl
import org.fyle.model.ClickListener
import org.fyle.model.KeyPressedListener
import org.fyle.view.TextField


class LoginPresenterImplTest extends Specification {
	
	def localization
	def view
	def loginUsername
	def loginPassword
	def regPass
	def regRepPass
	def regUsername
	def regEmail
	
	KeyEvent key
	JTextField comp
	ClickListener clLogin
	KeyPressedListener kplLogin
	ClickListener clRegister
	KeyPressedListener kplRegister
	LRValidatorImpl lrv
	LoginPresenterImpl lpi
	
	def setup(){
		
		localization = Mock(Localization)
		localization.getUsernameTooShort() >> "getUsernameTooShort"
		localization.getUsernameTooLarge() >> "getUsernameTooLarge"
		localization.getUsernameInvalidFormat() >> "getUsernameInvalidFormat"
		localization.getPasswordTooShort() >> "getPasswordTooShort"
		localization.getPasswordTooLarge() >> "getPasswordTooLarge"
		localization.getPasswordInvalidFormat() >> "getPasswordInvalidFormat"
		localization.getEmailTooShort() >> "getEmailTooShort"
		localization.getEmailTooLarge() >> "getEmailTooLarge"
		localization.getEmailInvalidFormat() >> "getEmailInvalidFormat"
		localization.getThisFieldIsEmpty() >> "getThisFieldIsEmpty"
		localization.getUsernameToolTip() >> "getUsernameToolTip"
		localization.getPasswordToolTip() >> "getPasswordToolTip"
		localization.getPasswordsDontMatch() >> "getPasswordsDontMatch"
		
		loginUsername = Mock(TextField)
		loginPassword = Mock(TextField)
		regPass = Mock(TextField)
		regRepPass = Mock(TextField)
		regUsername = Mock(TextField)
		regEmail = Mock(TextField)
		
		view = Mock(LoginView)
		view.subscribeOnLoginButtonClick(_) >> { ClickListener x -> clLogin = x }
		view.subscribeOnLoginKeyPressed(_) >> { KeyPressedListener z -> kplLogin = z }
		view.subscribeOnRegButtonClick(_) >> { ClickListener y -> clRegister = y }
		view.subscribeOnRegKeyPressed(_) >> { KeyPressedListener k -> kplRegister = k }
		view.getLoginUsername() >> loginUsername
		view.getLoginPassword() >> loginPassword
		view.getRegPass() >> regPass
		view.getRegRepPass() >> regRepPass
		view.getRegUsername() >> regUsername
		view.getRegEmail() >> regEmail
		
		lrv = new LRValidatorImpl(localization)
		lpi = new LoginPresenterImpl(view, localization, lrv)
		
		comp = new JTextField()
		key = new KeyEvent(comp, 0, 0, 0, KeyEvent.VK_ENTER, (char) 0)
	}
	
	def "When initializing LoginPresenterImpl test if init works properly"(){
		
		when:
		lpi.init()
		
		then:
		1 * view.getLoginUsername().setToolTipText("getUsernameToolTip")
		1 * view.getLoginPassword().setToolTipText("getPasswordToolTip")
		//1 * view.subscribeOnLoginButtonClick( [ actionPerformed: { } ] as ClickListener)
	
	}
	
	def "When LoginPresenterImpl's method start is called does LoginView show"(){
		
		when:
		lpi.start()
		
		then:
		1 * view.show()
	}
	
	def "When login's username field is empty and login button is clicked"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> ""
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When login's username field is not empty but password field is and login button is clicked"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc"
		view.getLoginPassword().getText() >> ""
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When login's username field hold a too short string and password field isn't empty and login button is clicked username field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc"
		view.getLoginPassword().getText() >> "a"
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getUsernameTooShort")
	}
	
	def "When login's username field hold a too long string and password field isn't empty and login button is clicked username field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abcdefg123456789123456789"
		view.getLoginPassword().getText() >> "a"
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getUsernameTooLarge")
	}
	
	def "When login username field's string is of proper length but has bad format and password field isn't empty and login button is clicked username field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abcd##"
		view.getLoginPassword().getText() >> "a"
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getUsernameInvalidFormat")
	}
	
	def "When login's password field hold a too short string and username field is proper and login button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc1"
		view.getLoginPassword().getText() >> "a"
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getPasswordTooShort")
	}
	
	def "When login's password field hold a too long string and username field is proper and login button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc1"
		view.getLoginPassword().getText() >> "a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111"
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getPasswordTooLarge")
	}
	
	def "When login password field's string is of proper length but has bad format and username field is proper and login button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc1"
		view.getLoginPassword().getText() >> "abcd1111"
		
		when:
		clLogin.actionPerformed()
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getPasswordInvalidFormat")
	}
	
	def "When register's username field is empty and register button is clicked"(){
		/* GIVEN */
		view.getRegUsername().getText() >> ""
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegUsername().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When register's username field is not empty but password field is and register button is clicked"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc"
		view.getRegPass().getText() >> ""
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegPass().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When register's email field is empty but password and username aren't and register button is clicked"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "a"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> ""
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegEmail().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When register's username field hold a too short string and password and email fields aren't empty and register button is clicked username field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegUsername().setBalloonTip("getUsernameTooShort")
	}
	
	def "When register's username field hold a too long string and password and email fields aren't empty and register button is clicked username field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abcdefg123456789123456789"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegUsername().setBalloonTip("getUsernameTooLarge")
	}
	
	def "When register username field's string is of proper length but has bad format and password and email fields aren't empty and register button is clicked username field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abcd##"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegUsername().setBalloonTip("getUsernameInvalidFormat")
	}
	
	def "When register's password field hold a too short string and username and email fields are proper and register button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a@a.a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegPass().setBalloonTip("getPasswordTooShort")
	}
	
	def "When register's password field hold a too long string and username and email fields are proper and register button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111"
		view.getRegEmail().getText() >> "a@a.a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegPass().setBalloonTip("getPasswordTooLarge")
	}
	
	def "When register password field's string is of proper length but has bad format and username and email fields are proper and register button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "abcd1111"
		view.getRegEmail().getText() >> "a@a.a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegPass().setBalloonTip("getPasswordInvalidFormat")
	}
	
	def "When register's email field hold a too short string and username and password fields are proper and register button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "aA12345678"
		view.getRegEmail().getText() >> "a@"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegEmail().setBalloonTip("getEmailTooShort")
	}
	
	def "When register's email field hold a too long string and username and password fields are proper and register button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "aA1234567"
		view.getRegEmail().getText() >> "a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegEmail().setBalloonTip("getEmailTooLarge")
	}
	
	def "When register email field's string is of proper length but has bad format and username and password fields are proper and register button is clicked password field's balloon tip should be properly set"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "abcd1111A"
		view.getRegEmail().getText() >> "a#a.a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegEmail().setBalloonTip("getEmailInvalidFormat")
	}
	
	def "When register's email, login and password fields are proper but password doesn't match repPass field and register button is clicked"(){
		/* GIVE */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "abcd1ABCD"
		view.getRegEmail().getText() >> "a@a.a"
		view.getRegRepPass().getText() >> "abcd1"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegRepPass().setBalloonTip("getPasswordsDontMatch")
	}
	
	def "When login's username field is empty and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> ""
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When login's username field is not empty but password field is and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc"
		view.getLoginPassword().getText() >> ""
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When login's username field hold a too short string and password field isn't empty and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc"
		view.getLoginPassword().getText() >> "a"
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getUsernameTooShort")
	}
	
	def "When login's username field hold a too long string and password field isn't empty and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abcdefg123456789123456789"
		view.getLoginPassword().getText() >> "a"
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getUsernameTooLarge")
	}
	
	def "When login username field's string is of proper length but has bad format and password field isn't empty and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abcd##"
		view.getLoginPassword().getText() >> "a"
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginUsername().setBalloonTip("getUsernameInvalidFormat")
	}
	
	def "When login's password field hold a too short string and username field is proper and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc1"
		view.getLoginPassword().getText() >> "a"
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getPasswordTooShort")
	}
	
	def "When login's password field hold a too long string and username field is proper and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc1"
		view.getLoginPassword().getText() >> "a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111"
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getPasswordTooLarge")
	}
	
	def "When login password field's string is of proper length but has bad format and username field is proper and enter key is pressed on some login field"(){
		/* GIVEN */
		view.getLoginUsername().getText() >> "abc1"
		view.getLoginPassword().getText() >> "abcd1111"
		
		when:
		kplLogin.actionPerformed(key)
		
		then:
		1 * view.getLoginPassword().setBalloonTip("getPasswordInvalidFormat")
	}
	
	def "When register's username field is empty and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> ""
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegUsername().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When register's username field is not empty but password field is and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc"
		view.getRegPass().getText() >> ""
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegPass().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When register's email field is empty but password and username aren't and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "a"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> ""
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegEmail().setBalloonTip("getThisFieldIsEmpty")
	}
	
	def "When register's username field hold a too short string and password and email fields aren't empty and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegUsername().setBalloonTip("getUsernameTooShort")
	}
	
	def "When register's username field hold a too long string and password and email fields aren't empty and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abcdefg123456789123456789"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a"
		
		when:
		clRegister.actionPerformed()
		
		then:
		1 * view.getRegUsername().setBalloonTip("getUsernameTooLarge")
	}
	
	def "When register username field's string is of proper length but has bad format and password and email fields aren't empty and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abcd##"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegUsername().setBalloonTip("getUsernameInvalidFormat")
	}
	
	def "When register's password field hold a too short string and username and email fields are proper and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "a"
		view.getRegEmail().getText() >> "a@a.a"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegPass().setBalloonTip("getPasswordTooShort")
	}
	
	def "When register's password field hold a too long string and username and email fields are proper and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111"
		view.getRegEmail().getText() >> "a@a.a"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegPass().setBalloonTip("getPasswordTooLarge")
	}
	
	def "When register password field's string is of proper length but has bad format and username and email fields are proper and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "abcd1111"
		view.getRegEmail().getText() >> "a@a.a"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegPass().setBalloonTip("getPasswordInvalidFormat")
	}
	
	def "When register's email field hold a too short string and username and password fields are proper and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "aA12345678"
		view.getRegEmail().getText() >> "a@"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegEmail().setBalloonTip("getEmailTooShort")
	}
	
	def "When register's email field hold a too long string and username and password fields are proper and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "aA1234567"
		view.getRegEmail().getText() >> "a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111a111111111111111111111"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegEmail().setBalloonTip("getEmailTooLarge")
	}
	
	def "When register email field's string is of proper length but has bad format and username and password fields are proper and enter key is pressed on some register field"(){
		/* GIVEN */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "abcd1111A"
		view.getRegEmail().getText() >> "a#a.a"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegEmail().setBalloonTip("getEmailInvalidFormat")
	}
	
	def "When register's email, login and password fields are proper but password doesn't match repPass field and enter key is pressed on some register field"(){
		/* GIVE */
		view.getRegUsername().getText() >> "abc1"
		view.getRegPass().getText() >> "abcd1ABCD"
		view.getRegEmail().getText() >> "a@a.a"
		view.getRegRepPass().getText() >> "abcd1"
		
		when:
		kplRegister.actionPerformed(key)
		
		then:
		1 * view.getRegRepPass().setBalloonTip("getPasswordsDontMatch")
	}
}
