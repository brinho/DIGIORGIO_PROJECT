import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String pass;
    private String name;
    private String surname;
    private FilmList list;
    private Boolean active;


    public User(String username, String pass, String name, String surname, FilmList list) {
        this.username = username;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        this.list = list;
        this.active = false; //di default messo a false
    }

    public String getPass() {
        return pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean login(String username, String pass) {
        if (username.equals(this.username) && pass.equals(this.pass) && !active) {
            active = true;
            return true;
        }else {
            return false;
        }
    }

    public void logout() {
        active = false;
    }


    public void addFilm(Film f) {
        list.addFilm(f);
    }

    public void addCorto(Cortometraggio c) {
        list.addCorto(c);
    }

    public ArrayList<Film> getList() {
        return list.getList();
    }

    public ArrayList<Cortometraggio> getCortoList() {
        return list.getCortoList();
    }

    public void removeFilm(int index) {
        list.removeFilm(index);
    }

    public void removeCorto(int index) {
        list.removeCorto(index);
    }

    public void removeAll() {
        list.removeAll();
    }

    public void removeCortoAll() {
        list.removeCortoAll();
    }

    public void saveList(ArrayList<Film> film_list,String username) {
        list.saveList(film_list,username);
    }

    public void saveCortoList(ArrayList<Cortometraggio> corto_list,String username) {
        list.saveCortoList(corto_list,username);
    }

    public void saveFilmFile(ArrayList<Film> film_list,String username){
        list.saveFilmFile(film_list,username);
    }

    public void saveCortoFile(ArrayList<Cortometraggio> corto_list,String username){
        list.saveCortoFile(corto_list,username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", list=" + list.toString() +
                ", active=" + active +
                '}';
    }
}