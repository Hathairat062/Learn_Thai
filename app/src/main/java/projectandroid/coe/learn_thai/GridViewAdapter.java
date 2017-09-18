package projectandroid.coe.learn_thai;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bow on 9/9/2560.
 */

public class GridViewAdapter extends ArrayAdapter<Tab1Learning> {
    public GridViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Tab1Learning> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(null == v){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.tab1grid,null);
        }
        Tab1Learning tab1Learning = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtView = (TextView) v.findViewById(R.id.txtTitle);

        img.setImageResource(tab1Learning.getImageId());
        txtView.setText(tab1Learning.getTitle());

        return v;
    }
}
