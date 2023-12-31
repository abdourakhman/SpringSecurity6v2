package ma.gemadec.securityv2.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
}
