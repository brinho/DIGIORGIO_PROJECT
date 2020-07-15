import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String pass;
    private String name;
    private String surname;
    private FilmList list;
    private Boolean active;//di default messo a false



    public User(String username, String pass,FilmList list) {
        this.username = username;
        this.pass = String.valueOf(pass.hashCode());
        this.list = list;
        this.active = false;
    }

    public User(String username, String pass, String name, String surname, FilmList list) {
        this.username = username;
        this.pass = String.valueOf(pass.hashCode());
        this.name = name;
        this.surname = surname;
        this.list = list;
        this.active = false;
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
        }else
            return false;
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

    public void saveList(ArrayList<Film> film_list) {
        list.saveList(film_list);
    }

    public void saveCortoList(ArrayList<Cortometraggio> corto_list) {
        list.saveCortoList(corto_list);
    }

    public void saveFilmFile(ArrayList<Film> film_list){
        list.saveFilmFile(film_list);
    }

    public void saveCortoFile(ArrayList<Cortometraggio> corto_list){
        list.saveCortoFile(corto_list);
    }


}