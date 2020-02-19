package model.VO;

import java.io.Serializable;

public class IncidenceAdmin extends Administrator implements Serializable {


    static final long serialVersionUID = 42L;
    public IncidenceAdmin(String name, String apell, String mail, String phone, String username, String password, String role) {
        super(name, apell, mail, phone, username, password, role);
    }
}
