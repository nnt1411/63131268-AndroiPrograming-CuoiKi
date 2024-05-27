package thanh.edu.appdocsach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import thanh.edu.appdocsach.database.databasedocsach;
import thanh.edu.appdocsach.model.Truyen;

public class ManDangBai extends AppCompatActivity {

    EditText edtTieuDe,edtNoiDung,edtAnh;
    Button btnDangBai;
    databasedocsach databasedocsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_dang_bai);

        edtTieuDe = findViewById(R.id.dbtieude);
        edtNoiDung = findViewById(R.id.dbnoidung);
        btnDangBai = findViewById(R.id.dbdangbai);
        edtAnh = findViewById(R.id.dbimg);

        databasedocsach = new databasedocsach(this);

        btnDangBai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentruyen = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();
                String img = edtAnh.getText().toString();

                Truyen truyen = CreateTruyen();

                if(tentruyen.equals("") || noidung.equals("") || img.equals("")){
                    Toast.makeText(ManDangBai.this,"Yêu cầu nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else{
                    databasedocsach.AddTruyen(truyen);
                    Intent intent = new Intent(ManDangBai.this,ManAdmin.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ManDangBai.this,"Thêm truyện thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Phương thức tạo truyện
    private Truyen CreateTruyen(){
        String tentruyen = edtTieuDe.getText().toString();
        String noidung = edtNoiDung.getText().toString();
        String img = edtAnh.getText().toString();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Id",0);

        Truyen truyen = new Truyen(tentruyen,noidung,img,id);
        return truyen;
    }
}