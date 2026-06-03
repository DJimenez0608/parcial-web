package com.parcial.parcialweb.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.parcial.parcialweb.entities.FacultadEntity;
import com.parcial.parcialweb.repositories.FacultadRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class FacultadService {
    

    @Autowired
    private FacultadRepository facultadRepository;

    //metodo para crear una facultad
    public void crearFacultad(String nombre, String decano, String ubicacion) {
        FacultadEntity facultad = new FacultadEntity();
        facultad.setNombre(nombre);
        facultad.setDecano(decano);
        facultad.setUbicacion(ubicacion);
        facultadRepository.save(facultad);
    }

    //metodo para eliminar una facultad

    @Transactional
    public void eliminarFacultad(int id) {
        if (!facultadRepository.existsById(id)) {
            throw new EntityNotFoundException("Facultad no encontrada con id: " + id);
        }
        facultadRepository.deleteById(id);
    }

    //metodo para obtener una facultad por id
    public FacultadEntity obtenerFacultadPorId(int id) {
        return facultadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facultad no encontrada con id: " + id));
    }   

    //metodo para obtener todas las facultades
    public java.util.List<FacultadEntity> obtenerTodasLasFacultades() {
        return facultadRepository.findAll().stream()
                .filter(facultad -> facultad.getProgramaAcademicoEntities() != null && !facultad.getProgramaAcademicoEntities().isEmpty())
                .collect(Collectors.toList());
            }



}
