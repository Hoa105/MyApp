# Ứng dụng Quản lý Nhà Trọ

Ứng dụng Android hỗ trợ quản lý danh sách phòng trọ, thông tin người thuê và tình trạng phòng.

## Tính năng

### 1. **Quản lý phòng**
- Mã phòng, tên phòng, giá thuê
- Tình trạng phòng (Còn trống / Đã thuê)
- Thông tin người thuê (tên, số điện thoại)

### 2. **CRUD Operations**
- **Create**: Thêm phòng mới
- **Read**: Hiển thị danh sách phòng bằng RecyclerView
- **Update**: Sửa thông tin phòng
- **Delete**: Xóa phòng với xác nhận

### 3. **Giao diện**
- RecyclerView với Material Design
- Hiển thị màu theo tình trạng:
  - 🟢 Xanh: Phòng còn trống
  - 🔴 Đỏ: Phòng đã thuê
- Nút Sửa / Xóa trong mỗi item

### 4. **Validate dữ liệu**
- Kiểm tra mã phòng không trùng
- Kiểm tra giá thuê hợp lệ
- Kiểm tra tên người thuê nếu phòng đã cho thuê

## Kiến trúc

Ứng dụng sử dụng kiến trúc **MVC**:

```
Model (Room.java, RoomManager.java)
  ↓
View (activity_*.xml, room_item.xml)
  ↓
Controller (MainActivity.java, AddRoomActivity.java, EditRoomActivity.java, RoomAdapter.java)
```

## Cấu trúc Project

```
app/src/main/
├── java/com/example/myapplication/
│   ├── Room.java                 # Model
│   ├── RoomManager.java          # Singleton - quản lý dữ liệu
│   ├── MainActivity.java         # Hiển thị danh sách
│   ├── AddRoomActivity.java      # Thêm phòng mới
│   ├── EditRoomActivity.java     # Sửa thông tin phòng
│   └── RoomAdapter.java          # Adapter cho RecyclerView
└── res/layout/
    ├── activity_main.xml         # Main screen
    ├── activity_add_room.xml     # Add screen
    ├── activity_edit_room.xml    # Edit screen
    └── room_item.xml             # RecyclerView item
```

## Công nghệ sử dụng

- Java 11
- Android API 24+
- Material Design 3
- AndroidX
- RecyclerView

## Dữ liệu

Dữ liệu được lưu tạm thời trong `List` (không dùng Database/Room).
Dữ liệu sẽ mất khi ứng dụng đóng.

## Cách sử dụng

1. **Mở ứng dụng**: Hiển thị danh sách phòng
2. **Thêm phòng**: Nhấn nút `+` (FAB)
3. **Sửa phòng**: Nhấn nút "Sửa" trong item
4. **Xóa phòng**: Nhấn nút "Xóa" hoặc nhấn giữ item

## Build & Run

```bash
./gradlew build         # Build project
./gradlew assembleDebug # Build APK debug
./gradlew installDebug  # Install vào điện thoại
```

## Tác giả

- Team development

---

**Yêu cầu dự án**: Xây dựng ứng dụng quản lý nhà trọ với đầy đủ CRUD, MVC, RecyclerView
