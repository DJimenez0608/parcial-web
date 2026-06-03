package com.parcial.parcialweb.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction(value = "status = 0")
@SQLDelete(sql = "UPDATE arco SET status = 1 WHERE id=?")

public class FacultadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombre;
    private String decano;
    private String ubicacion;

    @OneToMany(mappedBy = "facultad", fetch = FetchType.LAZY)
    private List<ProgramaAcademicoEntity> programaAcademicoEntities = new ArrayList<>();




}