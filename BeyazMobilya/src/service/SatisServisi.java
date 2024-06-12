package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Satis;
import DB.DBConnect;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SatisServisi {

    private Connection connection;

    public SatisServisi() {
        try {
            this.connection = DBConnect.baglantiAc(); // Veritabanı bağlantısını aç
        } catch (SQLException ex) {
            Logger.getLogger(SatisServisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void satisEkle(Satis satis) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO satis (satis_id, musteri_id, urun_id, adet, tarih) VALUES (?, ?, ?, ?, ?)"
        )) {
            if (satis.getMusteriId() == null || satis.getMusteriId().isEmpty()) {
                throw new IllegalArgumentException("Müşteri ID'si boş olamaz.");
            }

            statement.setString(1, satis.getSatisId());
            statement.setString(2, satis.getMusteriId());
            statement.setString(3, satis.getMobilyaId());
            statement.setInt(4, satis.getSatilanMiktar());
            statement.setDate(5, java.sql.Date.valueOf(satis.getSatisTarihi()));

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Satış başarıyla eklenmiştir.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Satış eklenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanına satış eklenirken bir sorun oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQL Hatası: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Beklenmeyen bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            System.err.println("Beklenmeyen Hata: " + ex.getMessage());
        }
    }

    public List<Satis> satisGetirByMusteriId(String musteriId) {
        List<Satis> musteriSatislar = new ArrayList<>();
        try {
            String sql = "SELECT * FROM satis WHERE musteri_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, musteriId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String satisId = resultSet.getString("satis_id");
                String mobilyaId = resultSet.getString("urun_id");
                int adet = resultSet.getInt("adet");
                Date tarih = resultSet.getDate("tarih");
                LocalDate satisTarihi = tarih.toLocalDate();

                Satis satis = new Satis(satisId, musteriId, mobilyaId, adet, satisTarihi);
                musteriSatislar.add(satis);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanından satışlar getirilirken bir sorun oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQL Hatası: " + ex.getMessage());
        }
        return musteriSatislar;
    }

    public List<Satis> tumSatislar() {
        List<Satis> tumSatislar = new ArrayList<>();
        try {
            String sql = "SELECT * FROM satis";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String satisId = resultSet.getString("satis_id");
                String musteriId = resultSet.getString("musteri_id");
                String mobilyaId = resultSet.getString("urun_id");
                int adet = resultSet.getInt("adet");
                Date tarih = resultSet.getDate("tarih");
                LocalDate satisTarihi = tarih.toLocalDate();

                Satis satis = new Satis(satisId, musteriId, mobilyaId, adet, satisTarihi);
                tumSatislar.add(satis);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Veritabanından satışlar getirilirken bir sorun oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQL Hatası: " + ex.getMessage());
        }
        return tumSatislar;
    }
}
