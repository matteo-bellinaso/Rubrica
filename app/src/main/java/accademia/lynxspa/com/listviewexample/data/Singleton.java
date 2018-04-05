package accademia.lynxspa.com.listviewexample.data;

import java.util.ArrayList;
import java.util.List;

import accademia.lynxspa.com.listviewexample.R;

/**
 * Created by matteobellinaso on 12/02/18.
 */

public class Singleton {


        private static Singleton ourInstance = new Singleton();

        public static Singleton getInstance() {
            return ourInstance;
        }


        private List<Contatto> list;

        private Singleton() {
            this.list = new ArrayList<Contatto>();
        }


       public void  setItemList(List<Contatto> list){// devo dichiarare cosa gli passo
            this.list = list;
       }


        public  List<Contatto> getItemList() {

        return list;
        }



}
