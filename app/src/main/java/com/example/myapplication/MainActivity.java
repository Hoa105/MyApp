package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RoomAdapter adapter;
    private RoomManager roomManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomManager = RoomManager.getInstance();
        initDummyData();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RoomAdapter(this, roomManager.getAllRooms());
        recyclerView.setAdapter(adapter);

        FloatingActionButton addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddRoomActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList(roomManager.getAllRooms());
    }

    private void initDummyData() {
        if (roomManager.getAllRooms().isEmpty()) {
            roomManager.addRoom(new Room("101", "Phòng 101 - Nhà hàng xóm", 2000000, false, "", ""));
            roomManager.addRoom(new Room("102", "Phòng 102 - Gần cửa sổ", 2500000, true, "Nguyễn Văn A", "0912345678"));
            roomManager.addRoom(new Room("103", "Phòng 103 - Thoáng mát", 2200000, false, "", ""));
        }
    }
}
