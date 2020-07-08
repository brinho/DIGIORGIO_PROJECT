import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements Services {
    FilmList film_list = new FilmList();

    public Server() throws RemoteException {
        super(1103);
        //super();
    }

    @Override
    public ArrayList<Film> getList() throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking getList()");
        return film_list.getList();
    }

    @Override
    public ArrayList<Cortometraggio> getCortoList() throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking getCortoList()");
        return film_list.getCortoList();
    }


    @Override
    public void addFilm(Film f) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking addFilm()");
        film_list.addFilm(f);
    }

    @Override
    public void addCorto(Cortometraggio c) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking addCorto()");
        film_list.addCorto(c);
    }

    @Override
    public void removeFilm(int index) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeFilm()");
        film_list.removeFilm(index);
    }

    public void removeAll() throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeAll()");
        film_list.removeAll();
    }

    @Override
    public void removeCorto(int index) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeCorto()");
        film_list.removeCorto(index);
    }

    public void removeCortoAll() throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeCortoAll()");
        film_list.removeAll();
    }

    @Override
    public void saveList(ArrayList<Film> list) throws RemoteException{
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking saveList()");
        film_list.saveList(list);
    }

    @Override
    public void saveCortoList(ArrayList<Cortometraggio> list) throws RemoteException{
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking saveCortoList()");
        film_list.saveCortoList(list);
    }


    public static void main(String[] args){
        System.out.println("------------------------");
        System.out.println("Starting the service ...");
        System.out.println("------------------------");
        try {
            // if not localhost
            System.setProperty("java.rmi.server.hostname","whitelodge.ns0.it");
            Registry registry = LocateRegistry.getRegistry();

            Services services = new Server();
            Naming.rebind("filmservice",services);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
