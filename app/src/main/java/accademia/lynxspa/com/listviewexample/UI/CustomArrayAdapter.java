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

import java.util.List;

import accademia.lynxspa.com.listviewexample.R;
import accademia.lynxspa.com.listviewexample.data.Contatto;
import accademia.lynxspa.com.listviewexample.data.Singleton;
import accademia.lynxspa.com.listviewexample.logic.DataAccess;
import accademia.lynxspa.com.listviewexample.logic.DetailActivity;
import accademia.lynxspa.com.listviewexample.logic.MainActivity;

public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.ViewHolder>{

    private Context contesto;
    private LayoutInflater layoutInflatera;
    private List<Contatto> contactLis;

    public final static String EXTRA_SELECTED_ITEM = "accademia.lynxspa.com.SELECTED_ITEM";

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView nomeContact;
        private ImageView imgContact;
        private TextView numberContact;
        private View root;
        private ImageView star;

        public ViewHolder(View v){
            super(v);
            nomeContact = v.findViewById(R.id.nome_contact_main);
            numberContact = v.findViewById(R.id.telefono_contact_main);
            imgContact = v.findViewById(R.id.img_utente);
            root = v;
            star = v.findViewById(R.id.star);


        }


        public void setOnItemClickCustom(Context context, final int position){

            context = root.getContext();
            final Context finalContext = context;
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(finalContext, DetailActivity.class);
                    intent.putExtra(EXTRA_SELECTED_ITEM, position);
                    finalContext.startActivity(intent);
                }
            });
        }
    }



    public CustomArrayAdapter(Context context) {
        contesto = context;
        contactLis = Singleton.getInstance().getItemList();
       layoutInflatera = LayoutInflater.from(context);
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflatera.inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nome = contactLis.get(position).getNome();
        String number = contactLis.get(position).getTelefono();

        holder.nomeContact.setText(nome);
        holder.numberContact.setText(number);

        holder.imgContact.setBackgroundColor(DataAccess.getColorForPosition(contesto,position));
        String preference = DataAccess.getPref(contesto);

        if(preference != null && preference.equals(number)){
            holder.star.setVisibility(View.VISIBLE);
        }else{
            holder.star.setVisibility(View.INVISIBLE);
        }

        holder.setOnItemClickCustom(contesto, position);
    }

    @Override
    public int getItemCount() {
        return contactLis.size();
    }
    

}
