package thanh.edu.appdocsach;

import android.content.Intent;
import android.database.Cursor;
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
public class MainActivity extends AppCompatActivity {

    //tạo biến cho đăng nhập
    EditText editTextTaiKhoan,editTextMatKhau;
    Button btnDangNhap,btnDangKy;
    //tạo đối tượng cho database
    databasedocsach databasedocsach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        AnhXa();
        //đối tượng database
        databasedocsach = new databasedocsach(this);
        //Chuyển màn hình sang màn hình đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegis = new Intent(MainActivity.this, MHDangKy.class);
                startActivity(intentRegis);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            //gán dữ liệu lấy từ edittext
            @Override
            public void onClick(View v) {
                String tenTaiKhoan = editTextTaiKhoan.getText().toString();
                String matKhau = editTextMatKhau.getText().toString();

                //con trỏ lấy dữ liệu
                Cursor cursor = databasedocsach.getData();
                boolean found = false; // Kiểm tra xem có đăng nhập thành công không
                //thực hiện vòng lặp để lấy dữ liệu
                //tài khoản ở ô 1, mk ô 2, 0 là id, 3 email, 4 phân quyền
                while (cursor.moveToNext()){
                    String dataTenTaiKhoan = cursor.getString(1);
                    String dataMauKhau = cursor.getString(2);
                    //nếu dữ liệu được nhập khớp với database
                    if (dataTenTaiKhoan.equals(tenTaiKhoan)&&dataMauKhau.equals(matKhau)){
                        found = true;

                        int phanQuyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        //chuyển và gửi dữ liệu sang màn hình chính
                        Intent intent = new Intent(MainActivity.this,MHChinh.class);
                        intent.putExtra("phanq",phanQuyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                        break;
                    }
                }
                // Nếu đăng nhập không được thì báo lỗi
                if (!found) {
                    Toast.makeText(MainActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_LONG).show();
                }
                //trả con trỏ về đầu và đóng
                cursor.moveToFirst();
                cursor.close();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void AnhXa() {
        editTextTaiKhoan = findViewById(R.id.edtTaiKhoan);
        editTextMatKhau = findViewById(R.id.edtMatKhau);
        btnDangKy = findViewById(R.id.signBtn);
        btnDangNhap = findViewById(R.id.loginBtn);
    }
}