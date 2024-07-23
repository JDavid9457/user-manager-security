package code.wolf.usermanagersecurity.infrastructure.util.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailValidation {
    @Value("${regex.email}")
    private String emailRegex;
    public boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile(emailRegex);
        return emailPattern.matcher(email).matches();
    }
}
