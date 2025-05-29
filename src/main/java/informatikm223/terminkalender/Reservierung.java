package informatikm223.terminkalender;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import java.util.UUID;
@ManagedBean
@RequestScoped

public class Reservierung {
    private String datum;
    private String von;
    private String bis;
    private int raumNummer;
    @Size(min=10, max=200, message="Min 10 Zeichen, max 200 Zeichen")
    private String bemerkung;
    @Pattern(regexp = "[A-Za-z,\\s]*", message = "Nur Buchstaben, Kommas sind erlaubt")
    private String teilnehmer;
    private String privaterSchluessel;
    private String oeffentlicherSchluessel;

    public Reservierung() {
        // Generiere private und öffentliche Schlüssel
        this.privaterSchluessel = UUID.randomUUID().toString();
        this.oeffentlicherSchluessel = UUID.randomUUID().toString();
    }

    // Getter und Setter für das Datum
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    // Getter und Setter für die Startzeit
    public String getVon() {
        return von;
    }

    public void setVon(String von) {
        this.von = von;
    }

    // Getter und Setter für die Endzeit
    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis;
    }

    // Getter und Setter für die Raumnummer
    public int getRaumNummer() {
        return raumNummer;
    }

    public void setRaumNummer(int raumNummer) {
        this.raumNummer = raumNummer;
    }

    // Getter und Setter für die Bemerkung
    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    // Getter und Setter für die Teilnehmer
    public String getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(String teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    // Getter für den privaten Schlüssel
    public String getPrivaterSchluessel() {
        return privaterSchluessel;
    }

    // Getter für den öffentlichen Schlüssel
    public String getOeffentlicherSchluessel() {
        return oeffentlicherSchluessel;
    }

    public void bearbeiteTermin() {
        // Implementiere die Bearbeitung des Termins hier
    }

    public void loescheTermin() {
        // Implementiere die Löschung des Termins hier
    }

    public void ladeTeilnehmerEin(String email) {
        // Implementiere die Einladung von Teilnehmern hier
    }
}


