package pe.edu.uni.pag_inicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.pag_inicio.entity.Usuarios;
import pe.edu.uni.pag_inicio.service.UsuariosService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
class UsuariosController {

    private final UsuariosService  usuariosService;

    @Autowired
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/{userId}")
    public Usuarios getUsuarioById(@PathVariable Long userId) {
        return usuariosService.getUserById(Math.toIntExact(userId));
    }

    @GetMapping("/email/{email}")
    public Usuarios getUsuarioByEmail(@PathVariable String email) {
        return usuariosService.getUserByEmail(email);
    }

    @GetMapping("/all")
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.getAllUsers();
    }

    @PostMapping("/create")
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return usuariosService.createUser(usuario);
    }

    @PutMapping("/update")
    public Usuarios updateUsuario(@RequestBody Usuarios usuario) {
        return usuariosService.updateUser(usuario);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUsuario(@PathVariable Long userId) {
        usuariosService.deleteUser(userId);
    }
}
