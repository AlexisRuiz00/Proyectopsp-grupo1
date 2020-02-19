package model;

import model.VO.Incidence;
import model.VO.IncidenceAdmin;

import java.util.ArrayList;

public interface Repository {

    public ArrayList<IncidenceAdmin> getIncidenceAdmins();
    public void saveIncidenceAdmin(IncidenceAdmin incidenceAdmin);

    public ArrayList<Incidence>getClientIncidences(String mail);
    public ArrayList<Incidence>getAdminIncidences(String adminName);

    public Incidence saveClientIncidence(Incidence incidence, IncidenceAdmin tmpAdmin);
    public Incidence updateIncidence(Incidence incidence);

    public String getLogin(ArrayList<String> credentials);

}
