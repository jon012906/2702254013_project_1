package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import index.DatabaseConnection;
import index.DatabaseSingleton;
import models.Menu;

public class MenuController {
    private static DatabaseConnection db = DatabaseSingleton.getInstance();

    public static List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        String query = "SELECT * FROM menu";

        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String kode = resultSet.getString("kode");
                String nama = resultSet.getString("nama");
                double harga = resultSet.getDouble("harga");
                int stok = resultSet.getInt("stok");

                Menu menu = new Menu(kode, nama, harga, stok);
                menus.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return menus;
    }

    public static boolean insertMenu(Menu menu) {
        String query = "INSERT INTO menu (kode, nama, harga, stok) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, menu.getKode());
            statement.setString(2, menu.getNama());
            statement.setDouble(3, menu.getHarga());
            statement.setInt(4, menu.getStok());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateMenu(String kode, String nama, double harga, int stok) {
        String query = "UPDATE menu SET nama = ?, harga = ?, stok = ? WHERE kode = ?";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, nama);
            statement.setDouble(2, harga);
            statement.setInt(3, stok);
            statement.setString(4, kode);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getAllKodes() {
        List<String> kodes = new ArrayList<>();
        String query = "SELECT kode FROM menu";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String kode = resultSet.getString("kode");
                kodes.add(kode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kodes;
    }

    public static boolean deleteMenu(String kode) {
        String query = "DELETE FROM menu WHERE kode = ?";
        try {
            PreparedStatement statement = db.connection.prepareStatement(query);
            statement.setString(1, kode);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
