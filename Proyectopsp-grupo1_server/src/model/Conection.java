package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

        Connection conn = null;

        public Conection()  {
            try {

                conn = DriverManager.getConnection("jdbc:mysql://localhost/atcs?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * @return La conexión creada.
         */
        public Connection getConn() {
            return conn;
        }

        /**
         * Borra la conexión creada.
         */
        public void Close() {
            conn = null;
        }

    }