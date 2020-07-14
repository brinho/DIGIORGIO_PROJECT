import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
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
                System.out.println(" 6 - QUIT ");
                System.out.println("--------------------------------");
                System.out.print(" choice:");
                int choice = user_input.nextInt();
                switch (choice) {
                    case 0://ADD
                        System.out.print("Opzioni di add: [1] add film , [2] add cortometraggio :");
                        int scelta1 = sc2.nextInt();
                        if( scelta1 == 1 ) {
                            System.out.print("Titolo:");
                            //String titolo = sc1.nextLine();
                            String titolo = sc1.next();
                            System.out.print("Anno:");
                            int anno = sc1.nextInt();
                            System.out.print("Durata(minuti):");
                            int durata = sc1.nextInt();
                            System.out.print("Visto(inserire true o false):");
                            boolean visto = sc1.nextBoolean();
                            if( visto == true){
                                System.out.print("Voto(da 1 a 10):");
                                int voto = sc1.nextInt();
                                Film f1 = new Film(titolo, anno, durata,voto,visto);
                                server.addFilm(f1);
                            }
                            else{
                                Film f2 = new Film(titolo, anno, durata,visto);
                                server.addFilm(f2);
                            }

                        } else if( scelta1 == 2 ) {
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
                            if( visto2 == true){
                                System.out.print("Voto(da 1 a 10):");
                                int voto2 = sc1.nextInt();
                                Cortometraggio c1 = new Cortometraggio(titolo2,anno2,durata2,voto2,visto2,metraggio);
                                server.addCorto(c1);
                            }
                            else{
                                Cortometraggio c2 = new Cortometraggio(titolo2,anno2,durata2,visto2,metraggio);
                                server.addCorto(c2);
                            }



                        }else{
                            System.out.println("Inserimento non valido, riprovare!");
                        }
                        break;

                    case 1://REMOVE
                        System.out.print("Opzioni di remove: [1] remove film , [2] remove cortometraggio :");
                        int scelta2 = sc2.nextInt();
                        if(scelta2 == 1) {
                            System.out.print("Opzioni di rimozione: [1] per eliminare 1 solo film , [2] per eliminare tutta la lista :");
                            int scelta3 = sc2.nextInt();
                            if (scelta3 == 1) {
                                System.out.print("Indice del film da rimuovere:");
                                int index = sc1.nextInt();
                                server.removeFilm(index);
                            } else if (scelta3 == 2) {
                                server.removeAll();
                                System.out.println("The film list is now empty!");
                            }else{
                                System.out.println("Inserimento non valido, riprovare!");
                            }
                        }else if(scelta2 == 2){
                            System.out.print("Opzioni di rimozione: [1] per eliminare 1 solo cortometraggio , [2] per eliminare tutta la lista :");
                            int scelta4 = sc2.nextInt();
                            if (scelta4 == 1) {
                                System.out.print("Indice del cortometraggio da rimuovere:");
                                int index = sc1.nextInt();
                                server.removeCorto(index);
                            } else if (scelta4 == 2) {
                                server.removeCortoAll();
                                System.out.println("The corto list is now empty!");
                            }else{
                                System.out.println("Inserimento non valido, riprovare!");
                            }
                        }else{
                            System.out.println("Inserimento non valido, riprovare!");
                        }

                        break;

                    case 2://LIST
                        System.out.print("Opzioni di list: [1] list film , [2] list cortometraggio :");
                        int scelta5 = sc2.nextInt();
                        if( scelta5 == 1 ){
                            ArrayList<Film> f_list = server.getList();
                        if(f_list.isEmpty()){
                            System.out.println("Empty film list!");
                        }else {
                            System.out.println("Received film list->");
                            System.out.println(f_list);
                        }
                        }else if ( scelta5 == 2 ){
                            ArrayList<Cortometraggio> c_list = server.getCortoList();
                            if(c_list.isEmpty()){
                                System.out.println("Empty corto list!");
                            }else {
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
                        if(scelta6 == 1){
                            ArrayList<Film> list = server.getList();
                            server.saveList(list);
                            System.out.println("Film Save completed!");
                        }else if(scelta6 == 2) {
                            ArrayList<Cortometraggio> c1_list = server.getCortoList();
                            server.saveCortoList(c1_list);
                            System.out.println("Corto Save completed!");
                        }else{
                            System.out.println("Inserimento non valido, riprovare!");
                        }
                        break;
                    case 4://SORT
                        System.out.print("Opzioni di sort: [1] riordina i tuoi film , [2] riordina i tuoi cortometraggi :");
                        int scelta7 = sc2.nextInt();
                        if( scelta7== 1){
                            System.out.println("Scegli come riordinare la tua lista di film !");
                            System.out.print("Inserire [1] per titolo , [2] per anno , [3] per durata , [4] per voto:");
                            int scelta8 = sc2.nextInt();
                            ArrayList<Film> complete_list = server.getList();
                            YearComparator fc = new YearComparator();
                            TimeComparator tc = new TimeComparator();
                            VotoComparator vc = new VotoComparator();
                            if (scelta8 == 1 ){
                                Collections.sort(complete_list);
                                System.out.println(complete_list);
                                System.out.println("Sorted film list by title!");
                            } else if (scelta8 == 2 ){
                                Collections.sort(complete_list,fc);
                                System.out.println(complete_list);
                                System.out.println("Sorted film list by year!");
                            } else if(scelta8 == 3 ){
                                Collections.sort(complete_list,tc);
                                System.out.println(complete_list);
                                System.out.println("Sorted film list by time!");
                            } else if(scelta8 == 4 ){
                            Collections.sort(complete_list,vc);
                            System.out.println(complete_list);
                            System.out.println("Sorted film list by voto!");
                            } else{
                                System.out.println("Inserimento non valido, riprovare!");
                            }
                        }else if(scelta7== 2){
                            ArrayList<Cortometraggio> complete_c_list = server.getCortoList();
                            LengthComparator lc = new LengthComparator();
                            Collections.sort(complete_c_list,lc);
                            System.out.println(complete_c_list);
                            System.out.println("Sorted Corto list by length!");

                        }else{
                            System.out.println("Inserimento non valido, riprovare!");
                        }

                        break;
                    case 5://SAVE ON FILE
                        System.out.print("Opzioni di save: [1] save film , [2] save cortometraggio :");
                        int scelta9 = sc2.nextInt();
                        if(scelta9 == 1){
                            ArrayList<Film> list = server.getList();
                            server.saveFilmFile(list);
                            System.out.println("Film Save on file completed!");
                        }else if(scelta9 == 2) {
                            ArrayList<Cortometraggio> c1_list = server.getCortoList();
                            server.saveCortoFile(c1_list);
                            System.out.println("Corto Save on file completed!");
                        }else{
                            System.out.println("Inserimento non valido, riprovare!");
                        }
                        break;
                    case 6://QUIT
                        go = false;
                        System.out.println("Quitting client: "+ Thread.currentThread().getName());
                        break;


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
}

