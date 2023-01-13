package com.naloga.AvtoIskalnik.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "avto")
@Data
public class Avto {


    @Id
    @GeneratedValue
    private long id;

    @Column(name = "znamka", nullable = false)
    private String znamka;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "cenaMin", nullable = false)
    private String cenaMin;

    @Column(name = "cenaMax", nullable = false)
    private String cenaMax;
    @Column(name = "prvaRegistracijaMin", nullable = false)
    private String prvaRegistracijaMin;
    @Column(name = "prvaRegistracijaMax", nullable = false)
    private String prvaRegistracijaMax;
    @Column(name = "tipGoriva", nullable = false)
    private String tipGoriva;
    @Column(name = "menjalnik", nullable = false)
    private String menjalnik;
    @Column(name = "prostorninaMotorjaMin", nullable = false)
    private String prostorninaMotorjaMin;

    @Column(name = "prostorninaMotorjaMax", nullable = false)
    private String prostorninaMotorjaMax;

    @Column(name = "mocMotorjaMin", nullable = false)
    private String mocMotorjaMin;

    @Column(name = "mocMotorjaMax", nullable = false)
    private String mocMotorjaMax;

    @Column(name = "lastVisited", nullable = false)
    private String lastVisited;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "source", nullable = false)
    private String source;



}
