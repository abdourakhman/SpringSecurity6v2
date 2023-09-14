package ma.gemadec.securityv2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class DemoController {
    @GetMapping("demo")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from a secure endpoint");
    }

}
