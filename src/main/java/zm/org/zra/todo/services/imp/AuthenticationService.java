package zm.org.zra.todo.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import zm.org.zra.todo.dtos.ActionResult;
import zm.org.zra.todo.dtos.LoginDTO;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    public ActionResult login(LoginDTO request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ActionResult(true, "Login successful.");
        }
        catch (AuthenticationException e) {
            return new ActionResult(false, e.getMessage());
        }

    }
}
