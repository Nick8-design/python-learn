package com.nickdieda.pythonlearn.data;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.quiz.OpenQuiz;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private List<OpenQuiz.Question> questionList;
    private SharedPreferences sharedPreferences;

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        public TextView questionNumber, questionStatus, questionText;
        public RadioGroup choicesGroup;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionNumber = itemView.findViewById(R.id.question_number);
            questionStatus = itemView.findViewById(R.id.question_status);
            questionText = itemView.findViewById(R.id.question_text);
            choicesGroup = itemView.findViewById(R.id.choices_group);
        }
    }

    public QuestionAdapter(List<OpenQuiz.Question> questionList, SharedPreferences sharedPreferences) {
        this.questionList = questionList;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final QuestionViewHolder holder, int position) {
        final OpenQuiz.Question question = questionList.get(position);

        holder.questionNumber.setText((position + 1) + "/" + questionList.size());

        holder.questionText.setText(question.getQuestionText());

        holder.choicesGroup.removeAllViews();
        for (int i = 0; i < question.getChoices().size(); i++) {
            RadioButton radioButton = new RadioButton(holder.itemView.getContext());
            radioButton.setText(question.getChoices().get(i));
            radioButton.setId(i);
            holder.choicesGroup.addView(radioButton);

            // Set the checked state if the question was already answered
            if (question.getAnsweredChoice() == i) {
                radioButton.setChecked(true);
                holder.questionStatus.setText("Answered");
            }
        }

        holder.choicesGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    OpenQuiz.Question currentQuestion = questionList.get(currentPosition);
                    currentQuestion.setAnsweredChoice(checkedId);
                    holder.questionStatus.setText("Answered");
                    saveAnswerState(currentPosition, checkedId);
                }
            }
        });

        // Load the saved state if the app was closed
        int savedAnswer = loadAnswerState(position);
        if (savedAnswer != -1) {
            holder.choicesGroup.check(savedAnswer);
            holder.questionStatus.setText("Answered");
        } else {
            holder.questionStatus.setText("UnAnswered");
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    private void saveAnswerState(int position, int answer) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("question_" + position, answer);
        editor.apply();
    }

    private int loadAnswerState(int position) {
        return sharedPreferences.getInt("question_" + position, -1);
    }
}
