package thanh.edu.appdocsach;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import thanh.edu.appdocsach.adapter.adapterTruyen;
import thanh.edu.appdocsach.database.databasedocsach;
import thanh.edu.appdocsach.model.Truyen;

public class ManTimKiem extends AppCompatActivity {

    ListView listView;
    EditText edt;
    ArrayList<Truyen> truyenArrayList;
    ArrayList<Truyen> arrayList;

    adapterTruyen adapterTruyen;
    databasedocsach databasedocsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_tim_kiem);

        listView = findViewById(R.id.listviewtimkiem);
        edt = findViewById(R.id.timkiem);
        initList();

        //Sự kiện khi click item truyện
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManTimKiem.this,ManNoiDung.class);
                String tent =   arrayList.get(position).getTenTruyen();
                String noidungt = arrayList.get(position).getNoiDung();
                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                //Log.e("Tên truyện : ",tent);
                startActivity(intent);
            }
        });

        //edittext search
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Chức năng tìm kiếm
    private void filter(String text){

        //xóa sau mỗi lần gọi tới filter
        arrayList.clear();

        ArrayList<Truyen> filteredList = new ArrayList<>();

        for(Truyen item : truyenArrayList){
            if (item.getTenTruyen().toLowerCase().contains(text.toLowerCase())){
                //thêm item vào filterlist
                filteredList.add(item);
                //Thêm dữ liệu để hiển thị ra item nội dung
                arrayList.add(item);
            }
        }
        adapterTruyen.filterList(filteredList);
    }

    //Hàm  gán dữ liệu từ CSDL vào listview
    public void initList(){
        truyenArrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        databasedocsach = new databasedocsach(this);

        Cursor cursor = databasedocsach.getData2();
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String tentruyen = cursor.getString(1);
            String noidung = cursor.getString(2);
            String anh = cursor.getString(3);
            int id_tk = cursor.getInt(4);

            //Thêm dữ liệu vào mảng
            truyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            arrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));
            adapterTruyen = new adapterTruyen(getApplicationContext(),truyenArrayList);
            //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursor.moveToFirst();
        cursor.close();
    }
}