package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DB.DBConnect;

public class SatisDAO {

    public void addSatis(Satis satis) {
        String sql = "INSERT INTO satis (satis_id, musteri_id, urun_id, adet, tarih) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DBConnect.baglantiAc();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, satis.getSatisId());
            statement.setString(2, satis.getMusteriId());
            statement.setString(3, satis.getMobilyaId());
            statement.setInt(4, satis.getSatilanMiktar());
            statement.setDate(5, java.sql.Date.valueOf(satis.getSatisTarihi()));

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SatisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Satis> getAllSatis() {
        List<Satis> satislar = new ArrayList<>();
        String sql = "SELECT * FROM satis";

        try (Connection connection = DBConnect.baglantiAc();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Satis satis = new Satis(
                    resultSet.getString("satis_id"),
                    resultSet.getString("musteri_id"),
                    resultSet.getString("urun_id"),
                    resultSet.getInt("adet"),
                    resultSet.getDate("tarih").toLocalDate()
                );
                satislar.add(satis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SatisDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return satislar;
    }
}

