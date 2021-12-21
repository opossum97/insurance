package com.example.insurance;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgersSQL {

    final static String url = "jdbc:postgresql://localhost:5432/insure";
    final static String user = "postgres";
    final static String password = "fghjkkl";

    public static Object checkPassword(String userLogin, String userPassword) {
        String notUser = "notUser";
        String queryAdmin = "SELECT * FROM admin WHERE login='" + userLogin + "' AND password='" + userPassword +"'";
        String queryClient = "SELECT * FROM client WHERE login='" + userLogin + "' AND password='" + userPassword +"'";

        Admin admin = null;
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryAdmin)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        Client client = null;
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryClient)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if(admin != null) {
            return admin;
        } if (client != null) {
            return client;
        }
        return notUser;
    }

    public static ArrayList<TableView> getVidiAndUslugi() {
        String queryVidi = "SELECT * FROM vidi ORDER BY name ASC";
        String uslugaIds = "(";

        ArrayList<Vidi> vidi = new ArrayList<Vidi>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryVidi)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                vidi.add(new Vidi(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                    rs.getInt(5), rs.getInt(6)));
                uslugaIds += rs.getInt(5) + ", ";
            }
            uslugaIds = uslugaIds.substring(0, uslugaIds.length() - 2);
            uslugaIds += ")";
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUsluga = "SELECT * FROM usluga WHERE id IN " + uslugaIds;
        ArrayList<Usluga> uslugas = new ArrayList<Usluga>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryUsluga)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                uslugas.add(new Usluga(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        ArrayList<TableView> tableViews = new ArrayList<>();
        for(Integer i = 0; i < vidi.size(); i++) {
            Usluga u = null;
            for(Integer j = 0; j < uslugas.size(); j++) {
                if(uslugas.get(j).getId() == vidi.get(i).getUslugaId()) {
                    u = uslugas.get(j);
                    break;
                }
            }
            TableView tv = new TableView(i + 1, vidi.get(i).getId(), vidi.get(i).getName(), vidi.get(i).getPrice(), vidi.get(i).getNumFamily(),
                    vidi.get(i).getNumber(), u.getId(), u.getName(), u.getPlace());
            tableViews.add(tv);
        }

        return tableViews;
    }

    public static void deleteVidUluga(TableView tableView) {
        String queryDeleteVid = "DELETE FROM vidi WHERE id = " + tableView.getIdVida();
        String queryDeleteUsluga = "DELETE FROM usluga WHERE id = " + tableView.getIdUslugi();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryDeleteVid)) {
             ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryDeleteUsluga)) {
             ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static void insertVidUsluga(String nameUsluga, String place,
                                       String nameVid, Integer price, Integer number, Integer numFamily) {

        String queryU = "INSERT INTO usluga (name, place) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryU)) {
            pst.setString(1, nameUsluga);
            pst.setString(2, place);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryUsluga = "SELECT * FROM usluga WHERE name = '" + nameUsluga + "' ORDER BY Id DESC";
        ArrayList<Usluga> uslugas = new ArrayList<Usluga>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryUsluga)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                uslugas.add(new Usluga(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        System.out.println(uslugas.get(0).getId());

        String queryV = "INSERT INTO vidi (name, price, number_family, uslugaId, number) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryV)) {
            pst.setString(1, nameVid);
            pst.setInt(2, price);
            pst.setInt(3, numFamily);
            pst.setInt(4, uslugas.get(0).getId());
            pst.setInt(5, number);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static ArrayList<TableViewClient> getContactsToDelete() {
        String queryClient = "SELECT * FROM contract WHERE dateOcon <= CURRENT_DATE ORDER BY clientId ASC";
        String clientIds = "(";

        ArrayList<Contract> contracts = new ArrayList<Contract>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryClient)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                contracts.add(new Contract(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getDate(6), rs.getDate(7),
                        rs.getInt(8), rs.getInt(9)));
                clientIds += rs.getInt(9) + ", ";
            }
            clientIds = clientIds.substring(0, clientIds.length() - 2);
            clientIds += ")";
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        String queryClien = "SELECT * FROM client WHERE id IN " + clientIds;
        ArrayList<Client> clients = new ArrayList<Client>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryClien)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        Integer counter = 1;
        ArrayList<TableViewClient> tableViewClients = new ArrayList<>();
        for(Integer i = 0; i < contracts.size(); i++) {
            for(Integer j = 0; j < clients.size(); j++) {
                if(clients.get(j).getId() == contracts.get(i).getClientId()) {
                    TableViewClient tableViewClient = new TableViewClient(counter, clients.get(j).getId(), clients.get(j).getFirstName(),
                            clients.get(j).getLastName(), contracts.get(i).getPolzovanie(), contracts.get(i).getId(),
                            contracts.get(i).getName(), contracts.get(i).getPrice(), contracts.get(i).getOplata(),
                            contracts.get(i).getDateZacl(), contracts.get(i).getDateOcon());
                    tableViewClients.add(tableViewClient);
                }
            }
        }

        return tableViewClients;
    }

    public static void deleteClient(TableViewClient tableViewClient) {
        String queryDeleteClient = "DELETE FROM client WHERE id = " + tableViewClient.getId();
        String queryDeleteContract= "DELETE FROM contract WHERE id = " + tableViewClient.getContractId();

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryDeleteClient)) {
            ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryDeleteContract)) {
            ResultSet rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static ArrayList<TableViewUse> getVidClienta(Client client) {

        String queryContract = "SELECT * FROM contract WHERE dateOcon >= CURRENT_DATE AND clientId = " + client.getId();
        String vidIds = "(";
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryContract)) {
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                contracts.add(new Contract(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getDate(6), rs.getDate(7),
                        rs.getInt(8), rs.getInt(9)));
                vidIds += rs.getInt(8) + ", ";
            }
            if(contracts.size() != 0) {
                vidIds = vidIds.substring(0, vidIds.length() - 2);
                vidIds += ")";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        ArrayList<TableViewUse> tableViews = new ArrayList<>();
        if(contracts.size() != 0) {
            String queryVidi = "SELECT * FROM vidi WHERE id IN " + vidIds;
            String uslugaIds = "(";
            ArrayList<Vidi> vidi = new ArrayList<Vidi>();
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pst = con.prepareStatement(queryVidi)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    vidi.add(new Vidi(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
                            rs.getInt(5), rs.getInt(6)));
                    uslugaIds += rs.getInt(5) + ", ";
                }
                uslugaIds = uslugaIds.substring(0, uslugaIds.length() - 2);
                uslugaIds += ")";
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }

            String queryUsluga = "SELECT * FROM usluga WHERE id IN " + uslugaIds;
            ArrayList<Usluga> uslugas = new ArrayList<Usluga>();
            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pst = con.prepareStatement(queryUsluga)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    uslugas.add(new Usluga(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
            System.out.println(uslugas.get(0).getName());

            for(Integer i = 0; i < vidi.size(); i++) {
                Usluga u = null;
                for(Integer j = 0; j < uslugas.size(); j++) {
                    if(uslugas.get(j).getId() == vidi.get(i).getUslugaId()) {
                        u = uslugas.get(j);
                        break;
                    }
                }
                Contract c = null;
                for(Integer j = 0; j < contracts.size(); j++) {
                    if(contracts.get(j).getVidId() == vidi.get(i).getId()) {
                        c = contracts.get(j);
                        break;
                    }
                }
                TableViewUse tv = new TableViewUse(i + 1, vidi.get(i).getId(), vidi.get(i).getName(), vidi.get(i).getPrice(),
                        vidi.get(i).getNumFamily(), vidi.get(i).getNumber(), u.getId(), u.getName(), u.getPlace(), c.getId(),
                        c.getPolzovanie(), c.getOplata(), c.getDateZacl(), c.getDateOcon());
                tableViews.add(tv);
            }
        }
        return tableViews;
    }

    public static void changePosechenie(TableViewUse tableViewUse) {
        String query = "UPDATE contract SET polzovanie = " + (tableViewUse.getPolzovanie() + 1) +" WHERE id = " + tableViewUse.getContractId();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static void insertContractAndBill(String nameContract, Integer price, Integer oplata, String dateO,
                                             String dateZ, Integer vidId, Integer clientId, Integer numKV) {
        String queryContract = "INSERT INTO contract (name, price, oplata, dateZacl, dateOcon, vidId, clientId) VALUES ('" +
                nameContract + "', " + price + ", " + oplata + ", '" +
                dateO +"', '" + dateZ + "', " + vidId + ", " + clientId + ")";
        System.out.println(queryContract);
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryContract)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        queryContract = "SELECT * FROM contract WHERE name = '" + nameContract + "' ORDER BY Id DESC";
        String queryBill = "INSERT INTO bill (sum, number , contractId) VALUES (?, ?, ?)";
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryContract)) {
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                contracts.add(new Contract(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getDate(6), rs.getDate(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryBill)) {
            pst.setInt(1, oplata);
            pst.setInt(2, numKV);
            pst.setInt(3, contracts.get(0).getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static void makePayBill(String nameContract, Integer sum, Integer numKV) {
        String queryContract = "SELECT * FROM contract WHERE name = '" + nameContract + "' AND clientId = " + HelloController.client.getId();
        String queryBill = "INSERT INTO bill (sum, number , contractId) VALUES (?, ?, ?)";
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryContract)) {
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                contracts.add(new Contract(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getDate(6), rs.getDate(7),
                        rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryBill)) {
            pst.setInt(1, sum);
            pst.setInt(2, numKV);
            pst.setInt(3, contracts.get(0).getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        Integer opl = contracts.get(0).getOplata() + sum;
        queryContract = "UPDATE contract SET oplata = " + opl +" WHERE id = " + contracts.get(0).getId();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryContract)) {
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(JavaPostgersSQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
