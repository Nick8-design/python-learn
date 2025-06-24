package com.nickdieda.pythonlearn.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nickdieda.pythonlearn.MainActivity;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.BrightnessUtil;

import java.util.Arrays;
import java.util.List;
public class Projects extends AppCompatActivity {
   TextView title;
    LinearLayout urpro;
    private RecyclerView recyclerView;
    LinearLayout homefra,lessonfra,cd;
    ImageView homei,lessoni;
    TextView hometext,lessontext,percentage;
    private MyAdapter myAdapter;

    private ImageButton menuButton;
    List<String> programNames = Arrays.asList(
            "Hello World", "Factorial", "Palindrome", "FizzBuzz", "Prime Numbers",
            "Bubble Sort", "Quick Sort", "Merge Sort", "Binary Search", "Linear Search",
            "Tic Tac Toe", "Calculator", "To-Do List", "Alarm Clock", "Game of Life"
    );

    List<String> programCodes = Arrays.asList(
            // Hello World
            "# Hello World\nprint('Hello, World!')",

            // Factorial
            "# Factorial\ndef factorial(n):\n if n == 0: return 1\n else: return n * factorial(n-1)\nprint(factorial(5))",

            // Palindrome
            "# Palindrome\ndef is_palindrome(s):\n return s == s[::-1]\nprint(is_palindrome('level'))",

            // FizzBuzz
            "# FizzBuzz\nfor i in range(1, 101):\n if i % 3 == 0 and i % 5 == 0: print('FizzBuzz')\n elif i % 3 == 0: print('Fizz')\n elif i % 5 == 0: print('Buzz')\n else: print(i)",

            // Prime Numbers
            "# Prime Numbers\nn = 100\nprimes = []\nfor x in range(2, n):\n is_prime = True\n for y in range(2, int(x**0.5)+1):\n  if x % y == 0:\n   is_prime = False\n   break\n if is_prime:\n  primes.append(x)\nprint(primes)",

            // Bubble Sort
            "# Bubble Sort\ndef bubble_sort(arr):\n for i in range(len(arr)):\n  for j in range(0, len(arr)-i-1):\n   if arr[j] > arr[j+1]:\n    arr[j], arr[j+1] = arr[j+1], arr[j]\narr = [64, 34, 25, 12, 22, 11, 90]\nbubble_sort(arr)\nprint(arr)",

            // Quick Sort
            "# Quick Sort\ndef quick_sort(arr):\n if len(arr) <= 1: return arr\n pivot = arr[len(arr) // 2]\n left = [x for x in arr if x < pivot]\n middle = [x for x in arr if x == pivot]\n right = [x for x in arr if x > pivot]\n return quick_sort(left) + middle + quick_sort(right)\narr = [3,6,8,10,1,2,1]\nprint(quick_sort(arr))",

            // Merge Sort
            "# Merge Sort\ndef merge_sort(arr):\n if len(arr) > 1:\n  mid = len(arr)//2\n  L = arr[:mid]\n  R = arr[mid:]\n  merge_sort(L)\n  merge_sort(R)\n  i = j = k = 0\n  while i < len(L) and j < len(R):\n   if L[i] < R[j]:\n    arr[k] = L[i]\n    i += 1\n   else:\n    arr[k] = R[j]\n    j += 1\n   k += 1\n  while i < len(L):\n   arr[k] = L[i]\n   i += 1\n   k += 1\n  while j < len(R):\n   arr[k] = R[j]\n   j += 1\n   k += 1\narr = [12, 11, 13, 5, 6, 7]\nmerge_sort(arr)\nprint(arr)",

            // Binary Search
            "# Binary Search\ndef binary_search(arr, x):\n l, r = 0, len(arr)-1\n while l <= r:\n  mid = (l + r) // 2\n  if arr[mid] == x:\n   return mid\n  elif arr[mid] < x:\n   l = mid + 1\n  else:\n   r = mid - 1\n return -1\narr = [2, 3, 4, 10, 40]\nx = 10\nprint(binary_search(arr, x))",

            // Linear Search
            "# Linear Search\ndef linear_search(arr, x):\n for i in range(len(arr)):\n  if arr[i] == x:\n   return i\n return -1\narr = [2, 3, 4, 10, 40]\nx = 10\nprint(linear_search(arr, x))",

            // Tic Tac Toe
            "# Tic Tac Toe\nboard = [' ' for _ in range(9)]\ndef print_board():\n for row in [board[i*3:(i+1)*3] for i in range(3)]:\n  print('| ' + ' | '.join(row) + ' |')\ndef is_winner(board, player):\n win_cond = [(0, 1, 2), (3, 4, 5), (6, 7, 8), (0, 3, 6), (1, 4, 7), (2, 5, 8), (0, 4, 8), (2, 4, 6)]\n return any(board[a] == board[b] == board[c] == player for (a, b, c) in win_cond)\ndef play_game():\n player = 'X'\n for _ in range(9):\n  print_board()\n  move = int(input(f'Player {player}, enter your move (1-9): ')) - 1\n  if board[move] == ' ':\n   board[move] = player\n   if is_winner(board, player):\n    print_board()\n    print(f'Player {player} wins!')\n    return\n   player = 'O' if player == 'X' else 'X'\n  else:\n   print('Invalid move! Try again.')\n print_board()\n print('It\'s a tie!')\nplay_game()",

            // Calculator
            "# Calculator\ndef add(x, y): return x + y\ndef subtract(x, y): return x - y\ndef multiply(x, y): return x * y\ndef divide(x, y): return x / y\nprint(add(2, 3))\nprint(subtract(7, 4))\nprint(multiply(3, 5))\nprint(divide(10, 2))",

            // To-Do List
            "# To-Do List\nclass TodoList:\n def __init__(self):\n  self.tasks = []\n def add_task(self, task):\n  self.tasks.append(task)\n def remove_task(self, task):\n  self.tasks.remove(task)\n def show_tasks(self):\n  return self.tasks\ntodo = TodoList()\ntodo.add_task('Buy milk')\ntodo.add_task('Read book')\nprint(todo.show_tasks())\ntodo.remove_task('Buy milk')\nprint(todo.show_tasks())",

            // Alarm Clock
            "# Alarm Clock\nimport time\nfrom datetime import datetime\ndef set_alarm(alarm_time):\n while True:\n  now = datetime.now().strftime('%H:%M:%S')\n  if now == alarm_time:\n   print('Wake up!')\n   break\n  time.sleep(1)\nalarm_time = '07:00:00'\nset_alarm(alarm_time)",

            // Game of Life
            "# Game of Life\nimport numpy as np\nimport matplotlib.pyplot as plt\ndef update(board):\n new_board = board.copy()\n for i in range(board.shape[0]):\n  for j in range(board.shape[1]):\n   total = (board[i, (j-1)%board.shape[1]] + board[i, (j+1)%board.shape[1]] + board[(i-1)%board.shape[0], j] + board[(i+1)%board.shape[0], j] + board[(i-1)%board.shape[0], (j-1)%board.shape[1]] + board[(i-1)%board.shape[0], (j+1)%board.shape[1]] + board[(i+1)%board.shape[0], (j-1)%board.shape[1]] + board[(i+1)%board.shape[0], (j+1)%board.shape[1]])\n   if board[i, j] == 1:\n    if (total < 2) or (total > 3):\n     new_board[i, j] = 0\n   else:\n    if total == 3:\n     new_board[i, j] = 1\n return new_board\nboard = np.random.choice([0, 1], size=(10, 10))\nplt.imshow(board, cmap='binary')\nplt.show()"
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_projects);

