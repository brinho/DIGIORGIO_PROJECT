import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FilmList {

    private ArrayList<Film> list = new ArrayList<>();
    private ArrayList<Cortometraggio> c_list = new ArrayList<>();

    public void addFilm(Film f) {
        list.add(f);
    }
    public void addCorto(Cortometraggio c) {
        c_list.add(c);
    }

    public void removeFilm(int index) {
        list.remove(index);
    }
    public void removeCorto(int index) {
        c_list.remove(index);
    }

    public void removeAll() { list.clear(); }
    public void removeCortoAll() { c_list.clear(); }

    public ArrayList<Film> getList() {
        ArrayList<Film> anotherlist = new ArrayList<>();
        for (Film f:list) {
            Film x = new Film(f.getTitolo(),f.getAnno(),f.getDurata());
            anotherlist.add(x);
        }
        return anotherlist;
    }

    public ArrayList<Cortometraggio> getCortoList() {
        ArrayList<Cortometraggio> anotherlist = new ArrayList<>();
        for (Cortometraggio c:c_list) {
            Cortometraggio x = new Cortometraggio(c.getTitolo(),c.getAnno(),c.getDurata(),c.getMetraggio());
            anotherlist.add(x);
        }
        return anotherlist;
    }

    public void saveList(ArrayList<Film> list){
        try (var oos = new ObjectOutputStream(new FileOutputStream("film.ser"))) {
            oos.writeObject(list);
            //oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveCortoList(ArrayList<Cortometraggio> list){
        try (var oos = new ObjectOutputStream(new FileOutputStream("corto.ser"))) {
            oos.writeObject(list);
            //oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
