package by.bsuir.jewelry.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor
public class AuthResult {
    private Long userId;
    private boolean isAuthenticated;
    private String role;
}
