package pe.edu.uni.pag_inicio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pe.edu.uni.pag_inicio.controller.dto.ContactoDTO;
import pe.edu.uni.pag_inicio.controller.dto.ProyectoDTO;
import pe.edu.uni.pag_inicio.entity.Contacto;
import pe.edu.uni.pag_inicio.entity.Creador;
import pe.edu.uni.pag_inicio.entity.Proyectos;
import pe.edu.uni.pag_inicio.entity.Usuarios;
import pe.edu.uni.pag_inicio.repository.ContactRepository;
import pe.edu.uni.pag_inicio.repository.CreadorRepository;

import java.util.Date;
import java.util.List;

@Service
public class ContactoService implements IContactoService{

    private final ContactRepository contactoRepository;
    private final CreadorRepository creadorRepository;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public ContactoService(ContactRepository contactRepository, CreadorRepository creadorRepository) {
        this.contactoRepository = contactRepository;
        this.creadorRepository = creadorRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void solicitarCreacion(ProyectoDTO proyectoDTO, ContactoDTO contactoDTO) {
        // Crear un objeto que almacene los detalles proporcionados por el usuario
        Proyectos nuevoProyecto = new Proyectos();
        Creador idcreador = creadorRepository.findByIdCreador(proyectoDTO.getIdCreador());
        nuevoProyecto.setTitulo(proyectoDTO.getTitulo());
        nuevoProyecto.setDescripcion(proyectoDTO.getDescripcion());
        nuevoProyecto.setObjetivos(proyectoDTO.getObjetivos());
        nuevoProyecto.setFecha_fin(proyectoDTO.getFechaFin());
        nuevoProyecto.setImage_url(proyectoDTO.getImageUrl());
        nuevoProyecto.setRecaudacion(proyectoDTO.getRecaudacion());
        nuevoProyecto.setFecha_inicio(proyectoDTO.getFechaInicio());
        nuevoProyecto.setCategoria(proyectoDTO.getCategoria());
        nuevoProyecto.setMonto_objetivo(proyectoDTO.getMontoObjetivo());
        nuevoProyecto.setEstado_aprobacion(false);
        nuevoProyecto.setUsuario(idcreador.getUsuario());

        // Crear un objeto de contacto para almacenar los detalles proporcionados por el usuario
        Contacto nuevoContacto = new Contacto();
        nuevoContacto.setUsuario(idcreador.getUsuario());
        nuevoContacto.setAsunto(contactoDTO.getAsunto());
        nuevoContacto.setMensaje(contactoDTO.getMensaje());
        nuevoContacto.setFechaEnvio(contactoDTO.getFechaEnvio());
        nuevoContacto.setProyecto(nuevoProyecto);

        // Lógica para enviar la solicitud de creación al administrador
        String mensajeNotificacion = "El usuario " + proyectoDTO.getIdCreador() +
                " ha solicitado la creación de un nuevo proyecto con los siguientes detalles: \n\n" +
                "Título del proyecto: " + proyectoDTO.getTitulo() + "\n" +
                "Descripción del proyecto: " + proyectoDTO.getDescripcion() + "\n" +
                "Objetivos del proyecto: " + proyectoDTO.getObjetivos() + "\n" +
                "Fecha fin del proyecto: " + proyectoDTO.getFechaFin() + "\n" +
                "Imagen del proyecto: " + proyectoDTO.getImageUrl() + "\n" +
                "Recaudación del proyecto: " + proyectoDTO.getRecaudacion() + "\n" +
                "Fecha de inicio del proyecto: " + proyectoDTO.getFechaInicio() + "\n" +
                "Categoría del proyecto: " + proyectoDTO.getCategoria() + "\n" +
                "Monto objetivo del proyecto: " + proyectoDTO.getMontoObjetivo() + "\n" +
                "Por favor, revise la solicitud.";

        enviarSolicitudCreacion(nuevoContacto, mensajeNotificacion, nuevoProyecto);
    }
    private void enviarSolicitudCreacion(Contacto contacto, String mensaje, Proyectos nuevoProyecto) {
        // Obtener una lista de todos los administradores disponibles
        List<Creador> administradoresDisponibles = creadorRepository.findAllAdministradores();

        // Verificar si hay administradores disponibles
        if (!administradoresDisponibles.isEmpty()) {
            // Implementar un algoritmo para seleccionar un administrador de manera cíclica
            int numeroFormulario = obtenerNumeroFormulario(); // Implementa la lógica para obtener el número de formulario

            // Calcular el índice del administrador utilizando el operador módulo
            int indiceAdmin = (numeroFormulario - 1) % administradoresDisponibles.size();
            Creador adminCreador = administradoresDisponibles.get(indiceAdmin);

            // Lógica para enviar el formulario al administrador específico
            // Puedes usar una función de notificación o correo electrónico aquí para enviar el mensaje
            // Ejemplo: enviarCorreoSolicitudCreacion(adminCreador.getUsuario().getEmail(), mensaje);
            System.out.println("Enviando formulario de creación a: " + adminCreador.getUsuario().getEmail());
        } else {
            // Lógica para manejar el caso cuando no hay administradores disponibles
            System.out.println("Error: No hay administradores disponibles para enviar la solicitud de creación.");
        }
    }

    private int obtenerNumeroFormulario() {
        String countQuery = "SELECT COUNT(*) FROM Contacto";
        return jdbcTemplate.queryForObject(countQuery, Integer.class);
    }

    @Override
    public void solicitarModificacion(ProyectoDTO proyectoDTO, ContactoDTO contactoDTO) {
        // Crear un objeto que almacene los cambios solicitados por el usuario
        Proyectos proyecto = contacto.getProyecto();

        if (contactoDTO.getTitulo() != null) {
            proyecto.setTitulo(contactoDTO.getTitulo());
        }

        if (contactoDTO.getDescripcion() != null) {
            proyecto.setDescripcion(contactoDTO.getDescripcion());
        }

        if (contactoDTO.getObjetivos() != null) {
            proyecto.setObjetivos(contactoDTO.getObjetivos());
        }

        if (contactoDTO.getFechaFin() != null) {
            proyecto.setFecha_fin(contactoDTO.getFechaFin());
        }

        if (contactoDTO.getImagen() != null) {
            proyecto.setImage_url(contactoDTO.getImagen());
        }

        // Lógica para enviar la solicitud de modificación al administrador
        String mensajeNotificacion = "El usuario " + contacto.getUsuario().getNombre() +
                " ha solicitado modificaciones en el proyecto con el ID: " + proyecto.getId_proyecto() + ". \n\n" +
                "Detalles de los cambios solicitados: \n" +
                "Título del proyecto: " + contactoDTO.getTitulo() + "\n" +
                "Descripción del proyecto: " + contactoDTO.getDescripcion() + "\n" +
                "Objetivos del proyecto: " + contactoDTO.getObjetivos() + "\n" +
                "Fecha fin del proyecto: " + contactoDTO.getFechaFin() + "\n" +
                "Imagen del proyecto: " + contactoDTO.getImagen() + "\n" +
                "Por favor, revise la solicitud.";

        enviarSolicitudModificacion(contacto, mensajeNotificacion);
    }
    private void enviarSolicitudModificacion(Contacto contacto, String mensaje) {
        // Aquí debes reemplazar 'adminCreador' con el método adecuado para encontrar al administrador específico
        Creador adminCreador = creadorRepository.findByIdCreador(contacto.getProyecto().getId_creador().getIdCreador());

        if (adminCreador != null) {
            // Lógica para enviar el formulario al administrador específico
            // Puedes usar una función de notificación o correo electrónico aquí para enviar el mensaje
            // Ejemplo: enviarCorreoSolicitudModificacion(adminCreador.getUsuario().getEmail(), mensaje);
            System.out.println("Enviando formulario de modificación a: " + adminCreador.getUsuario().getEmail());
        } else {
            // Lógica para manejar el caso cuando no se encuentra el administrador específico
            System.out.println("Error: No se ha encontrado el administrador para enviar la solicitud de modificación.");
        }
    }
}
