package code.wolf.usermanagersecurity.infrastructure.util.validation;


import code.wolf.usermanagersecurity.aplication.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameValidation implements ConstraintValidator<ExistsByUsername, String> {

    private final UserService service;

    public ExistsByUsernameValidation(UserService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (service == null) {
            return true;
        }
        return true;
    }
    
    
}
