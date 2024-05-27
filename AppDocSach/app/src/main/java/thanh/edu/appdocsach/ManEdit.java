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

public class ManEdit extends AppCompatActivity {

    EditText edtTenTruyen, edtNoiDung, edtAnh;
    Button btnUpdate, btnCancel;
    databasedocsach databasedocsach;

    int idTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_edit);

        edtTenTruyen = findViewById(R.id.edtTenTruyen);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        edtAnh = findViewById(R.id.edtAnh);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnCancel = findViewById(R.id.buttonCancel);

        databasedocsach = new databasedocsach(this);

        Intent intent = getIntent();
        idTruyen = intent.getIntExtra("ID", -1);
        String tenTruyen = intent.getStringExtra("TenTruyen");
        String noiDung = intent.getStringExtra("NoiDung");
        String anh = intent.getStringExtra("Anh");

        edtTenTruyen.setText(tenTruyen);
        edtNoiDung.setText(noiDung);
        edtAnh.setText(anh);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTruyenMoi = edtTenTruyen.getText().toString();
                String noiDungMoi = edtNoiDung.getText().toString();
                String anhMoi = edtAnh.getText().toString();

                databasedocsach.updateTruyen(idTruyen, tenTruyenMoi, noiDungMoi, anhMoi);
                Intent intentEdit = new Intent(ManEdit.this,ManAdmin.class);
                Toast.makeText(ManEdit.this, "Cập nhật truyện thành công", Toast.LENGTH_SHORT).show();
                startActivity(intentEdit);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}