package com.android.gimnasio;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.View.OnCreateContextMenuListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class EjercicioRealizadoActivity extends Activity{
	
	private LinearLayout linear,linear2;
	private TextView text;
	
	protected void OnCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ejercicio_realizado);
		linear=(LinearLayout)findViewById(R.id.linear);
		Log.d("Entre a la acttividad","");	
		/*linear2=new LinearLayout(this);
		linear2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
		for(int i=0;i<10;i++)
		{
			linear2.setOrientation(LinearLayout.HORIZONTAL);
			for(int j=0;j<5;j++)
			{
				Log.d("Entre a la acttividad","");
				text=new TextView(this);
				text.setText("pato y ottu "+i+j);
				linear2.addView(text);
					
			}
			linear.addView(linear2);
		}
		*/
		Log.d("Entra acttividad","");	

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}
}
