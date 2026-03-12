package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.widget.TextView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private Context context;
    private List<Room> rooms;
    private static final int COLOR_OCCUPIED = android.graphics.Color.parseColor("#FFCDD2");
    private static final int COLOR_AVAILABLE = android.graphics.Color.parseColor("#C8E6C9");

    public RoomAdapter(Context context, List<Room> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MaterialCardView cardView = (MaterialCardView) LayoutInflater.from(context)
                .inflate(R.layout.room_item, parent, false);
        return new RoomViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);

        holder.idView.setText("Phòng: " + room.getId());
        holder.nameView.setText(room.getName());
        holder.priceView.setText(String.format("%.0f đ/tháng", room.getPrice()));

        // Tô màu theo tình trạng
        if (room.isOccupied()) {
            holder.card.setCardBackgroundColor(COLOR_OCCUPIED);
            holder.statusView.setText("Đã thuê");
            holder.statusView.setTextColor(android.graphics.Color.parseColor("#D32F2F"));
        } else {
            holder.card.setCardBackgroundColor(COLOR_AVAILABLE);
            holder.statusView.setText("Còn trống");
            holder.statusView.setTextColor(android.graphics.Color.parseColor("#388E3C"));
        }

        holder.editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditRoomActivity.class);
            intent.putExtra("roomId", room.getId());
            context.startActivity(intent);
        });

        holder.deleteBtn.setOnClickListener(v -> showDeleteDialog(position, room.getId()));

        holder.card.setOnLongClickListener(v -> {
            showDeleteDialog(position, room.getId());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    private void showDeleteDialog(int position, String roomId) {
        new MaterialAlertDialogBuilder(context)
                .setTitle("Xóa phòng")
                .setMessage("Bạn có chắc muốn xóa phòng này không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    RoomManager.getInstance().deleteRoom(roomId);
                    notifyItemRemoved(position);
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    public void updateList(List<Room> newRooms) {
        this.rooms = newRooms;
        notifyDataSetChanged();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView card;
        TextView idView, nameView, priceView, statusView;
        MaterialButton editBtn, deleteBtn;

        public RoomViewHolder(@NonNull MaterialCardView itemView) {
            super(itemView);
            this.card = itemView;
            this.idView = itemView.findViewById(R.id.idView);
            this.nameView = itemView.findViewById(R.id.nameView);
            this.priceView = itemView.findViewById(R.id.priceView);
            this.statusView = itemView.findViewById(R.id.statusView);
            this.editBtn = itemView.findViewById(R.id.editBtn);
            this.deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
