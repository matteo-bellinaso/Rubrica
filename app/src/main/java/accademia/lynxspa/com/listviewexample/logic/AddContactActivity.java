package accademia.lynxspa.com.listviewexample.logic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import accademia.lynxspa.com.listviewexample.R;
import accademia.lynxspa.com.listviewexample.data.Contatto;

public class AddContactActivity extends Activity {

    EditText insertName;
    EditText insertTel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        final int  selectedItem = intent.getIntExtra(MainActivity.EXTRA_SELECTED_ITEM, 0);

        // Set TextView
        insertName = (EditText) findViewById(R.id.contact_nome_create);
        insertTel = (EditText) findViewById(R.id.contact_tel_create);

        ImageView image = (ImageView) findViewById(R.id.img_contact_add);

        List<Contatto> list = DataAccess.getData(this);


        for( int i = 0; i <= list.size() ; i++){

            image.setBackgroundColor(DataAccess.getColorForPosition(this, i));
            //questa funzione mi controlla di quanti elementi è formato il nostro array. e determina il colore che andrà messo.
        }


        // Set onclick listener
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveContact();
                finish();
            }
        });
    }


    private void SaveContact(){// questo metodo non torna niente perchè ricarichiamo l'array lista al resume della MainActivity

        Contatto newContact = new Contatto(insertName.getText().toString(), insertTel.getText().toString() );
        //il nuovo contatto è formato dai campi di inserimento.

        DataAccess.dataAdd(getApplicationContext(), newContact);

    }
}
