package com.example.appquiz;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionText, scoreText, userNameText;
    private LinearLayout answersGroup;
    private Button submitButton, startButton, exitButton;
    private String userName;

    private String[] questions = {
            "Câu 1: Ứng dụng này do nhóm mấy thực hiện?",
            "Câu 2: Giảng viên môn Android là ai?",
            "Câu 3: Thành viên nào của nhóm quê ở Yên Bái?",
            "Câu 4: Ngôn ngữ lập trình nào không phải của Google?",
            "Câu 5: Đâu là đáp án đúng khi nhắc đến tên viết tắt của Đại Học Công Nghệ Đông Á?",
            "Câu 6: Trong lập trình Java, kiểu dữ liệu nào để lưu trữ số thực?",
            "Câu 7: Phương thức nào trong Java dùng để in dữ liệu ra màn hình?",
            "Câu 8: Ký tự nào dùng để kết thúc một câu lệnh trong Java?"
    };

    private String[][] answers = {
            {"Nhóm 3", "Nhóm 4", "Nhóm 7", "Nhóm 10"},
            {"Lê Thùy Dung", "Đào Thị Phương Anh", "Lê Mai Nam", "Đặng Khánh Trung"},
            {"Nguyễn Hoài Nam", "Hà Quang Hà", "Hoàng Văn Long", "Hoàng Quang Huy"},
            {"Kotlin", "Dart", "Python", "Go"},
            {"EAUD", "EAUT", "EUAT", "TAEU"},
            {"int", "float", "char", "boolean"},
            {"print()", "System.out.println()", "write()", "display()"},
            {"#", "$", ";", "@"}
    };

    private int[] correctAnswers = {2, 2, 0, 3, 1, 1, 1, 2};

    private int currentQuestion = 0;
    private int score = 0;
    private int selectedAnswer = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.question_text);
        scoreText = findViewById(R.id.score_text);
        userNameText = findViewById(R.id.user_name_text);
        answersGroup = findViewById(R.id.answers_group);
        submitButton = findViewById(R.id.submit_button);
        startButton = findViewById(R.id.start_button);
        exitButton = findViewById(R.id.exit_button);

        userName = getIntent().getStringExtra("USERNAME");
        userNameText.setText("Hoàn thành các câu hỏi thật tốt nhé " + userName);

        loadQuestion();

        submitButton.setOnClickListener(v -> {
            if (selectedAnswer != -1) {
                if (selectedAnswer == correctAnswers[currentQuestion]) {
                    score++;
                    Toast.makeText(MainActivity.this, "Bạn làm rất tốt!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Cân nhắc lại khi chọn đáp án!", Toast.LENGTH_SHORT).show();
                }

                scoreText.setText("Điểm: " + score);

                currentQuestion++;
                if (currentQuestion < questions.length) {
                    loadQuestion();
                } else {
                    questionText.setText("Bạn đã hoàn thành quiz!");
                    answersGroup.setVisibility(View.GONE);
                    submitButton.setVisibility(View.GONE);
                    userNameText.setText("Chúc mừng " + userName + "! Thành tích gần nhất bạn đạt được là : " + score);
                    userNameText.setGravity(Gravity.CENTER);
                }
            } else {
                Toast.makeText(MainActivity.this, "Vui lòng chọn một đáp án!", Toast.LENGTH_SHORT).show();
            }
        });

        startButton.setOnClickListener(v -> {
            currentQuestion = 0;
            score = 0;
            loadQuestion();
            answersGroup.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            scoreText.setText("Điểm: " + score);
            userNameText.setText("Chú ý hơn để đạt được kết quả tốt nhất nhé " + userName);
        });

        // Thêm tính năng xác nhận khi thoát ứng dụng
        exitButton.setOnClickListener(v -> {
            // Tạo một hộp thoại xác nhận trước khi thoát
            new android.app.AlertDialog.Builder(MainActivity.this)
                    .setMessage("Bạn có chắc chắn muốn thoát ứng dụng?")
                    .setCancelable(false)
                    .setPositiveButton("Có", (dialog, id) -> finish())
                    .setNegativeButton("Không", null)
                    .show();
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestion]);
        selectedAnswer = -1;
        answersGroup.removeAllViews();

        final int buttonBackgroundColor = getResources().getColor(R.color.button_background);

        for (int i = 0; i < answers[currentQuestion].length; i++) {
            final int answerIndex = i;
            final Button answer = new Button(this);
            answer.setText(answers[currentQuestion][i]);
            answer.setBackgroundColor(getResources().getColor(R.color.my_purple));
            answer.setTextColor(getResources().getColor(R.color.white));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 8, 0, 8);
            answer.setLayoutParams(params);

            answer.setOnClickListener(v -> {
                selectedAnswer = answerIndex;
                for (int j = 0; j < answersGroup.getChildCount(); j++) {
                    Button button = (Button) answersGroup.getChildAt(j);
                    button.setBackgroundColor(buttonBackgroundColor);
                }
                answer.setBackgroundColor(getResources().getColor(R.color.my_light_green));
            });

            answersGroup.addView(answer);
        }
    }
}
