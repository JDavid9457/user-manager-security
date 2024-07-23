package code.wolf.usermanagersecurity.infrastructure.util.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PasswordValidation {

    @Value("${regex.password}")
    private String regexPassword;

    public boolean isValidPassword(String password) {
        Pattern emailPattern = Pattern.compile(regexPassword);
        return emailPattern.matcher(password).matches();
    }
}
