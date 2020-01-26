package Model;

import Model.VO.Incidence;
import Model.VO.IncidenceAdmin;
import Model.impl.ATCSJDBC;

import java.util.ArrayList;

public class Model {

    Repository repository;
    ArrayList<IncidenceAdmin> incidenceAdmins;

    public Model(){
        repository = new ATCSJDBC(this);
        incidenceAdmins = repository.getIncidenceAdmins();
    }

    public IncidenceAdmin getRandomIncidenceAdmin(){
        return incidenceAdmins.get((int)Math.random()*incidenceAdmins.size());
    }

    public ArrayList<Incidence> getClientIncidences(String mail){
        return repository.getClientIncidences(mail);
    }

    public Incidence saveIncidence(Incidence incidence){
        return repository.saveClientIncidence(incidence);
    }

    public Incidence updateIncidence(Incidence incidence){
        return repository.updateIncidence(incidence);
    }

}
