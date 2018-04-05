package accademia.lynxspa.com.listviewexample.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import accademia.lynxspa.com.listviewexample.R;
import accademia.lynxspa.com.listviewexample.data.Contatto;
import accademia.lynxspa.com.listviewexample.data.Singleton;
import accademia.lynxspa.com.listviewexample.logic.DataAccess;
import accademia.lynxspa.com.listviewexample.logic.DetailActivity;
import accademia.lynxspa.com.listviewexample.logic.MainActivity;

public class CustomArrayAdapter extends ArrayAdapter<Contatto>{

    private Context contesto;
    private LayoutInflater layoutInflatera;
    private List<Contatto> contactLis;

    public final static String EXTRA_SELECTED_ITEM = "accademia.lynxspa.com.SELECTED_ITEM";


    public CustomArrayAdapter(Context context) {
        super(context, R.layout.item_layout, Singleton.getInstance().getItemList());
        this.contactLis = Singleton.getInstance().getItemList();
        this.contesto = context;
    }

    private ImageView img_utente;
    private TextView nome_contact_main;
    private TextView telefono_contact_main;
    private ImageView image_star;


    public View getView(int position, View convertVire, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_layout, parent, false);


        img_utente = rowView.findViewById(R.id.img_utente);
        img_utente.setBackgroundColor(DataAccess.getColorForPosition( contesto , position));

        nome_contact_main = rowView.findViewById(R.id.nome_contact_main);
        nome_contact_main.setText(contactLis.get(position).getNome());

        telefono_contact_main = rowView.findViewById(R.id.telefono_contact_main);
        telefono_contact_main.setText(contactLis.get(position).getTelefono());



        image_star = (ImageView) rowView.findViewById(R.id.star);
        image_star.setImageDrawable();

        return rowView;

    }




    @Override
    public int getItemCount() {
        return contactLis.size();
    }


}
