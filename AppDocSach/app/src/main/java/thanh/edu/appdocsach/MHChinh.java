package thanh.edu.appdocsach;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thanh.edu.appdocsach.R.id;
import thanh.edu.appdocsach.adapter.adapterTruyen;
import thanh.edu.appdocsach.adapter.adapterchuyenmuc;
import thanh.edu.appdocsach.adapter.adapterthongtin;
import thanh.edu.appdocsach.adapter.apdapterSlide;
import thanh.edu.appdocsach.database.databasedocsach;
import thanh.edu.appdocsach.model.ChuyenMuc;
import thanh.edu.appdocsach.model.TaiKhoan;
import thanh.edu.appdocsach.model.Truyen;

public class MHChinh extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager2 viewPager2;
    NavigationView navigationView;
    ListView listViewSach,listViewThongTin,listViewManHinhChinh;
    DrawerLayout drawerLayout;

    String email;
    String tentaikhoan;

    ArrayList<ChuyenMuc> chuyenMucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;
    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    adapterchuyenmuc adapterchuyenmuc;
    adapterthongtin adapterthongtin;
    apdapterSlide adapterSlide;

    databasedocsach databasedocsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mhchinh);

        databasedocsach = new databasedocsach(this);

        //Nhận dữ liệu từ màn hình đăng nhập
        Intent intentpq =getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");

        AnhXa();
        ActionBar();
        setupViewPager();

        //sự kiện khi click vào sách
        listViewSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentNoiDung = new Intent(MHChinh.this, ManNoiDung.class);
                String tent =TruyenArrayList.get(position).getTenTruyen();
                String noidungt =TruyenArrayList.get(position).getNoiDung();
                intentNoiDung.putExtra("tentruyen",tent);
                intentNoiDung.putExtra("noidung",noidungt);
                startActivity(intentNoiDung);
            }
        });

        //sự kiện click vào item của listview cu drawer phần listview thông tin
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //đăng truyện
                if(position==0){
                    if(i==2){
                        Intent intentAdmin = new Intent(MHChinh.this, ManAdmin.class);
                        //gửi id tài khoản sang màn hình admin
                        intentAdmin.putExtra("Id",idd);
                        startActivity(intentAdmin);
                    }
                    else {
                        //nếu không phải admin thì không cho vào
                        Toast.makeText(MHChinh.this,"Bạn không có quyền đăng truyện",Toast.LENGTH_SHORT).show();
                        Log.e("ĐĂNG TRUYỆN","bạn không có quyền");
                    }
                }
                //nếu ấn vào phần thông tin sẽ chuyển trang sang thông tin
                else if(position==1){
                    Intent intentManThongTin = new Intent(MHChinh.this,ManThongTin.class);
                    startActivity(intentManThongTin);
                }
                //Chuyển về màn hình đăng nhập nếu ấn đăng xuất
                else if(position==2){
                    finish();
                }
            }
        });


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

    //Phương thức của viewpager2
    private void setupViewPager() {
        viewPager2 = findViewById(id.ViewPager2Slide);
        adapterSlide = new apdapterSlide(this, TruyenArrayList);
        viewPager2.setAdapter(adapterSlide);
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarManHinhChinh);
        viewPager2 = findViewById(id.ViewPager2Slide);
        listViewSach = findViewById(R.id.listViewSach);
        listViewManHinhChinh = findViewById(R.id.listViewManHinhChinh);
        listViewThongTin = findViewById(R.id.listViewThongTin);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerlayout);

        TruyenArrayList = new ArrayList<>();
        Cursor cursor1= databasedocsach.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listViewSach.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

        //Thông tin tài khoảm
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));

        adapterthongtin = new adapterthongtin(this,R.layout.navigation_thongtin,taiKhoanArrayList);
        listViewThongTin.setAdapter(adapterthongtin);

        //chuyên mục
        chuyenMucArrayList = new ArrayList<>();
        chuyenMucArrayList.add(new ChuyenMuc("ĐĂNG TRUYỆN",R.drawable.ic_dangtruyen));
        chuyenMucArrayList.add(new ChuyenMuc("THÔNG TIN APP",R.drawable.ic_thongtin));
        chuyenMucArrayList.add(new ChuyenMuc("ĐĂNG XUẤT",R.drawable.ic_dangxuat));

        adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.chuyenmuc,chuyenMucArrayList);
        listViewManHinhChinh.setAdapter(adapterchuyenmuc);
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