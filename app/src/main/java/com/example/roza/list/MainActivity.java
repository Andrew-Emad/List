package com.example.roza.list;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener {

    RecyclerView recyclerView;
    SelectableAdapter adapter;
    ListView listView;
    List<Item> selectedItems;
    ArrayList<String> select;
    HashMap<String,ArrayList<String>> Result;
    HashMap<String,String> ResultString;
    String disease;
    List<Item> hyperdata;
    List<Item> diabetesdata;
    List<Item> Gender;
    List<Item> PsychiatricDisorders;
    List<Item> Smoking;
    List<Item> Alcohol;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        String [] list={"Hypertension","Diabetes","Gender","Psychiatric Disorders","Smoking","Alcohol"};
        listView =findViewById(R.id.list1);


        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        loadrecycler(hyperdata,false);

                        disease="hyper";

                        listView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        loadrecycler(diabetesdata,true);

                        disease="diabetes";
                        recyclerView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        break;
                    case 2:
                        loadrecycler(Gender,false);

                        disease="Gender";
                        recyclerView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        break;
                    case 3:
                        loadrecycler(PsychiatricDisorders,true);

                        disease="Psychiatric Disorders";
                        recyclerView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        break;
                    case 4:
                        loadrecycler(Smoking,false);

                        disease="Smoking";
                        recyclerView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        break;
                    case 5:
                        loadrecycler(Alcohol,false);

                        disease="Alcohol";
                        recyclerView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        break;
                        default:
                            break;
                }
            }
        });



    }


    public void loadrecycler(List<Item> itemList,boolean multi)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) this.findViewById(R.id.selection_list);
        recyclerView.setLayoutManager(layoutManager);
     //   List<Item> selectableItems = generateItems();
        adapter = new SelectableAdapter(this,itemList,multi);

        recyclerView.setAdapter(adapter);
    }
    public void initialize()
    {
        Result=new HashMap<String,ArrayList<String>>();
        Result.put("hyper",new ArrayList<String>());
        Result.put("diabetes",new ArrayList<String>());
        Result.put("Gender",new ArrayList<String>());
        Result.put("Psychiatric Disorders",new ArrayList<String>());
        Result.put("Smoking",new ArrayList<String>());
        Result.put("Alcohol",new ArrayList<String>());

        ResultString=new HashMap<String,String>();
        ResultString.put("hyper",new String());
        ResultString.put("diabetes",new String());
        ResultString.put("Gender",new String());
        ResultString.put("Psychiatric Disorders",new String());
        ResultString.put("Smoking",new String());
        ResultString.put("Alcohol",new String());

        hyperdata = new ArrayList<>();
        hyperdata.add(new Item("Low-hypertensive (100-119/70-79 mmHg)"));
        hyperdata.add(new Item("Pre-hypertensive (120-139/80-89 mmHg)"));
        hyperdata.add(new Item("Stage 1 hypertensive (140-159/90-99 mmHg)"));
        hyperdata.add(new Item("Stage 2 hypertensive (=>160/=>100 mmHg)"));



        diabetesdata = new ArrayList<>();
        diabetesdata.add(new Item("Pre-Diabetic"));
        diabetesdata.add(new Item("Controlled Diabetic"));
        diabetesdata.add(new Item("Uncontrolled Diabetic"));


        Gender = new ArrayList<>();
        Gender.add(new Item("Male"));
        Gender.add(new Item("Female"));


        PsychiatricDisorders = new ArrayList<>();
        PsychiatricDisorders.add(new Item("Depression"));
        PsychiatricDisorders.add(new Item("Personality Disorder"));
        PsychiatricDisorders.add(new Item("Anxiety Disorder"));
        PsychiatricDisorders.add(new Item("Schizophrenia"));
        PsychiatricDisorders.add(new Item("Eating disorders"));
        PsychiatricDisorders.add(new Item("Addictive behaviors"));


        Smoking = new ArrayList<>();
        Smoking.add(new Item("Past Smoker (At least one year)"));
        Smoking.add(new Item("Past Heavy Smoker (At least one year)"));
        Smoking.add(new Item("Current Smoker (Within the current year)"));
        Smoking.add(new Item("Current Heavy Smoker (Within the current year)"));
        Smoking.add(new Item("Never Smoker "));
        Smoking.add(new Item("Non-daily(Occasional) Smoker"));


        Alcohol= new ArrayList<>();
        Alcohol.add(new Item("Very Heavy"));
        Alcohol.add(new Item("Heavy"));
        Alcohol.add(new Item("Moderate"));
        Alcohol.add(new Item("Light "));


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onItemSelected(SelectableItem selectableItem) {

        selectedItems = adapter.getSelectedItems();

        select=adapter.getSelectedItemsnames();
        if (disease=="hyper")
        {

           ArrayList<String> old= Result.put("hyper",select);
           String olds= ResultString.put("hyper",selectableItem.getName());

        }
        if (disease=="diabetes")
        {
            ArrayList<String> old= Result.put("diabetes",select);
            String olds= ResultString.put("diabetes",selectableItem.getName());

        }
        if (disease=="Gender")
        {

            ArrayList<String> old= Result.put("Gender",select);
            String olds= ResultString.put("Gender",selectableItem.getName());


        } if (disease=="Psychiatric Disorders")
        {

            ArrayList<String> old= Result.get("Psychiatric Disorders");
            old.add(selectableItem.getName());
            Result.put("Psychiatric Disorders",old);

            String olds= ResultString.get("Psychiatric Disorders");
            olds+=selectableItem.getName()+',';
            ResultString.put("Psychiatric Disorders",olds);


        } if (disease=="Smoking")
        {

            ArrayList<String> old= Result.put("Smoking",select);
            String olds= ResultString.put("Smoking",selectableItem.getName());
        } if (disease=="Alcohol")
        {

            ArrayList<String> old= Result.put("Alcohol",select);
            String olds= ResultString.put("Alcohol",selectableItem.getName());
        }



          //  Toast.makeText(this, "Selected item is "+selectableItem.getName()+

        //  ", Totally  selectem item count is "+selectedItems.size(), Toast.LENGTH_SHORT).show();

            Toast.makeText(this, selectableItem.getName(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.mybutton)
        {

            Toast.makeText(this, "Hyper is"+Result.get("hyper").toString()+"Diabetes is "+ResultString.get("diabetes").toString(), Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {


        recyclerView.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);

    }
}