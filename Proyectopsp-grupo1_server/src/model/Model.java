package model;

import model.VO.Incidence;
import model.VO.IncidenceAdmin;
import java.util.ArrayList;
import model.impl.ATCSJDBC;

public class Model {

    Repository repository;
    ArrayList<IncidenceAdmin> incidenceAdmins;

    public Model(){
        repository = new ATCSJDBC();
        incidenceAdmins = repository.getIncidenceAdmins();
    }

    public IncidenceAdmin getRandomIncidenceAdmin(){
        return incidenceAdmins.get((int)Math.random()*incidenceAdmins.size());
    }

    public ArrayList<Incidence> getClientIncidences(String mail){
        return repository.getClientIncidences(mail);
    }

    public Incidence saveIncidence(Incidence incidence){
        return repository.saveClientIncidence(incidence,this.getRandomIncidenceAdmin());
    }

    public Incidence updateIncidence(Incidence incidence){
        return repository.updateIncidence(incidence);
    }

    public String getLogin(ArrayList<String> credentials) {
        return repository.getLogin(credentials);
    }

    public ArrayList<Incidence> getAdminIncidences(String adminName){
        return repository.getAdminIncidences(adminName);
    }


    public ArrayList<IncidenceAdmin> getIncidenceAdmins(){
        return repository.getIncidenceAdmins();
    }
}
