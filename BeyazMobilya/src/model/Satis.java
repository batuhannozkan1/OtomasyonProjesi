package model;

import java.time.LocalDate;

public class Satis {
    private String satisId;
    private String musteriId;
    private String mobilyaId;
    private int satilanMiktar;
    private LocalDate satisTarihi;

    public Satis(String satisId, String musteriId, String mobilyaId, int satilanMiktar, LocalDate satisTarihi) {
        this.satisId = satisId;
        this.musteriId = musteriId;
        this.mobilyaId = mobilyaId;
        this.satilanMiktar = satilanMiktar;
        this.satisTarihi = satisTarihi;
    }

    public String getSatisId() {
        return satisId;
    }

    public void setSatisId(String satisId) {
        this.satisId = satisId;
    }

    public String getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(String musteriId) {
        this.musteriId = musteriId;
    }

    public String getMobilyaId() {
        return mobilyaId;
    }

    public void setMobilyaId(String mobilyaId) {
        this.mobilyaId = mobilyaId;
    }

    public int getSatilanMiktar() {
        return satilanMiktar;
    }

    public void setSatilanMiktar(int satilanMiktar) {
        this.satilanMiktar = satilanMiktar;
    }

    public LocalDate getSatisTarihi() {
        return satisTarihi;
    }

    public void setSatisTarihi(LocalDate satisTarihi) {
        this.satisTarihi = satisTarihi;
    }
}


