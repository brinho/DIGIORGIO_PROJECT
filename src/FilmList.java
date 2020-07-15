import java.io.*;
import java.util.ArrayList;

public class FilmList implements Serializable{

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
            Film x = new Film(f.getTitolo(),f.getAnno(),f.getDurata(),f.getVoto(),f.getVisto());
            anotherlist.add(x);
        }
        return anotherlist;
    }

    public ArrayList<Cortometraggio> getCortoList() {
        ArrayList<Cortometraggio> anotherlist = new ArrayList<>();
        for (Cortometraggio c:c_list) {
            Cortometraggio x = new Cortometraggio(c.getTitolo(),c.getAnno(),c.getDurata(),c.getVoto(),c.getVisto(),c.getMetraggio());
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

    public void saveFilmFile(ArrayList<Film> list){
        try (var myWriter = new BufferedWriter(new FileWriter(("film_list.txt"))))  {
            myWriter.write(list.toString());
            System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " Scritto correttamente sul file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCortoFile(ArrayList<Cortometraggio> c_list){
        try (var myWriter = new BufferedWriter(new FileWriter(("corto_list.txt"))))  {
            myWriter.write(c_list.toString());
            System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " Scritto correttamente sul file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
