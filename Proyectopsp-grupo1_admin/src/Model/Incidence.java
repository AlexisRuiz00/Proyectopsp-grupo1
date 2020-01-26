package Model;

public class Incidence {

    private int id;
    private String incidenceAdmin;
    private String mail;
    private String type;
    private String body;

    public Incidence(int id, String incidenceAdmin, String mail, String type, String body) {
        this.id = id;
        this.incidenceAdmin = incidenceAdmin;
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
    public String getIncidenceAdmin() {
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
}
