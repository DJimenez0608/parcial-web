package com.parcial.parcialweb.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.parcial.parcialweb.entities.FacultadEntity;
import com.parcial.parcialweb.services.FacultadService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/facultad")
public class FaculradController {

    private final FacultadService facultadService;

    public FaculradController(FacultadService facultadService) {
        this.facultadService = facultadService;
    }
//lista de facultades
    @GetMapping
    public ResponseEntity<List<FacultadEntity>> getAll() {
        List<FacultadEntity> list = facultadService.obtenerTodasLasFacultades();
        return ResponseEntity.ok(list);
    }
//obtener una facultad por id
    @GetMapping("/{id}")
    public ResponseEntity<FacultadEntity> get(@PathVariable int id) {
        FacultadEntity facultad = facultadService.obtenerFacultadPorId(id);
        return ResponseEntity.ok(facultad);
    }
//crear una facultad
    @PostMapping
    public ResponseEntity<FacultadEntity> create(@Valid @RequestBody FacultadEntity dto) {
        facultadService.crearFacultad(dto.getNombre(), dto.getDecano(), dto.getUbicacion());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand("new")
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultadEntity> update(@PathVariable int id, @Valid @RequestBody FacultadEntity dto) {
        facultadService.actualizarFacultad(id, dto.getNombre(), dto.getDecano(), dto.getUbicacion());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacultadEntity> delete(@PathVariable int id) {
        facultadService.eliminarFacultad(id);
        return ResponseEntity.noContent().build();
    }
}
