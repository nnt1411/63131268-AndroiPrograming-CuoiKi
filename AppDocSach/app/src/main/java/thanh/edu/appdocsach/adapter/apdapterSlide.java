package thanh.edu.appdocsach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import thanh.edu.appdocsach.R;
import thanh.edu.appdocsach.model.Truyen;

public class apdapterSlide extends RecyclerView.Adapter<apdapterSlide.TruyenViewHolder> {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public apdapterSlide(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @NonNull
    @Override
    public TruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TruyenViewHolder(LayoutInflater.from(context).inflate(R.layout.item_viewpager2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenViewHolder holder, int position) {
        Truyen truyen = listTruyen.get(position);
        holder.txtTenTruyen.setText(truyen.getTenTruyen());
        Picasso.get()
                .load(truyen.getAnh())
                .placeholder(R.drawable.baseline_cloud_download_24)
                .error(R.drawable.baseline_image_24)
                .into(holder.imageTruyen);
    }

    @Override
    public int getItemCount() {
        return listTruyen.size();
    }

    static class TruyenViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTruyen;
        ImageView imageTruyen;

        TruyenViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTruyen = itemView.findViewById(R.id.textViewTenTruyen);
            imageTruyen = itemView.findViewById(R.id.imageViewSlide);
        }
    }
}

