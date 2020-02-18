package model.impl;

import model.Conection;
import model.Repository;
import model.VO.Incidence;
import model.VO.IncidenceAdmin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ATCSJDBC implements Repository {

    public ATCSJDBC() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ArrayList<IncidenceAdmin> getIncidenceAdmins() {
        ArrayList<IncidenceAdmin> incidenceAdmins = new ArrayList<>();

        try {
            Conection conn = new Conection();
            Statement statement = conn.getConn().createStatement();
            ResultSet rs = statement.executeQuery("Select * from administrador where role ='IncidenceAdmin'");

            while (rs.next()) {
                IncidenceAdmin incidenceAdmin = null;

                incidenceAdmin = new IncidenceAdmin(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                if (incidenceAdmin != null) {
                    incidenceAdmins.add(incidenceAdmin);
                }
            }

            //CLOSE
            statement.close();
            rs.close();
            conn.Close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidenceAdmins;
    }

    @Override
    public ArrayList<Incidence> getClientIncidences(String mail) {
        ArrayList<Incidence> incidences = new ArrayList<>();

        try {
            Conection conn = new Conection();
            Statement statement = conn.getConn().createStatement();
            ResultSet rs = statement.executeQuery("Select * from incidencia where mail ='" + mail + "'");

            while (rs.next()) {
                Incidence incidence = null;

                incidence = new Incidence(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                if (incidence != null) {
                    incidences.add(incidence);
                }
            }

            //CLOSE
            statement.close();
            rs.close();
            conn.Close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return incidences;
    }

    @Override
    public ArrayList<Incidence> getAdminIncidences(String adminName) {
        ArrayList<Incidence> adminIncidences = new ArrayList<>();

        try {
            Conection conn = new Conection();
            Statement statement = conn.getConn().createStatement();
            ResultSet rs = statement.executeQuery("Select * from incidencia where incidenceAdmin ='" + adminName + "'");

            while (rs.next()) {
                Incidence incidence = null;

                incidence = new Incidence(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                if (incidence != null) {
                    adminIncidences.add(incidence);
                }
            }

            //CLOSE
            statement.close();
            rs.close();
            conn.Close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminIncidences;
    }

    @Override
    public Incidence saveClientIncidence(Incidence incidence, IncidenceAdmin tmpAdmin) {
        try {
            Conection conn = new Conection();
            Statement statement = conn.getConn().createStatement();

            incidence.setId((int) System.currentTimeMillis());
            incidence.setIncidenceAdmin(tmpAdmin.getUsername());

            System.out.println(incidence.getMail());

            statement.execute("INSERT into incidencia values (" + incidence.getId() + ",'" + incidence.getIncidenceAdmin() + "','" + incidence.getMail() + "','" + incidence.getType() + "','" + incidence.getBody() + "')");

            //CLOSE
            statement.close();
            conn.Close();
            return incidence;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new MiExcepcion("Imposible to save to database");
            return null;
        }
    }

    @Override
    public Incidence updateIncidence(Incidence incidence) {
        try {
            Conection conn = new Conection();
            Statement statement = conn.getConn().createStatement();

            statement.executeUpdate("UPDATE incidencia set incidenceAdmin='" + incidence.getIncidenceAdmin() + "', mail='" + incidence.getMail() + "', body='" + incidence.getBody() + "' WHERE id='" + incidence.getId() + "'");
            //CLOSE
            statement.close();
            conn.Close();
            return incidence;
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new MiExcepcion("Imposible to save to database");
            return null;
        }
    }

    @Override
    public String getLogin(ArrayList<String> credentials) {

        String role = "";

        try {

            Conection conn = new Conection();
            Statement statement = conn.getConn().createStatement();

            ResultSet result = statement.executeQuery("select role from administrador where username = '" + credentials.get(0) + "' and password = '" + credentials.get(1) + "'");

            if (result.next()) {

                role = result.getString(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }



}
