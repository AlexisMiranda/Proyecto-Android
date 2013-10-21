package com.android.gimnasio.maquinasPorDia;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.gimnasio.*;
import java.io.IOException;
import java.io.InputStream;

import com.android.gimnasio.R;

public class ItemAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] Ids;
    private final int rowResourceId;

    public ItemAdapter(Context context, int textViewResourceId, String[] objects) {

        super(context, textViewResourceId, objects);

        this.context = context;
        this.Ids = objects;
        this.rowResourceId = textViewResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	Log.d("entro en ","getview");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(rowResourceId, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);

        
        int id = Integer.parseInt(Ids[position]);
        String imageFile = MaquinasPorDiaActivity.GetbyId(id).IconFile;

        textView.setText(MaquinasPorDiaActivity.GetbyId(id).Name);
        // get input stream
        InputStream is = null;
		try {
			is = context.getResources().getAssets().open(imageFile);
			//is = c.getResources().getAssets().open(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
        imageView.setImageBitmap(BitmapFactory.decodeStream(is));
        return rowView;

    }
    

}
