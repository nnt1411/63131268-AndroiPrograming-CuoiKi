package thanh.edu.appdocsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import thanh.edu.appdocsach.R;
import thanh.edu.appdocsach.model.ChuyenMuc;
import thanh.edu.appdocsach.model.Truyen;

public class adapterchuyenmuc extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ChuyenMuc> chuyenMucList;

    public adapterchuyenmuc(Context context, int layout, List<ChuyenMuc> chuyenMucList) {
        this.context = context;
        this.layout = layout;
        this.chuyenMucList = chuyenMucList;
    }

    @Override
    public int getCount() {
        return chuyenMucList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgChuyenMuc);
        TextView txt = (TextView) convertView.findViewById(R.id.textViewTenChuyenMuc);
        ChuyenMuc cm = chuyenMucList.get(position);

        txt.setText(cm.getTenchuyenmuc());
        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.baseline_cloud_download_24).error(R.drawable.baseline_image_24).into(img);

        return convertView;
    }
}
