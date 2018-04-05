package accademia.lynxspa.com.listviewexample.logic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import accademia.lynxspa.com.listviewexample.R;

/**
 * Created by matteobellinaso on 19/02/18.
 */

public class DetailActivity extends Activity {

    private int currentItem;
    private String preferito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final int selectedItem = intent.getIntExtra(MainActivity.EXTRA_SELECTED_ITEM, 0); //prendo l'int per la posizione che passo usando l'Intent.

        currentItem = selectedItem;

        TextView resultName = (TextView) findViewById(R.id.detail_text_name);
        TextView resultTel = (TextView) findViewById(R.id.detail_text_tel);

        resultName.setText(DataAccess.getIndex(this, currentItem).getNome());
        resultTel.setText(DataAccess.getIndex(this, currentItem).getTelefono());

        ImageView imageView = (ImageView) findViewById(R.id.detail_img_view);
        imageView.setBackgroundColor(DataAccess.getColorForPosition(this, currentItem));

        preferito = DataAccess.getIndex(this, currentItem).getTelefono();

        // Set onclick listener
        Button button = (Button) findViewById(R.id.Back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button prefButton = (Button) findViewById(R.id.Preferito);
        prefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataAccess.savePref(DetailActivity.this , preferito);
                finish();
            }
        });
    }

}
