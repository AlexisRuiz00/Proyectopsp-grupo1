package Model;

public class Incidence {

    private int id;
    private IncidenceAdmin name;
    private Client mail;

    public Incidence(int id, IncidenceAdmin name, Client mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public Client getMail() {
        return mail;
    }

    public IncidenceAdmin getName() {
        return name;
    }
}
