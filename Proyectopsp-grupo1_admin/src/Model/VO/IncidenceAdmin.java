package model.VO;


public class IncidenceAdmin extends Administrator {

    public IncidenceAdmin(String name, String apell, String mail, String phone, String username, String password, String role) {
        super(name, apell, mail, phone, username, password, role);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setApell(String apell) {
        super.setApell(apell);
    }

    @Override
    public void setMail(String mail) {
        super.setMail(mail);
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setRole(String role) {
        super.setRole(role);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getApell() {
        return super.getApell();
    }

    @Override
    public String getMail() {
        return super.getMail();
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getRole() {
        return super.getRole();
    }
}
