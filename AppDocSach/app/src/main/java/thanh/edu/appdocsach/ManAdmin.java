package thanh.edu.appdocsach;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import thanh.edu.appdocsach.adapter.adapterTruyen;
import thanh.edu.appdocsach.database.databasedocsach;
import thanh.edu.appdocsach.model.Truyen;

public class ManAdmin extends AppCompatActivity {

    ListView listView;
    Button buttonThem;

    ArrayList<Truyen> TruyenArrayList;
    adapterTruyen adapterTruyen;
    databasedocsach databasedocsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_admin);

        listView = findViewById(R.id.listviewAdmin);
        buttonThem = findViewById(R.id.buttonAddTruyen);
        initList();

        //sự kiện click button thêm
        buttonThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("Id",0);
                Intent intent1 = new Intent(ManAdmin.this, ManDangBai.class);
                intent.putExtra("Id",id);
                startActivity(intent1);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDelete(position);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUpdate(position);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Gán DL vào listview
    public void initList(){
        TruyenArrayList = new ArrayList<>();
        databasedocsach = new databasedocsach(this);

        Cursor cursor1 = databasedocsach.getData2();

        while (cursor1.moveToNext()){

            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int id_tk = cursor1.getInt(4);
            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk));

            adapterTruyen = new adapterTruyen(getApplicationContext(),TruyenArrayList);
            listView.setAdapter(adapterTruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();
    }
    //Dialog xác nhận xóa
    private void DialogDelete(int position) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdelete);
        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtruyen = TruyenArrayList.get(position).getID();
                //Xóa trong SQL
                databasedocsach.Delete(idtruyen);
                //Cập nhật lại listview
                Intent intent = new Intent(ManAdmin.this, ManAdmin.class);
                finish();
                startActivity(intent);
                Toast.makeText(ManAdmin.this, "Xóa truyện thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    // Dialog cập nhật truyện
    private void DialogUpdate(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdate);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesUpdate);
        Button btnNo = dialog.findViewById(R.id.buttonNoUpdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Truyen truyen = TruyenArrayList.get(position);
                Intent intent = new Intent(ManAdmin.this, ManEdit.class);
                intent.putExtra("ID", truyen.getID());
                intent.putExtra("TenTruyen", truyen.getTenTruyen());
                intent.putExtra("NoiDung", truyen.getNoiDung());
                intent.putExtra("Anh", truyen.getAnh());
                startActivity(intent);
                dialog.dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}