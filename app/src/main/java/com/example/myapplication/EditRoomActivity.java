package com.example.myapplication;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class EditRoomActivity extends AppCompatActivity {
    private EditText idEdit, nameEdit, priceEdit, tenantEdit, phoneEdit;
    private CheckBox occupiedCheck;
    private RoomManager roomManager;
    private String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        roomManager = RoomManager.getInstance();
        roomId = getIntent().getStringExtra("roomId");

        initViews();
        loadRoomData();

        findViewById(R.id.saveBtn).setOnClickListener(v -> updateRoom());
        findViewById(R.id.cancelBtn).setOnClickListener(v -> finish());
    }

    private void initViews() {
        idEdit = findViewById(R.id.idEdit);
        nameEdit = findViewById(R.id.nameEdit);
        priceEdit = findViewById(R.id.priceEdit);
        tenantEdit = findViewById(R.id.tenantEdit);
        phoneEdit = findViewById(R.id.phoneEdit);
        occupiedCheck = findViewById(R.id.occupiedCheck);
        idEdit.setEnabled(false);
    }

    private void loadRoomData() {
        Room room = roomManager.getRoomById(roomId);
        if (room != null) {
            idEdit.setText(room.getId());
            nameEdit.setText(room.getName());
            priceEdit.setText(String.valueOf(room.getPrice()));
            tenantEdit.setText(room.getTenantName());
            phoneEdit.setText(room.getPhone());
            occupiedCheck.setChecked(room.isOccupied());
        }
    }

    private void updateRoom() {
        String name = nameEdit.getText().toString().trim();
        String priceStr = priceEdit.getText().toString().trim();
        String tenant = tenantEdit.getText().toString().trim();
        String phone = phoneEdit.getText().toString().trim();

        if (!validateInput(name, priceStr)) return;

        if (occupiedCheck.isChecked() && tenant.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên người thuê!", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        Room updatedRoom = new Room(roomId, name, price, occupiedCheck.isChecked(), tenant, phone);

        if (!RoomManager.isValidRoom(updatedRoom)) {
            Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
            return;
        }

        roomManager.updateRoom(roomId, updatedRoom);
        Toast.makeText(this, "Cập nhật phòng thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateInput(String name, String priceStr) {
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
