package Model;

import Model.VO.Incidence;
import Model.VO.IncidenceAdmin;

import java.util.ArrayList;

public interface Repository {

    public ArrayList<IncidenceAdmin> getIncidenceAdmins();

    public ArrayList<Incidence>getClientIncidences(String mail);
    public ArrayList<Incidence>getAdminIncidences(String adminName);

    public Incidence saveClientIncidence(Incidence incidence);
    public Incidence updateIncidence(Incidence incidence);

    public String getLogin(ArrayList<String> credentials);

}
