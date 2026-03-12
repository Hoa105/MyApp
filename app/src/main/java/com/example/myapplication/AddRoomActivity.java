package com.example.myapplication;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AddRoomActivity extends AppCompatActivity {
    private EditText idEdit, nameEdit, priceEdit, tenantEdit, phoneEdit;
    private CheckBox occupiedCheck;
    private RoomManager roomManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        roomManager = RoomManager.getInstance();
        initViews();

        findViewById(R.id.saveBtn).setOnClickListener(v -> saveRoom());
        findViewById(R.id.cancelBtn).setOnClickListener(v -> finish());
    }

    private void initViews() {
        idEdit = findViewById(R.id.idEdit);
        nameEdit = findViewById(R.id.nameEdit);
        priceEdit = findViewById(R.id.priceEdit);
        tenantEdit = findViewById(R.id.tenantEdit);
        phoneEdit = findViewById(R.id.phoneEdit);
        occupiedCheck = findViewById(R.id.occupiedCheck);
    }

    private void saveRoom() {
        String id = idEdit.getText().toString().trim();
        String name = nameEdit.getText().toString().trim();
        String priceStr = priceEdit.getText().toString().trim();
        String tenant = tenantEdit.getText().toString().trim();
        String phone = phoneEdit.getText().toString().trim();

        // Validate
        if (!validateInput(id, name, priceStr)) return;

        if (roomManager.isIdExists(id)) {
            Toast.makeText(this, "Mã phòng đã tồn tại!", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        boolean occupied = occupiedCheck.isChecked();

        if (occupied && tenant.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên người thuê!", Toast.LENGTH_SHORT).show();
            return;
        }

        Room room = new Room(id, name, price, occupied, tenant, phone);
        if (!RoomManager.isValidRoom(room)) {
            Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        roomManager.addRoom(room);
        Toast.makeText(this, "Thêm phòng thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateInput(String id, String name, String priceStr) {
        if (id.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã phòng!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên phòng!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (priceStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập giá thuê!", Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Giá thuê phải là số!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
