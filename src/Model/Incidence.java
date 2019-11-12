package Model;

public class Incidence {

    private int id;
    private IncidenceAdmin name;
    private String mail;

    public Incidence(int id, IncidenceAdmin name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public IncidenceAdmin getName() {
        return name;
    }
}
