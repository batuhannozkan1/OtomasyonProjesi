package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/beyazmobilya";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection baglantiAc() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void baglantiKapat(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, "Bağlantı kapatılamadı!", ex);
            }
        }
    }

    public static void main(String[] args) {
        try (Connection connection = baglantiAc()) {
            System.out.println("Bağlantı başarılı: " + connection);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, "Veritabanı bağlantısı açılırken hata oluştu!", ex);
        }
    }
}

