package thanh.edu.appdocsach;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thanh.edu.appdocsach.R.id;

public class MHChinh extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listViewSach,listViewThongTin,listViewManHinhChinh;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mhchinh);

        AnhXa();
        ActionBar();
        ActionViewFlipper();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    //phương thức của thanh actionbar và toolbar
    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size); //tạo icon cho toolbar
        //sự kiện khi click toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    //phương thức viewflipper
    private void ActionViewFlipper() {
        ArrayList<String> mangQuangCao = new ArrayList<>();
        //add ảnh vào bảng sử dụng thư viện picasso để link ảnh
        mangQuangCao.add("https://sachhay24h.com/uploads/images/sach-gia-tri-cua-su-tu-te-1.jpeg");
        mangQuangCao.add("https://sachhay24h.com/uploads/images/canh-dong-bat-tan-nguyen-ngoc-tu-1.jpg");
        mangQuangCao.add("https://sachhay24h.com/uploads/images/truyen-co-tich-the-tuc-su-tich-trau-cau.jpg");
        mangQuangCao.add("https://sachhay24h.com/uploads/images/truyen-co-tich-viet-nam-qua-bau-tien-1.jpg");
        //thực hiện vòng lặp gán ảnh lên imageview, từ view lên app
        for (int i=0;i<mangQuangCao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //cho viewflip chạy tự động
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        //gọi animation tạo ở file anim vào flipper
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarManHinhChinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        listViewSach = findViewById(R.id.listViewSach);
        listViewManHinhChinh = findViewById(R.id.listViewManHinhChinh);
        listViewThongTin = findViewById(R.id.listViewThongTin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);
    }

    //nạp menu tìm kiếm vào action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                //chuyển sang màn hình tìm kiếm nếu bấm vào icon tìm kiếm
                Intent intentTimKiem= new Intent(MHChinh.this,ManTimKiem.class);
                startActivity(intentTimKiem);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}