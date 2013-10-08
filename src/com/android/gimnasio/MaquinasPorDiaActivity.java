package com.android.gimnasio;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MaquinasPorDiaActivity extends Activity {
	private LinearLayout layout;
	
	private ImageView img;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maquinas_por_dia);
		layout=(LinearLayout)findViewById(R.id.layout);
		int r=(int) (Math.random()*6 + 1);
		InputStream is = null;
		ArrayList<String> maquinas=new ArrayList<String>();
		maquinas.add("banco_plano");
		maquinas.add("barra");
		maquinas.add("mancuernas");
		maquinas.add("press_banca");

		for (int i=1;i<maquinas.size();i++) {
			img=new ImageView(this);
			img.setPadding(1,10,1,10);
			try {
				is = this.getResources().getAssets().open("maquinas/"+maquinas.get(i)+".jpg");
			} catch (IOException e) {
				;
			}
			Bitmap image = BitmapFactory.decodeStream(is);

			img.setImageBitmap(image);
			layout.addView(img);
			Button e=new Button(this);
			e.setText("ingresar a los ejercicios de la maquina"+i);
			
			layout.addView(e);
		
		}
		
	}


	
	

}
