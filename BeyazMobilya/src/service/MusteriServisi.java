package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Musteri;

public class MusteriServisi {

    private List<Musteri> musteriler;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/beyazmobilya?useSSL=false";
    private final String kullaniciAdi = "root";
    private final String sifre = "your_password"; // MySQL şifrenizi buraya yazın

    public MusteriServisi() {
        this.musteriler = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(url, kullaniciAdi, sifre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void musteriEkle(Musteri musteri) {
        musteriler.add(musteri);
        String sql = "INSERT INTO Musteri (id, ad, telefon, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, musteri.getId());
            preparedStatement.setString(2, musteri.getAd());
            preparedStatement.setString(3, musteri.getTelefon());
            preparedStatement.setString(4, musteri.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void musteriKaldir(String musteriId) {
        musteriler.removeIf(musteri -> musteri.getId().equals(musteriId));
        String sql = "DELETE FROM Musteri WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, musteriId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Musteri musteriGetirById(String musteriId) {
        String sql = "SELECT * FROM Musteri WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, musteriId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String ad = resultSet.getString("ad");
                String telefon = resultSet.getString("telefon");
                String email = resultSet.getString("email");
                return new Musteri(id, ad, telefon, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Musteri> tumMusteriler() {
        List<Musteri> musteriler = new ArrayList<>();
        String sql = "SELECT * FROM Musteri";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String ad = resultSet.getString("ad");
                String telefon = resultSet.getString("telefon");
                String email = resultSet.getString("email");
                musteriler.add(new Musteri(id, ad, telefon, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musteriler;
    }

    // Diğer metotlar burada olacak...

}

