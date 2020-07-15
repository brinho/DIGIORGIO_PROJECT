import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Server extends UnicastRemoteObject implements Services {

    HashMap<String,User> user_list = new HashMap<String,User>();

    public Server() throws RemoteException {
        //super(1103);
        super();
    }

    @Override
    public void registerUser(String username,User u) throws RemoteException {
        user_list.put(username,u);
    }

    @Override
    public Boolean loginUser(String username,String pass){
        if(user_list.containsKey(username)){
            System.out.println("SERVER LOG :" + username +" login successful!");
           return user_list.get(username).login(username,pass);
        }
        return false;
    }

    @Override
    public ArrayList<Film> getList(String username) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking getList()");
        return user_list.get(username).getList();
    }

    @Override
    public ArrayList<Cortometraggio> getCortoList(String username) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking getCortoList()");
        return user_list.get(username).getCortoList();
    }


    @Override
    public void addFilm(String username,Film f) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking addFilm()");
        user_list.get(username).addFilm(f);
    }

    @Override
    public void addCorto(String username,Cortometraggio c) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking addCorto()");
        user_list.get(username).addCorto(c);
    }

    @Override
    public void removeFilm(String username,int index) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeFilm()");
        user_list.get(username).removeFilm(index);
    }

    public void removeAll(String username) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeAll()");
        user_list.get(username).removeAll();
    }

    @Override
    public void removeCorto(String username,int index) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeCorto()");
        user_list.get(username).removeCorto(index);
    }

    public void removeCortoAll(String username) throws RemoteException {
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking removeCortoAll()");
        user_list.get(username).removeCortoAll();
    }

    @Override
    public void saveList(String username,ArrayList<Film> list) throws RemoteException{
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking saveList()");
        user_list.get(username).saveList(list);
    }

    @Override
    public void saveCortoList(String username,ArrayList<Cortometraggio> list) throws RemoteException{
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking saveCortoList()");
        user_list.get(username).saveCortoList(list);
    }

    @Override
    public void saveFilmFile(String username,ArrayList<Film> list) throws RemoteException{
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking saveFilmFile()");
        user_list.get(username).saveFilmFile(list);
    }

    @Override
    public void saveCortoFile(String username,ArrayList<Cortometraggio> c_list) throws RemoteException{
        System.out.println("SERVER LOG by Thread: "+Thread.currentThread().getName()+ " invoking saveCortoFile()");
        user_list.get(username).saveCortoFile(c_list);
    }




    public static void main(String[] args){
        System.out.println("------------------------");
        System.out.println("Starting the service ...");
        System.out.println("------------------------");
        try {
            // if not localhost
            //System.setProperty("java.rmi.server.hostname","whitelodge.ns0.it");
            //Registry registry = LocateRegistry.getRegistry();

            Services services = new Server();
            Naming.rebind("filmservice",services);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
