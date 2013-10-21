package com.android.gimnasio.maquinasPorDia;

import java.util.ArrayList;

import com.android.gimnasio.R;
import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.TipoEjercicioUsuario;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class MaquinasPorDiaActivity extends Activity {
	
    ListView listView;
    private Maquina maquina;
    private TipoEjercicioUsuario teu;
    public static ArrayList<Item> Items;
    private ArrayList<Integer> ids,ids_ejercicios;
	private String dia;
	private TextView titulo;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas_por_dia);
        titulo=(TextView)findViewById(R.id.textView1);
        
        maquina=new Maquina(this);
        teu=new TipoEjercicioUsuario(this);
        dia=getIntent().getCharSequenceExtra("dia").toString();
        titulo.setText("Rutina para el dia "+dia);
        titulo.setTextColor(Color.parseColor("#08088A"));      
        Toast.makeText(getApplicationContext(),"Dia seleccionado "+dia,Toast.LENGTH_SHORT).show();
        ids=maquina.getMaquinasSeleccionadasPorDia(dia);
        LoadModel(ids,maquina);
        listView = (ListView) findViewById(R.id.listView);
        String[] ids = new String[Items.size()];
        for (int i= 0; i < ids.length; i++){

            ids[i] = Integer.toString(i+1);
        }

        ItemAdapter adapter = new ItemAdapter(this,R.layout.activity_maquinas_por_dia_row, ids);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = listView.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            }


        });
                       
    }
    
   
    public static void LoadModel( ArrayList<Integer> ids ,Maquina maquina) {
    	Log.d("entro en ","load model");
        Items = new ArrayList<Item>();
        for(int i=0;i<ids.size();i++)
        {
            Items.add(new Item((i+1),maquina.getrutaImagen(ids.get(i)), maquina.getNombre(ids.get(i))));
	
        }

    }
    public static Item GetbyId(int id){

        for(Item item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }
    
}


