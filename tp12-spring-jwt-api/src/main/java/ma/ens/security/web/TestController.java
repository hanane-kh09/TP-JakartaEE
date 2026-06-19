package ma.ens.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/api/user/profile")
    public String userProfile(Authentication auth) {
        return "Bonjour " + auth.getName() + " ! Vous êtes connecté.";
    }

    @GetMapping("/api/admin/dashboard")
    public String adminDashboard(Authentication auth) {
        return "Dashboard Admin — connecté en tant que : " + auth.getName();
    }
}