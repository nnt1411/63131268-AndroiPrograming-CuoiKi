package thanh.edu.appdocsach;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManThongTin extends AppCompatActivity {

    TextView txtThongTinApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_thong_tin);

        txtThongTinApp = findViewById(R.id.textviewthongtin);
        String thongtin = "LẬP TRÌNH ỨNG DỤNG DI ĐỘNG CUỐI KÌ\n\n" +
                "Tính năng chính:\n" +
                "- Đọc sách offline.\n" +
                "- CSDL sử dụng SQLite\n" +
                "- Đăng truyện mới bằng quyền admin\n" +
                "- Tìm kiếm truyện\n" +
                "Liên hệ hỗ trợ: abc@appdocsach.com";
        txtThongTinApp.setText(thongtin);

    }
}