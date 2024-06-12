package model;

public class Musteri {

    private String id;
    private String ad;
    private String telefon;
    private String email;

    public Musteri(String id, String ad, String telefon, String email) {
        this.id = id;
        this.ad = ad;
        this.telefon = telefon;
        this.email = email;
    }

    // Getter ve setter metodları
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
