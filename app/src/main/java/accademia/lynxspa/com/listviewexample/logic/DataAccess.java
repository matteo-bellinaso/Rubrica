package accademia.lynxspa.com.listviewexample.logic;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import accademia.lynxspa.com.listviewexample.R;
import accademia.lynxspa.com.listviewexample.data.Contatto;
import accademia.lynxspa.com.listviewexample.data.Singleton;

/**
 * Created by matteobellinaso on 12/02/18.
 */

public class DataAccess {


    public static String file_pref = "filePreferito";
    public static String nome_pref ="nomePreferito";

    public  static void initData(Context context){

      Contatto contat1 =  new Contatto("matteo", "3456788");//basta add
        Contatto contat2 =  new Contatto("Marione", "345678768");//basta add

        dataAdd(context, contat1);
        dataAdd(context, contat2);

    }

    public static List<Contatto> getData(Context context){

        return Singleton.getInstance().getItemList();
        //metodo che ritrna il nostro array instanziato grazie alla classe singleton
    }


    public static List<Contatto> dataAdd( Context context, Contatto contact){
        //il dataAdd richiede sempre un contesto e giustamente l'oggetto da aggiungere.

        List<Contatto> data = DataAccess.getData(context);
        // richiamiamo la lista del singleton tramite getData, creando una nuova lista

        data.add(contact);
        //ci aggiungiamo l'oggetto passato a dataAdd nella versione data alla nuova lista.

        Singleton.getInstance().setItemList(data);
        //salviamo la nuova lista aggiornata sopra quella vecchia.

        return data;

    }


    public static Contatto getIndex(Context context, int position){ //funzione che passando un context e un indice.

        List<Contatto> data = DataAccess.getData(context); //prende la lista che abbiamo salvato.

        return data.get(position); //con .get(positino) mi cerca la posizione dell'oggetto selezionato e restituisce l'oggetto.



    }

        public static List<Contatto> dataRemove(Context context, int position){ //funziona come dataAdd soltanto che rimuove l'elemento

        List<Contatto> data = DataAccess.getData(context);
       data.remove(position);

       Singleton.getInstance().setItemList(data);

       return data;

        }

    public static int getColorForPosition(Context context, int position){

        if(position % 2 == 0){
            return context.getColor(R.color.light_blu);
        }else{
            return context.getColor(R.color.light_gray);
        }

    }

    public static void savePref(Context context, String name){
        //questa funzione ci permette di salvare come preferito un singolo oggetto della nostra lista.

        SharedPreferences Pref = context.getSharedPreferences(file_pref, Context.MODE_PRIVATE);
        // mi prende la stringa dichiarate in partenza,

        SharedPreferences.Editor editor = Pref.edit();
        // ci vuole sempre l'editor per potere cambiare valore a questa variabile.

        editor.putString(nome_pref, name);
        //gli diamo la chiave, e il valore da assegnarci

        editor.commit();
        //con commit mandiamo il valore.
    }

    public static String getPref(Context context){

        SharedPreferences Pref = context.getSharedPreferences(file_pref, Context.MODE_PRIVATE);
        //questo metodo ritorna, la stringa che abbiamo settato come preferito, va a prendere il file, nel nostro caso una semplice variabile.

        String preferito = Pref.getString(nome_pref, null);
        //preleva il nome di quello che abbiamo settato come preferito, null è di default

        return preferito;
        //e lo ritorna.

    }
}
