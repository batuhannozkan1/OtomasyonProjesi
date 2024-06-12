package app;

import java.time.LocalDate;
import model.Mobilya;
import model.Musteri;
import model.Satis;
import service.EnvanterServisi;
import service.MusteriServisi;
import service.SatisServisi;

import java.util.Date;

public class AnaUygulama {

    public static void main(String[] args) {
        // Servisleri oluştur
        EnvanterServisi envanterServisi = new EnvanterServisi();
        MusteriServisi musteriServisi = new MusteriServisi();
        SatisServisi satisServisi = new SatisServisi();

        // Envantere mobilya ekle
        Mobilya sandalye = new Mobilya(1, "Sandalye", "Oturma", 49.99, 10);
        Mobilya masa = new Mobilya(2, "Masa", "Yemek", 99.99, 5);

        envanterServisi.mobilyaEkle(sandalye);
        envanterServisi.mobilyaEkle(masa);

        // Müşteri ekle
        Musteri musteri = new Musteri("1", "Ahmet Yılmaz", "555-1234", "ahmet.yilmaz@example.com");
        musteriServisi.musteriEkle(musteri);

        // Satış ekle
        LocalDate satisTarihi = LocalDate.now();
        Satis satis = new Satis("1", "1", "1", 2, satisTarihi);

        satisServisi.satisEkle(satis);

        // Verileri görüntüle
        System.out.println("Envanter:");
        for (Mobilya mobilya : envanterServisi.tumMobilyalar()) {
            System.out.println("ID: " + mobilya.getId() + ", Ad: " + mobilya.getAd() + ", Tür: " + mobilya.getKategori() + ", Fiyat: " + mobilya.getFiyat() + ", Miktar: " + mobilya.getStok());
        }

        System.out.println("\nMüşteriler:");
        for (Musteri m : musteriServisi.tumMusteriler()) {
            System.out.println("ID: " + m.getId() + ", Ad: " + m.getAd() + ", Telefon: " + m.getTelefon() + ", Email: " + m.getEmail());
        }

        System.out.println("\nSatışlar:");
        for (Satis s : satisServisi.tumSatislar()) {
            System.out.println("Satış ID: " + s.getSatisId() + ", Müşteri ID: " + s.getMusteriId() + ", Mobilya ID: " + s.getMobilyaId() + ", Satılan Miktar: " + s.getSatilanMiktar() + ", Satış Tarihi: " + s.getSatisTarihi());
        }
    }
}
