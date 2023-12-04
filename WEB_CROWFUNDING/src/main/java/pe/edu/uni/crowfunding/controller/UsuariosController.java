package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Usuario;
import pe.edu.uni.crowfunding.service.UsuariosService;

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
    public Usuario getUsuarioById(@PathVariable int userId) {
        return usuariosService.getUserById(userId);
    }

    @GetMapping("/email/{email}")
    public Usuario getUsuarioByEmail(@PathVariable String email) {
        return usuariosService.getUserByEmail(email);
    }

    @GetMapping("/all")
    public List<Usuario> getAllUsuarios() {
        return usuariosService.getAllUsers();
    }

    @PostMapping("/create")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuariosService.createUser(usuario);
    }

    @PutMapping("/update/{idUsuario}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable int idUsuario,
            @RequestBody Usuario usuarioDTO) {

        Usuario usuarioActualizado = usuariosService.updateUser(idUsuario,usuarioDTO);

        if (usuarioActualizado != null) {
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Mensajedto> deleteUsuario(@PathVariable int userId) {
        Mensajedto resultado =usuariosService.deleteUser(userId);
        if (resultado.getCodigo() == 1) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
