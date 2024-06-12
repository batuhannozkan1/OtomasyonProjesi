package service;

import DB.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mobilya;

public class EnvanterServisi {

    public void mobilyaEkle(Mobilya mobilya) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnect.baglantiAc();
            String sql = "INSERT INTO envanter (ad, kategori, fiyat, stok, malzeme, renk, agirlik, boyutlar, uretim_tarihi, tedarikci_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mobilya.getAd());
            statement.setString(2, mobilya.getKategori());
            statement.setDouble(3, mobilya.getFiyat());
            statement.setInt(4, mobilya.getStok());
            statement.setString(5, mobilya.getMalzeme());
            statement.setString(6, mobilya.getRenk());
            statement.setDouble(7, mobilya.getAgirlik());
            statement.setString(8, mobilya.getBoyutlar());
            statement.setDate(9, new java.sql.Date(mobilya.getUretimTarihi().getTime()));
            statement.setInt(10, mobilya.getTedarikciId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                DBConnect.baglantiKapat(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void mobilyaKaldir(String mobilyaId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnect.baglantiAc();
            String sql = "DELETE FROM envanter WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(mobilyaId));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                DBConnect.baglantiKapat(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Mobilya mobilyaGetirById(String mobilyaId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Mobilya mobilya = null;

        try {
            connection = DBConnect.baglantiAc();
            String sql = "SELECT * FROM envanter WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(mobilyaId));
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mobilya = new Mobilya();
                mobilya.setId(resultSet.getInt("id"));
                mobilya.setAd(resultSet.getString("ad"));
                mobilya.setKategori(resultSet.getString("kategori"));
                mobilya.setFiyat(resultSet.getDouble("fiyat"));
                mobilya.setStok(resultSet.getInt("stok"));
                mobilya.setMalzeme(resultSet.getString("malzeme"));
                mobilya.setRenk(resultSet.getString("renk"));
                mobilya.setAgirlik(resultSet.getDouble("agirlik"));
                mobilya.setBoyutlar(resultSet.getString("boyutlar"));
                mobilya.setUretimTarihi(resultSet.getDate("uretim_tarihi"));
                mobilya.setTedarikciId(resultSet.getInt("tedarikci_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                DBConnect.baglantiKapat(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return mobilya;
    }

    public List<Mobilya> tumMobilyalar() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Mobilya> mobilyaListesi = new ArrayList<>();

        try {
            connection = DBConnect.baglantiAc();
            String sql = "SELECT * FROM envanter";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mobilya mobilya = new Mobilya();
                mobilya.setId(resultSet.getInt("id"));
                mobilya.setAd(resultSet.getString("ad"));
                mobilya.setKategori(resultSet.getString("kategori"));
                mobilya.setFiyat(resultSet.getDouble("fiyat"));
                mobilya.setStok(resultSet.getInt("stok"));
                mobilya.setMalzeme(resultSet.getString("malzeme"));
                mobilya.setRenk(resultSet.getString("renk"));
                mobilya.setAgirlik(resultSet.getDouble("agirlik"));
                mobilya.setBoyutlar(resultSet.getString("boyutlar"));
                mobilya.setUretimTarihi(resultSet.getDate("uretim_tarihi"));
                mobilya.setTedarikciId(resultSet.getInt("tedarikci_id"));

                mobilyaListesi.add(mobilya);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                DBConnect.baglantiKapat(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return mobilyaListesi;
    }
}
