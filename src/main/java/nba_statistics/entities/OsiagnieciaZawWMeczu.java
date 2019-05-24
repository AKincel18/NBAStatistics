/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity(name="OsiagnieciaZawWMeczu")
@Table(name = "osiagniecia_zawodnika_w_meczu")
public class OsiagnieciaZawWMeczu {
    
    @EmbeddedId
    private OsiagnieciaZawWMeczuId id;

    @ManyToOne
    @MapsId("id_zawodnika")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_zawodnika")
    private Zawodnicy zawodnik;

    @ManyToOne
    @MapsId("id_meczu")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_meczu")
    private Mecze mecz;
    
    @Column(name = "zdobyte_punkty")
    private int zdobytePunkty;
    
    @Column(name = "przechwyty")
    private int przechwyty;
    
    @Column(name = "zbiorki")
    private int zbiorki;
    
    @Column(name = "bloki")
    private int bloki;
    
    @Column(name = "faule")
    private int faule;
    
    @Column(name = "faule_techniczne")
    private int fauleTech;
    
    public OsiagnieciaZawWMeczu(){};

    public OsiagnieciaZawWMeczu(Zawodnicy zawodnik, Mecze mecz, int zdobytePunkty, int przechwyty, int zbiorki, int bloki, int faule, int fauleTech) {
        this.zawodnik = zawodnik;
        this.mecz = mecz;
        this.zdobytePunkty = zdobytePunkty;
        this.przechwyty = przechwyty;
        this.zbiorki = zbiorki;
        this.bloki = bloki;
        this.faule = faule;
        this.fauleTech = fauleTech;
        this.id = new OsiagnieciaZawWMeczuId();
    }

    public OsiagnieciaZawWMeczuId getId() {
        return id;
    }

    public void setId(OsiagnieciaZawWMeczuId id) {
        this.id = id;
    }

    public int getZdobytePunkty() {
        return zdobytePunkty;
    }

    public void setZdobytePunkty(int zdobytePunkty) {
        this.zdobytePunkty = zdobytePunkty;
    }

    public int getPrzechwyty() {
        return przechwyty;
    }

    public void setPrzechwyty(int przechwyty) {
        this.przechwyty = przechwyty;
    }

    public int getZbiorki() {
        return zbiorki;
    }

    public void setZbiorki(int zbiorki) {
        this.zbiorki = zbiorki;
    }

    public int getBloki() {
        return bloki;
    }

    public void setBloki(int bloki) {
        this.bloki = bloki;
    }

    public int getFaule() {
        return faule;
    }

    public void setFaule(int faule) {
        this.faule = faule;
    }

    public int getFauleTech() {
        return fauleTech;
    }

    public void setFauleTech(int fauleTech) {
        this.fauleTech = fauleTech;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(Zawodnicy zawodnik) {
        this.zawodnik = zawodnik;
    }

    public Mecze getMecz() {
        return mecz;
    }

    public void setMecz(Mecze mecz) {
        this.mecz = mecz;
    }

    @Override
    public String toString() {
        return "OsiagnieciaZawWMeczu{" +
                "id=" + id +
                ", zdobytePunkty=" + zdobytePunkty +
                ", przechwyty=" + przechwyty +
                ", zbiorki=" + zbiorki +
                ", bloki=" + bloki +
                ", faule=" + faule +
                ", fauleTech=" + fauleTech +
                '}';
    }
}