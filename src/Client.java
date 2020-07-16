import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        if (args.length!=2)  {
            System.out.println("Usage: java Client <server address> <service name>");
            return;
        }


        String address = args[0];
        String service_name = args[1];


        try {
            Services server = (Services) Naming.lookup("rmi://" + address + "/" + service_name);

            Scanner sc3 = new Scanner(System.in);
            Scanner access = new Scanner(System.in);

            boolean go2= true;
            FilmList list1 = new FilmList();
            HashMap<String,User> u_list;

            while(go2) {
                System.out.println("Benvenuto in Film Diary!");
                System.out.print("Opzioni di accesso [1] registrazione, [2] login :");
                u_list = server.getUser_list();
                int choice_access = sc3.nextInt();
                String username1;
                if (choice_access == 1) { //REGISTRAZIONE
                    System.out.print("Username:");
                    username1 = access.next();
                    for (int i = 0; i < u_list.size(); i++) {
                        if (u_list.containsKey(username1))
                            go2 = false;
                    }
                    if(go2 == false){
                        System.out.println("Errore, Username gia esistente! Scegliere un altro username");
                        System.out.print("Username:");
                        username1 = access.next();
                        go2 = true;
                    }
                    System.out.print("Name:");
                    String name1 = access.next();
                    System.out.print("Surname:");
                    String surnname1 = access.next();
                    System.out.print("Password:");
                    String password1 = access.next();
                    String pass = String.valueOf(password1.hashCode());
                    server.registerUser(username1, pass,name1,surnname1,list1);
                    System.out.println("Complimenti "+ username1 + " adesso puoi loggarti !");
                } else if (choice_access == 2) {//LOGIN
                    System.out.print("Username:");
                    String username2 = access.next();
                    if(u_list.containsKey(username2)){
                        System.out.print("Password:");
                        String password2 = access.next();
                        String pass2 = String.valueOf(password2.hashCode());
                        if(u_list.get(username2).getPass().equals(pass2)){
                            server.loginUser(username2, pass2);
                            System.out.println("Login effettuato con successo!");
                            menu(username2,u_list,server);
                        }else{
                            System.out.println("Login fallito! Password errata");
                        }

                    }else{
                        System.out.println("Username inserito inesistente !");

                    }


                } else {
                    System.out.println("Opzione inesistente!");
                }

            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }


    private static void menu(String username, HashMap<String,User> u_list, Services server) throws RemoteException{

        boolean go = true;
        Scanner user_input = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        while (go) {

            System.out.println("--------------------------------");
            System.out.println(" 0 - ADD");
            System.out.println(" 1 - REMOVE ");
            System.out.println(" 2 - LIST ");
            System.out.println(" 3 - SAVE ");
            System.out.println(" 4 - SORT ");
            System.out.println(" 5 - SAVE ON FILE ");
            System.out.println(" 6 - REGISTERED USERs ");
            System.out.println(" 7 - LOGOUT ");
            System.out.println("--------------------------------");
            System.out.print(" choice:");
            int choice = user_input.nextInt();
            switch (choice) {
                case 0://ADD
                    System.out.print("Opzioni di add: [1] add film , [2] add cortometraggio :");
                    int scelta1 = sc2.nextInt();
                    if (scelta1 == 1) {
                        System.out.print("Titolo:");
                        //String titolo = sc1.nextLine();
                        String titolo = sc1.next();
                        System.out.print("Anno:");
                        int anno = sc1.nextInt();
                        System.out.print("Durata(minuti):");
                        int durata = sc1.nextInt();
                        System.out.print("Visto(inserire true o false):");
                        boolean visto = sc1.nextBoolean();
                        if (visto == true) {
                            System.out.print("Voto(da 1 a 10):");
                            int voto = sc1.nextInt();
                            Film f1 = new Film(titolo, anno, durata, voto, visto);
                            server.addFilm(u_list.get(username).getUsername(), f1);
                        } else {
                            Film f2 = new Film(titolo, anno, durata, visto);
                            server.addFilm(u_list.get(username).getUsername(), f2);
                        }

                    } else if (scelta1 == 2) {
                        System.out.print("Titolo:");
                        //String titolo2 = sc1.nextLine();
                        String titolo2 = sc1.next();
                        System.out.print("Anno:");
                        int anno2 = sc1.nextInt();
                        System.out.print("Durata(minuti):");
                        int durata2 = sc1.nextInt();
                        System.out.print("Metraggio(metri):");
                        int metraggio = sc1.nextInt();
                        System.out.print("Visto(inserire true o false):");
                        boolean visto2 = sc1.nextBoolean();
                        if (visto2 == true) {
                            System.out.print("Voto(da 1 a 10):");
                            int voto2 = sc1.nextInt();
                            Cortometraggio c1 = new Cortometraggio(titolo2, anno2, durata2, voto2, visto2, metraggio);
                            server.addCorto(u_list.get(username).getUsername(), c1);
                        } else {
                            Cortometraggio c2 = new Cortometraggio(titolo2, anno2, durata2, visto2, metraggio);
                            server.addCorto(u_list.get(username).getUsername(), c2);
                        }


                    } else {
                        System.out.println("Inserimento non valido, riprovare!");
                    }
                    break;

                case 1://REMOVE
                    System.out.print("Opzioni di remove: [1] remove film , [2] remove cortometraggio :");
                    int scelta2 = sc2.nextInt();
                    if (scelta2 == 1) {
                        System.out.print("Opzioni di rimozione: [1] per eliminare 1 solo film , [2] per eliminare tutta la lista :");
                        int scelta3 = sc2.nextInt();
                        if (scelta3 == 1) {
                            System.out.print("Indice del film da rimuovere:");
                            int index = sc1.nextInt();
                            server.removeFilm(u_list.get(username).getUsername(), index);
                        } else if (scelta3 == 2) {
                            server.removeAll(u_list.get(username).getUsername());
                            System.out.println("The film list is now empty!");
                        } else {
                            System.out.println("Inserimento non valido, riprovare!");
                        }
                    } else if (scelta2 == 2) {
                        System.out.print("Opzioni di rimozione: [1] per eliminare 1 solo cortometraggio , [2] per eliminare tutta la lista :");
                        int scelta4 = sc2.nextInt();
                        if (scelta4 == 1) {
                            System.out.print("Indice del cortometraggio da rimuovere:");
                            int index = sc1.nextInt();
                            server.removeCorto(u_list.get(username).getUsername(), index);
                        } else if (scelta4 == 2) {
                            server.removeCortoAll(u_list.get(username).getUsername());
                            System.out.println("The corto list is now empty!");
                        } else {
                            System.out.println("Inserimento non valido, riprovare!");
                        }
                    } else {
                        System.out.println("Inserimento non valido, riprovare!");
                    }

                    break;

                case 2://LIST
                    System.out.print("Opzioni di list: [1] list film , [2] list cortometraggio :");
                    int scelta5 = sc2.nextInt();
                    if (scelta5 == 1) {
                        ArrayList<Film> f_list = server.getList(u_list.get(username).getUsername());
                        if (f_list.isEmpty()) {
                            System.out.println("Empty film list!");
                        } else {
                            System.out.println("Received film list->");
                            System.out.println(f_list);
                        }
                    } else if (scelta5 == 2) {
                        ArrayList<Cortometraggio> c_list = server.getCortoList(u_list.get(username).getUsername());
                        if (c_list.isEmpty()) {
                            System.out.println("Empty corto list!");
                        } else {
                            System.out.println("Received corto list->");
                            System.out.println(c_list);
                        }
                    } else {
                        System.out.println("Inserimento non valido, riprovare!");
                    }
                    break;
                case 3://SAVE
                    System.out.print("Opzioni di save: [1] save film , [2] save cortometraggio :");
                    int scelta6 = sc2.nextInt();
                    if (scelta6 == 1) {
                        ArrayList<Film> list = server.getList(u_list.get(username).getUsername());
                        server.saveList(u_list.get(username).getUsername(), list);
                        System.out.println("Film Save completed!");
                    } else if (scelta6 == 2) {
                        ArrayList<Cortometraggio> c1_list = server.getCortoList(u_list.get(username).getUsername());
                        server.saveCortoList(u_list.get(username).getUsername(), c1_list);
                        System.out.println("Corto Save completed!");
                    } else {
                        System.out.println("Inserimento non valido, riprovare!");
                    }
                    break;
                case 4://SORT
                    System.out.print("Opzioni di sort: [1] riordina i tuoi film , [2] riordina i tuoi cortometraggi :");
                    int scelta7 = sc2.nextInt();
                    if (scelta7 == 1) {
                        System.out.println("Scegli come riordinare la tua lista di film !");
                        System.out.print("Inserire [1] per titolo , [2] per anno , [3] per durata , [4] per voto:");
                        int scelta8 = sc2.nextInt();
                        ArrayList<Film> complete_list = server.getList(u_list.get(username).getUsername());
                        YearComparator fc = new YearComparator();
                        TimeComparator tc = new TimeComparator();
                        VotoComparator vc = new VotoComparator();
                        if (scelta8 == 1) {
                            Collections.sort(complete_list);
                            System.out.println(complete_list);
                            System.out.println("Sorted film list by title!");
                        } else if (scelta8 == 2) {
                            Collections.sort(complete_list, fc);
                            System.out.println(complete_list);
                            System.out.println("Sorted film list by year!");
                        } else if (scelta8 == 3) {
                            Collections.sort(complete_list, tc);
                            System.out.println(complete_list);
                            System.out.println("Sorted film list by time!");
                        } else if (scelta8 == 4) {
                            Collections.sort(complete_list, vc);
                            System.out.println(complete_list);
                            System.out.println("Sorted film list by voto!");
                        } else {
                            System.out.println("Inserimento non valido, riprovare!");
                        }
                    } else if (scelta7 == 2) {
                        ArrayList<Cortometraggio> complete_c_list = server.getCortoList(u_list.get(username).getUsername());
                        LengthComparator lc = new LengthComparator();
                        Collections.sort(complete_c_list, lc);
                        System.out.println(complete_c_list);
                        System.out.println("Sorted Corto list by length!");

                    } else {
                        System.out.println("Inserimento non valido, riprovare!");
                    }

                    break;
                case 5://SAVE ON FILE
                    System.out.print("Opzioni di save: [1] save film , [2] save cortometraggio :");
                    int scelta9 = sc2.nextInt();
                    if (scelta9 == 1) {
                        ArrayList<Film> list = server.getList(u_list.get(username).getUsername());
                        server.saveFilmFile(u_list.get(username).getUsername(), list);
                        System.out.println("Film Save on file completed!");
                    } else if (scelta9 == 2) {
                        ArrayList<Cortometraggio> c1_list = server.getCortoList(u_list.get(username).getUsername());
                        server.saveCortoFile(u_list.get(username).getUsername(), c1_list);
                        System.out.println("Corto Save on file completed!");
                    } else {
                        System.out.println("Inserimento non valido, riprovare!");
                    }
                    break;
                case 6://REGISTERED USERs
                    HashMap<String,User> tmp_list = server.getUser_list();
                    System.out.println(tmp_list.toString());
                    break;
                case 7://QUIT
                    server.logout(u_list.get(username).getUsername());
                    go = false;
                    System.out.println("Quitting user: " + u_list.get(username).getUsername());
                    break;


            }

        }
    }
}



