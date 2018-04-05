package accademia.lynxspa.com.listviewexample.logic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import accademia.lynxspa.com.listviewexample.R;
import accademia.lynxspa.com.listviewexample.UI.CustomArrayAdapter;

public class MainActivity extends AppCompatActivity {

    //static final String[] FRUITS = new String[]{"Apple", "Avocado", "Banana",
    //       "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
    //        "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple"};

    public final static String EXTRA_SELECTED_ITEM = "accademia.lynxspa.com.SELECTED_ITEM";

    CustomArrayAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static int visual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataAccess.initData(getApplicationContext());   //inizializzo l'array l'inizializzazione mi chiede sempre un context

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomArrayAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);// chiede sempre due parametri, uno XMl e l'altro.

        return true;//mettendo true anzichè lasciare super oncreate mi prende questo menu anziche quello del padre

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add: {

                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                //al click del bottone add nel menù ci manda ad un activity per agiungere un oggetto.

                startActivity(intent);

                return true;
            }
            case R.id.change:{

                if(visual == 0){
                    visual++;
                    mLayoutManager = new GridLayoutManager(this, 2);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                }else{
                    visual--;
                    mLayoutManager = new LinearLayoutManager(this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                }
            }

            default: {
                return super.onOptionsItemSelected(item);  }
        }  }


    @Override
    protected void onResume(){  //al resume dell'activity mi controlla se ci sono state modifiche nell'array e le applica alla activity
        super.onResume();
    }

}
