package Model;


import java.io.Serializable;

public class Incidence implements Serializable {

    static final long serialVersionUID = 42L;
    private int id;
    private String incidenceAdmin;
    private String mail;
    private String type;
    private String body;

    public Incidence(String mail, String type) {
        this.mail = mail;
        this.type = type;
    }



    public Incidence(int id, String IncidenceAdmin, String mail, String type, String body) {
        this.id = id;
        this.incidenceAdmin = IncidenceAdmin;
        this.mail = mail;
        this.type = type;
        this.body = body;
    }

    public int getId() {
        return id;
    }
    public String getMail() {
        return mail;
    }
    public String getName() {
        return incidenceAdmin;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return ""+this.id;
    }
}