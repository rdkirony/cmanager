package com.br.cmanager.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Table(name = "SEMESTRE_MATERIA")
@Entity
class SemestreMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @ManyToMany
    @JoinColumn(name = "MATERIA_ID", referencedColumnName = "ID")
    var materia: List<Materia> = ArrayList()

    @OneToOne
    @JoinColumn(name = "SEMESTRE_ID", referencedColumnName = "ID")
    var semestre: Semestre = Semestre()
}