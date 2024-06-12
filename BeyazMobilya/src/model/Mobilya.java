package model;

import java.util.Date;

public class Mobilya {

    private int id;
    private String ad;
    private String kategori;
    private double fiyat;
    private int stok;
    private String malzeme;
    private String renk;
    private double agirlik;
    private String boyutlar;
    private Date uretimTarihi;
    private int tedarikciId;

    public Mobilya() {
        this.uretimTarihi = new Date(); // Varsayılan olarak şu anki tarih atanıyor
    }

    public Mobilya(int id, String ad, String kategori, double fiyat, int stok) {
        this.id = id;
        this.ad = ad;
        this.kategori = kategori;
        this.fiyat = fiyat;
        this.stok = stok;
        this.uretimTarihi = new Date(); // Varsayılan olarak şu anki tarih atanıyor
    }

    

    // Getter ve setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getMalzeme() {
        return malzeme;
    }

    public void setMalzeme(String malzeme) {
        this.malzeme = malzeme;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public double getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(double agirlik) {
        this.agirlik = agirlik;
    }

    public String getBoyutlar() {
        return boyutlar;
    }

    public void setBoyutlar(String boyutlar) {
        this.boyutlar = boyutlar;
    }

    public Date getUretimTarihi() {
        return uretimTarihi;
    }

    public void setUretimTarihi(Date uretimTarihi) {
        this.uretimTarihi = uretimTarihi;
    }

    public int getTedarikciId() {
        return tedarikciId;
    }

    public void setTedarikciId(int tedarikciId) {
        this.tedarikciId = tedarikciId;
    }
}
