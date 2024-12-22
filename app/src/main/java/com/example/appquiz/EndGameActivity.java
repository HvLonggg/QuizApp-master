package com.example.appquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        // Hiển thị thông điệp với điểm số
        TextView endMessage = findViewById(R.id.end_message);
        int score = getIntent().getIntExtra("SCORE", 0);
        endMessage.setText("Chúc mừng! Điểm số của bạn là: " + score);

        // Tìm và xử lý sự kiện cho Button "Quay lại Start"
        Button backToStartButton = findViewById(R.id.back_to_start_button);
        backToStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển hướng về Activity Start
                Intent intent = new Intent(EndGameActivity.this, StartActivity.class);
                startActivity(intent);
                finish();  // Đảm bảo không quay lại EndGameActivity
            }
        });
    }
}
