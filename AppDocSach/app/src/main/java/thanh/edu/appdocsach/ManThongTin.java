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

        txtThongTinApp =findViewById(R.id.textviewthongtin);
        String thongtin = "LẬP TRÌNH ỨNG DỤNG DI ĐỘNG BÀI TẬP CUỐI KÌ\n"+
                "63131268 NGUYỄN NGỌC THÀNH";
        txtThongTinApp.setText(thongtin);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}