package informatikm223.terminkalender;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ReservierungenBean implements Serializable {
    private Reservierung reservierung;
    private final List<Reservierung> reservierungen;
    private final List<Raum> raeume;
    private boolean registrierungAbgeschlossen;
    private String schluessel; // Eingegebener Schlüssel
    private boolean zeigeDetails = false;

    private String tempPrivaterSchluessel;
    private String tempOeffentlicherSchluessel;

    public ReservierungenBean() {
        reservierung = new Reservierung();
        reservierungen = new ArrayList<>();
        raeume = new ArrayList<>();
        registrierungAbgeschlossen = false;


        raeume.add(new Raum(101));
        raeume.add(new Raum(102));
        raeume.add(new Raum(103));
        raeume.add(new Raum(104));
        raeume.add(new Raum(105));
    }

    public Reservierung getReservierung() {
        return reservierung;
    }
    public List<Raum> getRaeume() {
        return raeume;
    }
    public void setReservierung(Reservierung reservierung) {
        this.reservierung = reservierung;
    }

    public List<Reservierung> getReservierungen() {
        return reservierungen;
    }

    public boolean isRegistrierungAbgeschlossen() {
        return registrierungAbgeschlossen;
    }

    public String aktualisiereReservierung() {
        // Finden Sie die entsprechende Reservierung in der Liste und aktualisieren Sie sie
        for (int i = 0; i < reservierungen.size(); i++) {
            Reservierung vorhandeneReservierung = reservierungen.get(i);
            if (vorhandeneReservierung.getPrivaterSchluessel().equals(reservierung.getPrivaterSchluessel())) {
                reservierungen.set(i, reservierung);
                break; // Beenden Sie die Schleife, sobald die Reservierung aktualisiert wurde
            }
        }

        // Erstellen Sie ein neues Reservierungs-Objekt für die nächste Eingabe
        reservierung = new Reservierung();

        // Setzen Sie die Registrierung als nicht abgeschlossen
        registrierungAbgeschlossen = false;

        // Leiten Sie zur Bestätigungs- oder Startseite weiter
        return "index"; // Navigieren Sie zurück zur Startseite oder zu einer Bestätigungsseite
    }
    public String loescheReservierung(String privaterSchluessel) {
        // Durchsuchen Sie die Liste der Reservierungen nach der Reservierung mit dem angegebenen privaten Schlüssel
        for (Reservierung r : reservierungen) {
            if (r.getPrivaterSchluessel().equals(privaterSchluessel)) {
                reservierungen.remove(r);
                break; // Beenden Sie die Schleife, sobald die Reservierung gelöscht wurde
            }
        }

        // Leiten Sie zur Bestätigungs- oder Startseite weiter
        return "index"; // Navigieren Sie zurück zur Startseite oder zu einer Bestätigungsseite
    }
    public String addReservierung() {
        tempPrivaterSchluessel = reservierung.getPrivaterSchluessel();
        tempOeffentlicherSchluessel = reservierung.getOeffentlicherSchluessel();

        // Fügen Sie die Reservierung hinzu, erstellen Sie eine neue Reservierung
        reservierungen.add(reservierung);
        reservierung = new Reservierung();

        // Setzen Sie die Registrierung als abgeschlossen
        registrierungAbgeschlossen = true;

        // Leiten Sie zur Erfolgsmeldungsseite weiter
        return "success";
    }
    public Raum findRaumByNummer(int raumNummer) {
        for (Raum raum : raeume) {
            if (raum.getNummer() == raumNummer) {
                return raum;
            }
        }
        return null; // Raum wurde nicht gefunden
    }

    public String showReservationForm() {
        // Setzen Sie die Eigenschaft registrierungAbgeschlossen auf false, wenn Sie zur Eingabeform zurückkehren möchten.
        registrierungAbgeschlossen = false;
        return "reservationForm";
    }
    public void navigateBackToIndex() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }




    public void ladeTeilnehmerEin(Reservierung termin, String email) {
        termin.ladeTeilnehmerEin(email);
    }

    public String getSchluessel() {
        return schluessel;
    }

    public void setSchluessel(String schluessel) {
        this.schluessel = schluessel;
    }

    public String bearbeiteTermin() {
        for (Reservierung r : reservierungen) {
            if (r.getPrivaterSchluessel().equals(schluessel)) {
                this.reservierung = r;
                this.zeigeDetails = true;
                return null; // Bleibt auf der aktuellen Seite
            }
        }
        this.zeigeDetails = false;
        return null;
    }

    public String seheTerminAn() {
        for (Reservierung r : reservierungen) {
            if (r.getOeffentlicherSchluessel().equals(schluessel)) {
                this.reservierung = r;
                this.zeigeDetails = true;
                return null; // Bleibt auf der aktuellen Seite
            }
        }
        this.zeigeDetails = false;
        return null;
    }
    public String getTempPrivaterSchluessel() {
        return tempPrivaterSchluessel;
    }

    public String getTempOeffentlicherSchluessel() {
        return tempOeffentlicherSchluessel;
    }
    public void ueberpruefeSchluessel() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        for (Reservierung r : reservierungen) {
            if (r.getPrivaterSchluessel().equals(schluessel)) {
                this.reservierung = r;
                context.getExternalContext().redirect("BearbeitungsSeite.xhtml");
                return;
            } else if (r.getOeffentlicherSchluessel().equals(schluessel)) {
                this.reservierung = r;
                context.getExternalContext().redirect("AnsichtsSeite.xhtml");
                return;
            }
        }
        context.getExternalContext().redirect("schluesselNichtGefunden.xhtml");
    }

    public boolean isZeigeDetails() {
        return zeigeDetails;
    }

}
