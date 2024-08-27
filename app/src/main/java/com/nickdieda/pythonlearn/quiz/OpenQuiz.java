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
import com.nickdieda.pythonlearn.Lessons.basic.PDis;
import com.nickdieda.pythonlearn.Lessons.basic.PyComments;
import com.nickdieda.pythonlearn.Lessons.basic.PyState;
import com.nickdieda.pythonlearn.Lessons.basic.Syntaxs;
import com.nickdieda.pythonlearn.Lessons.basic.WPCodes;
import com.nickdieda.pythonlearn.R;
import com.nickdieda.pythonlearn.common.ReturnActivity;
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
    ImageView back,imgmark;
   private int activityid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_quiz);

        sharedPreferences = getSharedPreferences("quiz_app", MODE_PRIVATE);

        recyclerView = findViewById(R.id.recycler_view);
        ansd = findViewById(R.id.ansdis);
        markd = findViewById(R.id.markdis);
        imgmark=findViewById(R.id.imgmark);
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
        }else if (activityid==3) {
            questionList.add(new Question("Python files are saved with which of the following file extension?", Arrays.asList(".js", ".py", ".php"), 1));
            questionList.add(new Question("Can you write and save python files using this app?", Arrays.asList("Yes", "Never", "I hope so"), 0));
            questionList.add(new Question("Which of the following does this app have?", Arrays.asList("Python Console", "Python interpreter(compiler)"), 1));
            questionList.add(new Question("How do we run this file (factorial.py) in command prompt or terminal.", Arrays.asList("python factorial.py", "run factorial.py"), 0));
        } else if (activityid==4){
                questionList.add(new Question("Which of the following function displays output in python", Arrays.asList("print();", "cout()","print()"), 2));
                questionList.add(new Question("Can we print numbers and string together?", Arrays.asList("Yes", "Never","I hope so"), 0));
                questionList.add(new Question("What is the output print('Nick love'+8)", Arrays.asList("Nick love 8","error", "Nick love+8"), 1));
                questionList.add(new Question("What is the output print('Nick love'8)", Arrays.asList("Nick love 8","error", "Nick love+8"), 1));
        } else if (activityid==5){
            questionList.add(new Question("How many statements are here?a=6;b=6;print(a+b)", Arrays.asList("1", "2","3"), 2));
            questionList.add(new Question("Can one statement be in more than one line?", Arrays.asList("Yes", "Never","I hope so"), 0));
            questionList.add(new Question("In Python, a line typically contains how many statements", Arrays.asList("two","one"), 1));
            questionList.add(new Question("Can a statement be used to compute for a value", Arrays.asList("Never","Yes", "No"), 1));
        } else if (activityid==6){
            questionList.add(new Question("It indicates a group (block) of statements in Python.", Arrays.asList("indentation","brackets"), 0));
            questionList.add(new Question("Is indentation important in python", Arrays.asList("No","Yes"), 1));
            questionList.add(new Question("Not Observing Indentation in a block of statements leads to?", Arrays.asList("indentation error","name error"), 0));
            questionList.add(new Question("Is the block of statements below well indented \nx=3\ny=8\n\tprint(x+y)", Arrays.asList("Never","Yes", "No"), 2));
        } else if (activityid==7){
            questionList.add(new Question("What are comments used for? .", Arrays.asList("Deleting codes","Explaining codes"), 1));
            questionList.add(new Question("Are comments interpreted by the compiler", Arrays.asList("No","Yes"), 0));
            questionList.add(new Question("Which character is used to comment in python", Arrays.asList("#","//"), 0));
            questionList.add(new Question("Will comments be executed?", Arrays.asList("Wow","Yes", "No"), 2));
        } else if (activityid==8){
            questionList.add(new Question("It is used to store a value.", Arrays.asList("operator","variable"), 1));
            questionList.add(new Question("Which character is used to assign value to a variable?", Arrays.asList(">","="), 1));
            questionList.add(new Question("Which one is assigned correctly ", Arrays.asList("Dr.Angulu = 1","dr_angulu = 1"), 1));
            questionList.add(new Question("Which value is stored in value x=8", Arrays.asList("8","0", "Gen z"), 0));
        } else if (activityid==9){
            questionList.add(new Question("Which of the following is an integer data type?", Arrays.asList("True","'False'","454"), 2));
            questionList.add(new Question("Which of the following is an string data type?", Arrays.asList("'211'","hello","True"), 0));
            questionList.add(new Question("Which of the following is an Boolean data type?", Arrays.asList("True","'True'"), 0));
            questionList.add(new Question("Which of the following is an floating point data type?", Arrays.asList("8","'8'", "1.11"), 2));
        } else if (activityid==10){
            questionList.add(new Question("Is a number without decimal", Arrays.asList("Floating point","Integer","complex"), 1));
            questionList.add(new Question("Which of the following is a floating number ", Arrays.asList("'211'","211","2.11"), 2));
            questionList.add(new Question("It is an imaginary number.", Arrays.asList("int","'True'","complex"), 2));
            questionList.add(new Question("What is the type of the x variable below?\nx = 4.44", Arrays.asList("complex","int", "float"), 2));
        } else if (activityid==11){
            questionList.add(new Question("Which function is used to round numbers to the specified decimal point", Arrays.asList("Floating point()","abs()","round()"), 2));
            questionList.add(new Question("Which of the following returns the absolute value of a number", Arrays.asList("round()","Abs()","abs()"), 2));
            questionList.add(new Question("What is the value of \n\tw=pow(4,2)", Arrays.asList("16","8","6"), 0));
            questionList.add(new Question("What is the absolute value of -1.2", Arrays.asList("-1","1.2", "0.2"), 1));
        } else if (activityid==12){
            questionList.add(new Question("Which of the following is a well declared string ", Arrays.asList("string = string","stn = 'hi'","string = new string()"), 1));
            questionList.add(new Question("Why should you use double quotes ", Arrays.asList("when string contains double quotes","when string contains single quotes","abs()"), 1));
            questionList.add(new Question("Which character is used to escape characters", Arrays.asList("\\","/"), 0));
            questionList.add(new Question("We combine or concatenate strings using?", Arrays.asList("+","+1", "-"), 0));
        } else if (activityid==13){
            questionList.add(new Question("Which function can help convert text to lower case", Arrays.asList("lower()","upper()"), 0));
            questionList.add(new Question("Which of the following capitalizes a string", Arrays.asList("capitalize()","capital()","Upper()"), 0));
            questionList.add(new Question("Which character will be displayed.\nx=mango\nprint(x[3])", Arrays.asList("n","g","error"), 1));
            questionList.add(new Question("What function replaces parts of a string", Arrays.asList("replace_with()","replace()", "remove()"), 1));
        } else if (activityid==14){
            questionList.add(new Question("Which of the following convert objects to strings ", Arrays.asList("string()","ast()","str()"), 2));
            questionList.add(new Question("To convert object to integer we use which method", Arrays.asList("int","int()","Int()"), 1));
            questionList.add(new Question("Can we convert a string representing number to integer", Arrays.asList("YES","NOPE"), 0));
            questionList.add(new Question("Which of the following can be converted to float", Arrays.asList("'33'","aa"), 0));
        } else if (activityid==15){
            questionList.add(new Question("Which data type returns True or False", Arrays.asList("Conditional","Boolean","if"), 1));
            questionList.add(new Question("Which function do we use to check if the data type is boolean", Arrays.asList("type()","bool()","name()"), 0));
            questionList.add(new Question("Can boolean be used inside an if statement?", Arrays.asList("YES","NOPE"), 0));
            questionList.add(new Question("Which data type do we get when we compare objects", Arrays.asList("boolean","true"), 0));
        } else if (activityid==16){
            questionList.add(new Question("Which among this is an operator from this problem? \na > 10", Arrays.asList("a",">","10"), 1));
            questionList.add(new Question("Which of the following is an operand here?\n5 == 5", Arrays.asList("5","True","=="), 0));
            questionList.add(new Question("Symbols that perform operations on operands are called.", Arrays.asList("operators","functions"), 0));
            questionList.add(new Question("Can operators be used on strings?", Arrays.asList("yes","no"), 0));
        } else if (activityid==17){
            questionList.add(new Question("Which operator is used in division", Arrays.asList("/","\\","-:-"), 0));
            questionList.add(new Question("Which operator is used here?\nprint(5 % 5)", Arrays.asList("5","True","%"), 2));
            questionList.add(new Question("What is the out put of the code\nprint(5%)", Arrays.asList("'5%'","5%","error"), 2));
            questionList.add(new Question("Can operators be used on strings?", Arrays.asList("yes","no"), 0));
        } else if (activityid==18){
            questionList.add(new Question("Am used to assign value to variable.", Arrays.asList("/","==","="), 2));
            questionList.add(new Question("Which of the following is modulus assignment ", Arrays.asList("%","%=","mod"), 1));
            questionList.add(new Question("Is this a = a / 5 same as a /=5", Arrays.asList("'Yes'","No","error"), 0));
            questionList.add(new Question("Can we assign sting to a variable?", Arrays.asList("yes","no"), 0));
        } else if (activityid==19){
            questionList.add(new Question("Which operator can we use to confirm if the name entered is the same as the name in the database.", Arrays.asList("/","==","="), 1));
            questionList.add(new Question("Which operator can we use to check if someone can get an ID ", Arrays.asList("==",">=","="), 1));
            questionList.add(new Question("Comparison operator returns which data type", Arrays.asList("True","Boolean","False"), 1));
            questionList.add(new Question("Can we compare stings", Arrays.asList("yes","no"), 0));
        } else if (activityid==20){
            questionList.add(new Question("What is the output\nprint(True and False)", Arrays.asList("True","error","False"), 2));
            questionList.add(new Question("What is the output\nprint(True and true)", Arrays.asList("True","error","False"), 1));
            questionList.add(new Question("If those 2 bottle each have water bring them and also bring them if there is water in one but if none of them has water dont bring them ,which logical operator will i use here", Arrays.asList("and","or","not"), 1));
            questionList.add(new Question("What does logic not do operands", Arrays.asList("negate","False"), 0));
        } else if (activityid==21){
            questionList.add(new Question("It returns False when both operands points to the same object", Arrays.asList("is","not","is not"), 2));
            questionList.add(new Question("Are this the same a == b  and a is b from a = 10,b = 10", Arrays.asList("yes","no","error"), 1));
            questionList.add(new Question("I want to check if the variable am using are both pointing the name variable i had intialized,which operator should I use?", Arrays.asList("is","or","is not"), 0));
            questionList.add(new Question("Type of operator that tells us if operands are points to the same object are called?", Arrays.asList("logical","identity"), 1));
        } else if (activityid==22){
            questionList.add(new Question("To check if my name is present in the guest list ,I use which operator?", Arrays.asList("is","in","is not"), 1));
            questionList.add(new Question("Checks if a value is present in an object am which type of operator?", Arrays.asList("Logical","Identity","Membership"), 2));
            questionList.add(new Question("not in checks if?", Arrays.asList("values are pointing same object","the value is not in the object"), 1));
            questionList.add(new Question("Which is not a membership operator", Arrays.asList("in","is","not in"), 1));
        } else if (activityid==23){
            questionList.add(new Question("Does python have containers", Arrays.asList("yes","nop"), 0));
            questionList.add(new Question("Is python object oriented language", Arrays.asList("Nop","Yes"), 1));
            questionList.add(new Question("Can python hold many objects in one object?", Arrays.asList("yes","no"), 0));
            questionList.add(new Question("Is a string an object", Arrays.asList("No","Yes"), 1));
        } else if (activityid==24){
            questionList.add(new Question("Which is the syntax for list", Arrays.asList("names = [elements]","name[elements]"), 0));
            questionList.add(new Question("Which is the syntax for list", Arrays.asList("name = list(elements)","name = list((elements))"), 1));
            questionList.add(new Question("append() function does what to list", Arrays.asList("add elements anywhere on the list","add element at the end of the list"), 1));
            questionList.add(new Question("To know how many people are on the list i use which method?", Arrays.asList("count()","len()"), 1));
        } else if (activityid==25){
            questionList.add(new Question("Which is the syntax for tuple", Arrays.asList("names = [elements]","name=(elements)"), 1));
            questionList.add(new Question("Which is the syntax for tuple", Arrays.asList("name = list(elements)","name = tuple((elements))"), 1));
            questionList.add(new Question("Can we add new element to a tuple", Arrays.asList("Yes","No"), 1));
            questionList.add(new Question("To know how many people are on the tuple i use which method?", Arrays.asList("count()","len()"), 1));
        } else if (activityid==26){
            questionList.add(new Question("Which is the syntax for set", Arrays.asList("names = {elements}","name=(elements)"), 0));
            questionList.add(new Question("Which is the syntax for set", Arrays.asList("name = list(elements)","name = set((elements))"), 1));
            questionList.add(new Question("Can we add new element to a tuple", Arrays.asList("Yes","No"), 0));
            questionList.add(new Question("To know how many people are on the set i use which method?", Arrays.asList("count()","len()"), 1));
        } else if (activityid==27){
            questionList.add(new Question("How do you create an empty dictionary in Python?", Arrays.asList("my_dict = {}", "my_dict = []"), 0));
            questionList.add(new Question("Which method is used to get the value associated with a key in a dictionary called person?", Arrays.asList("person.get('keyname')", "person.value()"), 0));
            questionList.add(new Question("Can a dictionary key be a mutable type?", Arrays.asList("Yes", "No"), 1));
            questionList.add(new Question("How do you add a new key-value pair to an existing dictionary?", Arrays.asList("my_dict[key] = value", "my_dict.add(key, value)"), 0));
        } else if (activityid==28){
            questionList.add(new Question("A block of codes with head that perfom a specific task is called", Arrays.asList("function", "dictionary"), 0));
            questionList.add(new Question("A function in python is identified by", Arrays.asList("def", "fun"), 0));
            questionList.add(new Question("Can a function be called as many times as posible", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("Which is the correct head of a function", Arrays.asList("def my_fun();", "def my_fun():"), 1));
        } else if (activityid==29){
            questionList.add(new Question("How will we call this def myFun(n):", Arrays.asList("myFun(n)", "myFun(8)"), 1));
            questionList.add(new Question("If i want to use a block of code over and over again what do i do", Arrays.asList("i put them in a function", "i put them in a dictionary"), 0));
            questionList.add(new Question("Is indentation important in functions", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("What does the return statement do", Arrays.asList("return result and teminate execution", "stops the function from being called"), 0));
        } else if (activityid==30){
            questionList.add(new Question("What is a lambda function?", Arrays.asList("A small anonymous function", "A large named function"), 0));
            questionList.add(new Question("What is the correct syntax for a lambda function?", Arrays.asList("lambda arguments: expression", "def lambda arguments: expression"), 0));
            questionList.add(new Question("Can lambda functions take multiple arguments?", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("Lambda functions are typically used for?", Arrays.asList("Small, simple operations", "Complex, lengthy operations"), 0));
        } else if (activityid==31){
            questionList.add(new Question("What is the use of pass statement", Arrays.asList("Placeholder", "Commenting things out"), 0));
            questionList.add(new Question("Can we use pass statement in a function instead of writing codes", Arrays.asList("Gen Z", "Yes","No"), 1));
            questionList.add(new Question("Can pass be used in a class and if statement", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("I don't remember what to put inside my for loop but I want to continue coding,what will i place in the body of the for loop to avoid error?", Arrays.asList("# to be worked on", "pass"), 1));
        } else if (activityid==32){
            questionList.add(new Question("Can if statement work together with Logical operators", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("If statements is executed if the condition is", Arrays.asList("True", "False","No"), 0));
            questionList.add(new Question("Indentation is important in if..else ", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("If statement inside another if statement is called ", Arrays.asList("elif", "Nested if"), 1));
        } else if (activityid==33){
            questionList.add(new Question("I want to assign an answer that depends on some condition to a variable , which conditional statement should i use?", Arrays.asList("if...else", "if...else shorthand"), 0));
            questionList.add(new Question("How many statements does if...else statement excecute", Arrays.asList("4", "3","2"), 2));
            questionList.add(new Question("Does if...els have shorthand", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("If statement inside another if statement is called ", Arrays.asList("elif", "Nested if"), 1));
        } else if (activityid==34){
            questionList.add(new Question("What is the first value while using range() in for loop", Arrays.asList("1", "0","null"), 1));
            questionList.add(new Question("Is it possible to have nested for loop in python", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("Can i loop over a String using for loop", Arrays.asList("Yes","No"), 0));
            questionList.add(new Question("Which loop iterates through iterable objects?", Arrays.asList("while loop", "for loop"), 1));
        } else if (activityid==35){
            questionList.add(new Question("Which loop executes as the given condition or expression is True", Arrays.asList("for loop", "while loop","null"), 1));
            questionList.add(new Question("Is indentation important in while loop", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("Can i loop over a String using while loop", Arrays.asList("Yes","No"), 0));
            questionList.add(new Question("Which loop iterates through iterable objects?", Arrays.asList("while loop", "for loop"), 1));
        } else if (activityid==36){
            questionList.add(new Question("Which of the jumps the current iteration and continues on the next iteration ", Arrays.asList("for loop", "continue","break"), 1));
            questionList.add(new Question("When i want to stop looping once i meet a certain condition which statement do i use", Arrays.asList("Loop", "break","continue"), 1));
            questionList.add(new Question("Can i use cintinue on other objects like Strings", Arrays.asList("Yes","No"), 0));
            questionList.add(new Question("continue can be used on for loop can it be also be used on while loops", Arrays.asList("Yes", "No"), 0));
        } else if (activityid==37){
            questionList.add(new Question("Is class an object python", Arrays.asList("Yes", "No"), 0));
            questionList.add(new Question("Which parameter give as access to attributes and methods in a class ", Arrays.asList("self", "--init--","__in__"), 0));
            questionList.add(new Question("A function inside a class is called ", Arrays.asList("init","method"), 0));
            questionList.add(new Question("Which is the correct instance of class Details", Arrays.asList("det = new Details", "det = Details()"), 1));
        } else if (activityid==38){
            questionList.add(new Question("Taking attributes of another class is called", Arrays.asList("borrowing", "inheritance"), 1));
            questionList.add(new Question("WHere is inheritance of class A done correctily", Arrays.asList(" class A(B):", "class B(A):","class A:"), 1));
            questionList.add(new Question("Aclass that is being inherited from is called", Arrays.asList("child","parent"), 1));
            questionList.add(new Question("In python can a class inherit the methods of another class", Arrays.asList("Yes", "No"), 0));
        } else if (activityid==39){
            questionList.add(new Question("The name given to a variable created and declared inside a variable is called", Arrays.asList("Local variable", "Global variable"), 0));
            questionList.add(new Question("Is it posible to have two global scope with the same name ", Arrays.asList("Yes","No"), 1));
            questionList.add(new Question("Can we have 2 local varible  each in different functions with the same name", Arrays.asList("No","Yes"),1));
            questionList.add(new Question("Select correct converted local variable name = 'John' to global", Arrays.asList("name = 'John'", "global name = 'John'"), 1));
        } else if (activityid==40){
            questionList.add(new Question("Which brackets do we use as placeholder while formatting the string", Arrays.asList("[]", "{}"), 1));
            questionList.add(new Question("m = 'I [1] Python' Is it posible to format the string this way ", Arrays.asList("Yes","No"), 1));
            questionList.add(new Question("Is it possible to use multiple placeholder to format a string?", Arrays.asList("No","Yes"),1));
            questionList.add(new Question("What does literal String interpolation do", Arrays.asList("Allows one to place expression inside a string", "allow use to create a class in a string"), 0));
        } else if (activityid==41){
            questionList.add(new Question("I want to stop my program from crushing each time it encounters an error which statement do i use", Arrays.asList("try...except", "try...catch"), 0));
            questionList.add(new Question("Does the program continue being executed after throwing try exception error", Arrays.asList("Yes","No"), 0));
            questionList.add(new Question("To execute a block of code where or not exception was thrown which statement do i use", Arrays.asList("finally", "try","except"),0));
            questionList.add(new Question("Can we throw our own exception", Arrays.asList("Yes", "No"), 0));
        } else if (activityid==42){
            questionList.add(new Question("Which function returns an iterator from an iterable objects", Arrays.asList("infer()", "iter()"), 1));
            questionList.add(new Question("Which function is used to iterate through iterator", Arrays.asList("next","next()"), 1));
            questionList.add(new Question("Objects that can be iterated over are called ", Arrays.asList("iterable", "try"),0));
            questionList.add(new Question("It is used to iterate through iterable objects", Arrays.asList("iterator", "looper"), 0));
        } else if (activityid==43){
            questionList.add(new Question("Can you take users input in Python", Arrays.asList("YES", "NO"), 0));
            questionList.add(new Question("We use which function to take users input", Arrays.asList("input","input()"), 1));
            questionList.add(new Question("To prompt the user for input where do we write the sting", Arrays.asList("after input()", "before input()","inside input()"),2));
            questionList.add(new Question("Which statement is correct to assign the name entered by user to variable name", Arrays.asList("name = input('fname')", "name = fname.input()"), 0));
        } else if (activityid==44){
            questionList.add(new Question("Why do we use modules in Python?", Arrays.asList("To organize code", "To make code slower"), 0));
            questionList.add(new Question("Which keyword is used to import a module?", Arrays.asList("include", "import"), 1));
            questionList.add(new Question("How do you import only the sqrt function from the math module?", Arrays.asList("from math import sqrt", "import math.sqrt"), 0));
            questionList.add(new Question("Which function lists all functions and variables in a module?", Arrays.asList("list()", "dir()"), 1));
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
                ReturnActivity rt=new ReturnActivity();
                Intent bk = rt.returnINT(getApplicationContext(),activityid);

                    bk.putExtra("tto",ttt);
                    startActivity(bk);
                }
            });




        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnActivity rt=new ReturnActivity();
                Intent bk = rt.returnINT(getApplicationContext(),activityid);

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
        } else if (activityid==3) {
            savedir("wpc", correctAnswers);
        }  else if (activityid==4) {
                savedir("pdis",correctAnswers);
        }  else if (activityid==5) {
            savedir("pystate",correctAnswers);
        }  else if (activityid==6) {
            savedir("syntax",correctAnswers);
        }  else if (activityid==7) {
            savedir("comments",correctAnswers);
        }  else if (activityid==8) {
            savedir("variables",correctAnswers);
        }  else if (activityid==9) {
            savedir("pydata",correctAnswers);
        }  else if (activityid==10) {
            savedir("pynum",correctAnswers);
        }  else if (activityid==11) {
            savedir("nummed",correctAnswers);
        }  else if (activityid==12) {
            savedir("pystring",correctAnswers);
        }  else if (activityid==13) {
            savedir("pystrmed",correctAnswers);
        }  else if (activityid==14) {
            savedir("typeconv",correctAnswers);
        }  else if (activityid==15) {
            savedir("bools",correctAnswers);
        }  else if (activityid==16) {
            savedir("intopera",correctAnswers);
        }  else if (activityid==17) {
            savedir("arithoper",correctAnswers);
        }  else if (activityid==18) {
            savedir("assig",correctAnswers);
        }  else if (activityid==19) {
            savedir("comppera",correctAnswers);
        }  else if (activityid==20) {
            savedir("logop",correctAnswers);
        }  else if (activityid==21) {
            savedir("identityop",correctAnswers);
        }  else if (activityid==22) {
            savedir("membership",correctAnswers);
        }  else if (activityid==23) {
            savedir("introcon",correctAnswers);
        }  else if (activityid==24) {
            savedir("lists",correctAnswers);
        }  else if (activityid==25) {
            savedir("ctuple",correctAnswers);
        }  else if (activityid==26) {
            savedir("cset",correctAnswers);
        }  else if (activityid==27) {
            savedir("cdic",correctAnswers);
        }  else if (activityid==28) {
            savedir("intfun",correctAnswers);
        }  else if (activityid==29) {
            savedir("funs",correctAnswers);
        }  else if (activityid==30) {
            savedir("lambdas",correctAnswers);
        }  else if (activityid==31) {
            savedir("passpy",correctAnswers);
        }  else if (activityid==32) {
            savedir("if_els",correctAnswers);
        }  else if (activityid==33) {
            savedir("if_elss",correctAnswers);
        }  else if (activityid==34) {
            savedir("forl",correctAnswers);
        }  else if (activityid==35) {
            savedir("whilel",correctAnswers);
        }  else if (activityid==36) {
            savedir("brks",correctAnswers);
        }  else if (activityid==37) {
            savedir("classespy",correctAnswers);
        }  else if (activityid==38) {
            savedir("inheritance",correctAnswers);
        }  else if (activityid==39) {
            savedir("varscope",correctAnswers);
        }  else if (activityid==40) {
            savedir("formstring",correctAnswers);
        }  else if (activityid==41) {
            savedir("handerror",correctAnswers);
        }  else if (activityid==42) {
            savedir("interator",correctAnswers);
        }  else if (activityid==43) {
            savedir("inputm",correctAnswers);
        }  else if (activityid==44) {
            savedir("intromodule",correctAnswers);
        }





        if (correctAnswers == questionList.size()) {
            resultTitle = "Excellent!";
            imgmark.setImageResource(R.drawable.tick);

            resultText = "You got all the answers correct.";
        } else {
            resultTitle = "Good try!";
            imgmark.setImageResource(R.drawable.wron);

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
