import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Services extends Remote {
    public ArrayList<Film> getList() throws RemoteException;
    public void addFilm(Film f) throws RemoteException;
    public void removeFilm(int index) throws RemoteException;
    public void saveList(ArrayList<Film> list) throws RemoteException;
    public void removeAll() throws RemoteException;
    public void addCorto(Cortometraggio c) throws RemoteException;
    public ArrayList<Cortometraggio> getCortoList() throws RemoteException;
    public void removeCorto(int index) throws RemoteException;
    public void removeCortoAll() throws RemoteException;
    public void saveCortoList(ArrayList<Cortometraggio> list) throws RemoteException;

}
