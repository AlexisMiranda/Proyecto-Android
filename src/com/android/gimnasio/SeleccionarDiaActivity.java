package com.android.gimnasio;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SeleccionarDiaActivity extends ListActivity {

	private String[] dias={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	private ListView lview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(CONTEXT_INCLUDE_CODE);
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dias));

		//setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_seleccionar_dia, android.R.id.text1, dias));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
	
	protected void onListItemClick(ListView list,View view,int position,long id){
		super.onListItemClick(list,view,position,id);
		String nombre_lista=dias[position];
		try{
			 Intent i = new Intent(this, MaquinasPorDiaActivity.class );
			 i.putExtra("dia", nombre_lista);
		     startActivity(i);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public LinearLayout crearlayout()
	{
		LinearLayout linear_vertical=new LinearLayout(this);
		linear_vertical.setOrientation(LinearLayout.VERTICAL);
		linear_vertical.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linear_vertical.addView(crearEditText(1,"",100,100,10,10,0,0));
		return linear_vertical;
	}

	public EditText crearEditText(int id,String texto,int w,int h,int left,int top,int right,int bottom)
	{
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, w, getResources().getDisplayMetrics());
		int heigth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, h, getResources().getDisplayMetrics());
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, heigth);
		lp.setMargins(left, top, right, bottom);
		EditText edit=new EditText(this);
		edit.setLayoutParams(lp);
		edit.setInputType(InputType.TYPE_CLASS_NUMBER);
		edit.setTag(texto+id);
		edit.setFilters(new InputFilter[] {new InputFilter.LengthFilter(5)});
		return edit;
	}
    
}
