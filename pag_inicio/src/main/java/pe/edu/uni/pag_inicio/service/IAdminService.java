package pe.edu.uni.pag_inicio.service;

import org.springframework.stereotype.Service;

@Service
public interface IAdminService {
    void aprobarProyecto(int id);
    void rechazarProyecto(int id);
    void eliminarProyecto(int id);
}
