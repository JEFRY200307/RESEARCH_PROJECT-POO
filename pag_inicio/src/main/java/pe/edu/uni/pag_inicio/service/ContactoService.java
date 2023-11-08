package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Usuarios;
import pe.edu.uni.pag_inicio.repository.ContactRepository;

import java.util.List;

@Service
public class ContactoService implements IContactoService{

    private final ContactRepository contactRepository;

    @Autowired
    public ContactoService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contacto> getContactMessagesByUser(Usuarios usuario) {
        return contactRepository.findByUsuario(usuario);
    }

    @Override
    public Contacto saveContactMessage(Contacto message) {
        return contactRepository.save(message);
    }
}
