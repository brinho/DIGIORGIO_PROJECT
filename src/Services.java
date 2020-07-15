import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Services extends Remote {
    public ArrayList<Film> getList(String username) throws RemoteException;
    public void addFilm(String username,Film f) throws RemoteException;
    public void removeFilm(String username,int index) throws RemoteException;
    public void saveList(String username,ArrayList<Film> list) throws RemoteException;
    public void removeAll(String username) throws RemoteException;
    public void addCorto(String username,Cortometraggio c) throws RemoteException;
    public ArrayList<Cortometraggio> getCortoList(String username) throws RemoteException;
    public void removeCorto(String username,int index) throws RemoteException;
    public void removeCortoAll(String username) throws RemoteException;
    public void saveCortoList(String username,ArrayList<Cortometraggio> list) throws RemoteException;
    public void saveFilmFile(String username,ArrayList<Film> list) throws RemoteException;
    public void saveCortoFile(String username,ArrayList<Cortometraggio> c_list) throws RemoteException;
    public void registerUser(String username,User u) throws RemoteException;
    public Boolean loginUser(String username,String pass) throws RemoteException;

}
