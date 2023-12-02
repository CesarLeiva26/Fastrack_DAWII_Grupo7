package pe.edu.cibertec.Fastrack_DAWll_Grupo7.Controller.frontoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Model.bd.Usuario;
import pe.edu.cibertec.Fastrack_DAWll_Grupo7.Service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "frontoffice/auth/frmRegistroUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.saveUser(usuario);
        return "frontoffice/auth/frmLogin";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/auth/login?logout";
    }
}