package com.android.gimnasio.maquinasPorDia;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import com.android.gimnasio.EjercicioRealizadoActivity;
import com.android.gimnasio.LoginActivity;
import com.android.gimnasio.R;
import com.android.gimnasio.api.Maquina;
import com.android.gimnasio.api.TipoEjercicioUsuario;
import com.android.gimnasio.api.Usuario;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
	private LinearLayout linear;
	private int id_usuario_loggeado;
	private Usuario usuario;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas_por_dia);
        super.setTheme( android.R.style.Theme );
        titulo=(TextView)findViewById(R.id.textView1);
        linear=(LinearLayout)findViewById(R.id.linear_layout_act);
        linear.addView(desloggear(),0);
        maquina=new Maquina(this);
        teu=new TipoEjercicioUsuario(this);
        usuario=new Usuario(this);
        id_usuario_loggeado=usuario.getIdUsuarioLoggeado();
        dia=getIntent().getCharSequenceExtra("dia").toString();
        titulo.setText("\tRutina para el dia "+dia);
        titulo.setTextColor(Color.parseColor("#08088A"));      
        Toast.makeText(getApplicationContext(),"Dia seleccionado "+dia,Toast.LENGTH_SHORT).show();
        ids=maquina.getMaquinasSeleccionadasPorDia(id_usuario_loggeado,dia.substring(0,3));
       
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
                String str=(String)o;
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(), EjercicioRealizadoActivity.class);
                i.putExtra("dia", dia);
                i.putExtra("num_maquina_selec",""+MaquinasPorDiaActivity.this.ids.get(Integer.parseInt(str)-1));
                startActivity(i);
            }


        });
                       
    }
    
   
    public static void LoadModel( ArrayList<Integer> ids ,Maquina maquina) {
    	Log.d("entro en ","load model");

    	Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();
		linkedHashSet.addAll(ids);
		ids.clear();
		ids.addAll(linkedHashSet);
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
	public TextView desloggear()
	{
		TextView text=new TextView(this);
		Usuario u=new Usuario(this);
		int id_usuario_loggeado=u.getIdUsuarioLoggeado();
		String nombre=u.getNombre(id_usuario_loggeado);
		text.setText(nombre.substring(0,1).toUpperCase()+nombre.substring(1)+"\tX Cerrar Sesion");
		LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(100, 5, 0, 5);
		text.setLayoutParams(lp);
		text.setTextSize(18);
		text.setTextColor(Color.parseColor("#DF0101"));
		text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//lo envio a login activity
				Toast.makeText(getApplicationContext(),"Usuario desloggeado",Toast.LENGTH_SHORT).show();
				Intent i=new Intent(getApplicationContext(), LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		return text;
		
	}
    
}