        title=findViewById(R.id.title);
        urpro=findViewById(R.id.mypro);
        homefra=findViewById(R.id.home_fra);
        lessonfra=findViewById(R.id.lesson_fra);
        homei=findViewById(R.id.home_image);
        lessoni=findViewById(R.id.lessons_im);
        hometext=findViewById(R.id.home_text);
        lessontext=findViewById(R.id.lesson_text);
        recyclerView = findViewById(R.id.recycler_view);
        menuButton = findViewById(R.id.menu_button);



        title.setText("Projects");
        urpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Myproject.class);
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });

        homei.setImageResource(R.drawable.unhome);
        lessoni.setImageResource(R.drawable.unbook);
        hometext.setTextColor(getResources().getColor(R.color.off));
        lessontext.setTextColor(getResources().getColor(R.color.off));
        homei.setBackgroundResource(R.drawable.round_unselected);
        lessoni.setBackgroundResource(R.drawable.round_unselected);

        lessonfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LessonsActivity.class);
                startActivity(intent);

            }
        });


        homefra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });




        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(programNames, programCodes);
        recyclerView.setAdapter(myAdapter);
    }
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<String> names;
        private List<String> codes;

        MyAdapter(List<String> names, List<String> codes) {
            this.names = names;
            this.codes = codes;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(names.get(position), codes.get(position));
        }

        @Override
        public int getItemCount() {
            return names.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private String code;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.item_text);
            itemView.setOnClickListener(this);
        }

        void bind(String name, String code) {
            nameTextView.setText(name);
            this.code = code;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Projects.this, CompilerPy.class);
            intent.putExtra("mainid",11);
            intent.putExtra("code", code);
          //  Toast.makeText(Projects.this, code, Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.kebab, popupMenu.getMenu());

        popupMenu.getMenu().findItem(R.id.action_save).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding
        popupMenu.getMenu().findItem(R.id.action_open).setVisible(false); // Or use setEnabled(false) if you want to disable it instead of hiding
        popupMenu.getMenu().findItem(R.id.action_sav).setVisible(false);
        popupMenu.getMenu().findItem(R.id.rate_us).setVisible(false);


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_settings) {
                    BrightnessUtil.showBrightnessDialog(Projects.this);
                    return true;

                } else {
                    return false;
                }
            }

        });

        popupMenu.show();
    }
}