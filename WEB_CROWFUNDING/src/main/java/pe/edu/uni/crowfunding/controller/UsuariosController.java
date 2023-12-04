package pe.edu.uni.crowfunding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.crowfunding.DTO.CredencialesDTO;
import pe.edu.uni.crowfunding.DTO.Mensajedto;
import pe.edu.uni.crowfunding.model.Usuario;
import pe.edu.uni.crowfunding.service.UsuariosService;

import java.util.List;

@RestController
@RequestMapping("/UniOpportunity")
@CrossOrigin(origins = "http://localhost:3000")
class UsuariosController {

    private final UsuariosService  usuariosService;
    @Autowired
    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody CredencialesDTO credenciales) {
        try {
            // Verificar las credenciales del usuario
            if (usuariosService.verificarCredenciales(credenciales)) {
                return new ResponseEntity<>(new Mensajedto(1, "Inicio de sesión exitoso"), HttpStatus.OK);
            } else {
                // Credenciales incorrectas
                return new ResponseEntity<>(new Mensajedto(-1, "Credenciales incorrectas"), HttpStatus.UNAUTHORIZED);
            }
        } catch (EmptyResultDataAccessException e) {
            // Manejar el caso de que no se encuentre ningún usuario con el nombre proporcionado
            return new ResponseEntity<>(new Mensajedto(-1, "Usuario no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Otro error inesperado
            return new ResponseEntity<>(new Mensajedto(-1, "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
