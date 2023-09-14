package ma.gemadec.securityv2.controller;

import lombok.RequiredArgsConstructor;
import ma.gemadec.securityv2.services.AuthenticationService;
import ma.gemadec.securityv2.utils.AuthenticationRequest;
import ma.gemadec.securityv2.utils.AuthenticationResponse;
import ma.gemadec.securityv2.utils.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @GetMapping("notAccess")
    public String itWorks(){
        return "Access Denied ";
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest req){
        return ResponseEntity.ok(authenticationService.register(req));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest req){
        return ResponseEntity.ok(authenticationService.authenticate(req));
    }
}
