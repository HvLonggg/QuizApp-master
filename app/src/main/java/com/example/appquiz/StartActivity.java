package com.example.appquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        EditText nameInput = findViewById(R.id.name_input);
        Button startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (!name.isEmpty()) {
                // Chuyển sang MainActivity và gửi dữ liệu tên
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                intent.putExtra("USERNAME", name);
                startActivity(intent);
                finish();
            } else {
                // Hiển thị thông báo nếu tên rỗng
                Toast.makeText(StartActivity.this, "Thêm thông tin của người dùng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
