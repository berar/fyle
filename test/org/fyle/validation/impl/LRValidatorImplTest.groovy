package org.fyle.validation.impl
import spock.lang.Specification
import org.fyle.localization.Localization
import org.fyle.validation.impl.ValidationResult
class LRValidatorImplTest extends Specification {
	def localization
	LRValidatorImpl lrv
	
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
		lrv = new LRValidatorImpl(localization)
	}
	
	def "When username is too short return proper ValidationResult"(){
		when:
		def vR = lrv.isUsernameCorrect("abc")
		then:
		vR.isValid() == false
		vR.getMessage() == "getUsernameTooShort"
	}
	
	def "When username is too large return proper ValidationResult"(){
		when:
		def vR = lrv.isUsernameCorrect("abcdefg12345678912345678")
		then:
		vR.isValid() == false
		vR.getMessage() == "getUsernameTooLarge"
	}
	
	def "When username's format is invalid return proper ValidationResult"(){
		when:
		def vR = lrv.isUsernameCorrect("abc###")
		then:
		vR.isValid() == false
		vR.getMessage() == "getUsernameInvalidFormat"
	}
	
	def "When username is valid return proper ValidationResult"(){
		when:
		def vR = lrv.isUsernameCorrect("abcd1")
		then:
		vR.isValid() == true
		vR.getMessage() == "default"
	}
	
	def "When password is too short return proper ValidationResult"(){
		when:
		def vR = lrv.isPasswordCorrect("abc")
		then:
		vR.isValid() == false
		vR.getMessage() == "getPasswordTooShort"
	}
	
	def "When password is too large return proper ValidationResult"(){
		when:
		def vR = lrv.isPasswordCorrect("abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678")
		then:
		vR.isValid() == false
		vR.getMessage() == "getPasswordTooLarge"
	}
	
	def "When password's format is invalid return proper ValidationResult"(){
		when:
		def vR = lrv.isPasswordCorrect("abcdefg111")
		then:
		vR.isValid() == false
		vR.getMessage() == "getPasswordInvalidFormat"
	}
	
	def "When password is valid return proper ValidationResult"(){
		when:
		def vR = lrv.isPasswordCorrect("abcd1AAAA")
		then:
		vR.isValid() == true
		vR.getMessage() == "default"
	}
	
	def "When email is too short return proper ValidationResult"(){
		when:
		def vR = lrv.isEmailCorrect("a@")
		then:
		vR.isValid() == false
		vR.getMessage() == "getEmailTooShort"
	}
	
	def "When email is too large return proper ValidationResult"(){
		when:
		def vR = lrv.isEmailCorrect("abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678abcdefg12345678912345678")
		then:
		vR.isValid() == false
		vR.getMessage() == "getEmailTooLarge"
	}
	
	def "When email's format is invalid return proper ValidationResult"(){
		when:
		def vR = lrv.isEmailCorrect("ab#a.a")
		then:
		vR.isValid() == false
		vR.getMessage() == "getEmailInvalidFormat"
	}
	
	def "When email is valid return proper ValidationResult"(){
		when:
		def vR = lrv.isEmailCorrect("abc@abc.ab")
		then:
		vR.isValid() == true
		vR.getMessage() == "default"
	}
}
