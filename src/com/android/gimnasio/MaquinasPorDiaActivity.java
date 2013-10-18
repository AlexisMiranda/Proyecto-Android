package com.android.gimnasio;

import com.android.gimnasio.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
public class MaquinasPorDiaActivity extends ListActivity {
	
	String[] s={"aaa","bbbb","cccc","ddd","eee","fff","ggg","hhh","iii","jjj","frtgt","aaa","frfrfr","ncjenue"};
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas_por_dia);   
        ArrayAdapter<String> a= new ArrayAdapter<String>(this,R.layout.activity_maquinas_por_dia_row,R.id.titulo,s);
       
     setListAdapter(a);
                       
    }
}


