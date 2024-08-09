package com.nickdieda.pythonlearn.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nickdieda.pythonlearn.Lessons.basic.InstallPy;
import com.nickdieda.pythonlearn.Lessons.basic.Introduction;
import com.nickdieda.pythonlearn.Lessons.basic.OverviewBasic;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.data.QuestionAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenQuiz extends AppCompatActivity {
    private RecyclerView recyclerView;
    private QuestionAdapter questionAdapter;
    private List<Question> questionList;
    private SharedPreferences sharedPreferences;
    LinearLayout ansd, markd;
    TextView tto,quiz,bl;
    ImageView back;
    int activityid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_quiz);

        sharedPreferences = getSharedPreferences("quiz_app", MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_view);
        ansd = findViewById(R.id.ansdis);
        markd = findViewById(R.id.markdis);
        back=findViewById(R.id.returnback);
        quiz=findViewById(R.id.qiuz);
        bl=findViewById(R.id.backtolessons);

        quiz.setText("");
        quiz.setBackgroundResource(R.drawable.les);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tto=findViewById(R.id.title);
        String ttt=getIntent().getStringExtra("tt");
        activityid=getIntent().getIntExtra("id",0);
        tto.setText(ttt);
        tto.setSelected(true);
      //  Toast.makeText(getApplicationContext(),"hi "+activityid,Toast.LENGTH_LONG).show();

        questionList = new ArrayList<>();
        if(activityid==0) {
            questionList.add(new Question("What is the output of the following code: print(2 + 3)", Arrays.asList("5", "23"), 0));
            questionList.add(new Question("Which of the following is a correct variable assignment in Python?", Arrays.asList("x = 5", "5 = x"), 0));
            questionList.add(new Question("What is the name of this app", Arrays.asList("Python Learn", "Python pro"), 0));
            questionList.add(new Question("Which function is used to display output in Python?", Arrays.asList("print()", "display()"), 0));
        } else if (activityid==1) {
            questionList.add(new Question("What is python", Arrays.asList("A programming language", "A mark up language"), 0));
            questionList.add(new Question("Can Python be used for web programming", Arrays.asList("No", "Yes"), 1));
            questionList.add(new Question("Which one will display output \tI love Python", Arrays.asList("print(I love Python)", "print('I love Python')"), 1));
            questionList.add(new Question("Python was created by ", Arrays.asList("Guido van Rossum", "Nick Dieda Dieda"), 0));
        }else if (activityid==2){
            questionList.add(new Question("Can you learn Python using this app even without a PC ?", Arrays.asList("No,of course", "Yes"), 1));
            questionList.add(new Question("Is a program that executes Python codes.", Arrays.asList("Python compiler", "Python runner"), 0));
            questionList.add(new Question("Which of the following does this app have?", Arrays.asList("an Online compiler", "an Offline compiler"), 1));
            questionList.add(new Question("What is the output of the following code println('Hello World')", Arrays.asList("Hello World", "Error"), 1));
        }



        questionAdapter = new QuestionAdapter(questionList, sharedPreferences);
        recyclerView.setAdapter(questionAdapter);

        Button submitButton = findViewById(R.id.submit_b);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allQuestionsAnswered()) {
                    displayResults();

                } else {
                    Toast.makeText(getApplicationContext(), "Please answer all questions", Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent();
                if(activityid==0) {
                 bk  =new Intent(getApplicationContext(), OverviewBasic.class);
                } else if (activityid==1) {
                    bk = new Intent(getApplicationContext(), Introduction.class);
                } else if (activityid==2) {
                    bk = new Intent(getApplicationContext(), InstallPy.class);
                }
                    bk.putExtra("tto",ttt);
                    startActivity(bk);
                }
            });




        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent();
                if(activityid==0) {
                    bk  =new Intent(getApplicationContext(), OverviewBasic.class);
                } else if (activityid==1) {
                    bk = new Intent(getApplicationContext(), Introduction.class);
                } else if (activityid==2) {
                    bk = new Intent(getApplicationContext(), InstallPy.class);
                }
                bk.putExtra("tto",ttt);
                startActivity(bk);
            }
        });


        Button retakeQuizButton = findViewById(R.id.retake_quiz_button);
        retakeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetQuiz();
            }
        });
    }

    private boolean allQuestionsAnswered() {
        for (Question question : questionList) {
            if (question.getAnsweredChoice() == -1) {
                return false;
            }
        }
        return true;
    }

    private void displayResults() {
        int correctAnswers = 0;
        for (Question question : questionList) {
            if (question.isCorrect()) {
                correctAnswers++;
            }
        }

        String resultTitle;
        String resultText;

        if (activityid==0){
            savedir("overv",correctAnswers);
        } else if (activityid==1) {
            savedir("intro",correctAnswers);
    } else if (activityid==2) {
        savedir("installa",correctAnswers);
    }


        if (correctAnswers == questionList.size()) {
            resultTitle = "Excellent!";

            resultText = "You got all the answers correct.";
        } else {
            resultTitle = "Good try!";

            resultText = "You got " + correctAnswers + " out of " + questionList.size() + " questions correct.";
        }


        TextView resultTitleView = findViewById(R.id.result_title);
        TextView resultTextView = findViewById(R.id.result_text);

        resultTitleView.setText(resultTitle);
        resultTextView.setText(resultText);

        ansd.setVisibility(View.GONE);
        markd.setVisibility(View.VISIBLE);

    }

    private void resetQuiz() {


       ansd.setVisibility(View.VISIBLE);
       markd.setVisibility(View.GONE);
    }

    public static class Question {
        private String questionText;
        private List<String> choices;
        private int correctAnswer;
        private int answeredChoice = -1; // -1 indicates unanswered

        public Question(String questionText, List<String> choices, int correctAnswer) {
            this.questionText = questionText;
            this.choices = choices;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<String> getChoices() {
            return choices;
        }

        public int getAnsweredChoice() {
            return answeredChoice;
        }

        public void setAnsweredChoice(int answeredChoice) {
            this.answeredChoice = answeredChoice;
        }

        public boolean isCorrect() {
            return answeredChoice == correctAnswer;
        }
    }

public  void savedir(String dire,int d){
    SharedPreferences perfom = getSharedPreferences("progress", MODE_PRIVATE);
    SharedPreferences.Editor editor = perfom.edit();
    editor.putInt(dire, d);
    editor.apply();
}


}
