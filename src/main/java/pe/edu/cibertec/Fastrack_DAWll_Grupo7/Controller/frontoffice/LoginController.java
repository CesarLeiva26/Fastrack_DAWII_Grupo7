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
    @PostMapping("/login-success")
    public String loginUsuario(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("usuario", userDetails.getUsername());
        return "home";
    }

    @PostMapping("/cambiarpassword")
    public String cambiarPassword(@RequestParam String newPassword) {
        Usuario usuario = obtenerUsuarioActual();
        if (usuario != null) {
            usuarioService.updatePassword(usuario, newPassword);
        }
        return "frontoffice/auth/frmLogin";
    }

    private Usuario obtenerUsuarioActual() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioService.findUserByUserName(userDetails.getUsername());
    }
}
