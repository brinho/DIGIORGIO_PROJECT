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

    public void saveList(ArrayList<Film> list,String username){
        String nome_file = username + "_film.ser";
        try (var oos = new ObjectOutputStream(new FileOutputStream(nome_file))) {
            oos.writeObject(list);
            //oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCortoList(ArrayList<Cortometraggio> list,String username){
        String nome_file = username + "_corto.ser";
        try (var oos = new ObjectOutputStream(new FileOutputStream(nome_file))) {
            oos.writeObject(list);
            //oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFilmFile(ArrayList<Film> list,String username){
        String nome_file = username + "_film_list.txt";
        try (var myWriter = new BufferedWriter(new FileWriter((nome_file))))  {
            myWriter.write(list.toString());
            System.out.println("SERVER LOG : "+username+ " Scritto correttamente sul file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCortoFile(ArrayList<Cortometraggio> c_list,String username){
        String nome_file = username + "_corto_list.txt";
        try (var myWriter = new BufferedWriter(new FileWriter((nome_file))))  {
            myWriter.write(c_list.toString());
            System.out.println("SERVER LOG : "+username+ " Scritto correttamente sul file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "{ Film_list=" + list +
                ", corto_list=" + c_list +
                '}';
    }
}
