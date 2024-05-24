package thanh.edu.appdocsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thanh.edu.appdocsach.R;
import thanh.edu.appdocsach.model.Truyen;

public class adapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listtruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listtruyen) {
        this.context = context;
        this.listtruyen = listtruyen;
    }

    @Override
    public int getCount() {
        return listtruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listtruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtTenTruyen;
        ImageView imageTruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen,null);
        viewHolder.txtTenTruyen = convertView.findViewById(R.id.textViewTenTruyenNew);
        viewHolder.imageTruyen = convertView.findViewById(R.id.imgNewTruyen);
        convertView.setTag(viewHolder);
        Truyen truyen = (Truyen) getItem(position);
        viewHolder.txtTenTruyen.setText(truyen.getTenTruyen());
        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.baseline_cloud_download_24).error(R.drawable.baseline_image_24).into(viewHolder.imageTruyen);
        return convertView;
    }
}
