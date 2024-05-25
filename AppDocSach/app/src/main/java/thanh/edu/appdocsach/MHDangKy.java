package thanh.edu.appdocsach;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import thanh.edu.appdocsach.model.TaiKhoan;
import thanh.edu.appdocsach.database.databasedocsach;

public class MHDangKy extends AppCompatActivity {

    EditText editTextDKTaiKhoan, editTextDKMatKhau,editTextDKMail;
    Button btnDangKy,btnTroLai;
    databasedocsach databasedocsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mhdang_ky);

        databasedocsach = new databasedocsach(this);
        AnhXa();
        //Xử lý khi ấn đăng ký, nếu đủ thông tin thì đưa dữ liệu vào database
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = editTextDKTaiKhoan.getText().toString();
                String matkhau = editTextDKMatKhau.getText().toString();
                String mail = editTextDKMail.getText().toString();

                if(taikhoan.isEmpty() || matkhau.isEmpty() || mail.isEmpty()) {
                    Toast.makeText(MHDangKy.this, "Chưa đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    // Kiểm tra xem tài khoản đã tồn tại hay chưa
                    Cursor cursor = databasedocsach.getData(); // Giả sử phương thức này trả về tất cả dữ liệu người dùng
                    boolean accountExists = false;

                    while(cursor.moveToNext()) {
                        if(cursor.getString(1).equalsIgnoreCase(taikhoan)) {
                            accountExists = true;
                            break;
                        }
                    }
                    cursor.close();

                    if(accountExists) {
                        Toast.makeText(MHDangKy.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else {
                        TaiKhoan taiKhoanMoi = new TaiKhoan(taikhoan, matkhau, mail, 1);
                        databasedocsach.AddTaiKhoan(taiKhoanMoi);
                        Toast.makeText(MHDangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(MHDangKy.this, MainActivity.class);
                startActivity(intentBack);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Phương thức tạo tài khoản
    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = editTextDKTaiKhoan.getText().toString();
        String matkhau = editTextDKMatKhau.getText().toString();
        String mail = editTextDKMail.getText().toString();
        int phanquyen = 1;
        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,mail,phanquyen);
        return tk;
    }

    private void AnhXa() {
        editTextDKMail = findViewById(R.id.edtDKMail);
        editTextDKMatKhau = findViewById(R.id.edtDKMatKhau);
        editTextDKTaiKhoan = findViewById(R.id.edtDKTKhoan);
        btnDangKy = findViewById(R.id.dangKyBtn);
        btnTroLai = findViewById(R.id.backBtn);
    }
}