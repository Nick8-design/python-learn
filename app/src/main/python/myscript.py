import sys
from os.path import dirname, join
from com.chaquo.python import Python
import time  # To wait for the input

# A global variable to store input from the user
user_input = None

def main(CodeAreaData):
    global user_input

    file_dir = str(Python.getPlatform().getApplication().getFilesDir())
    filename = join(dirname(file_dir), 'file.txt')

    try:
        original_stdout = sys.stdout
        sys.stdout = open(filename, 'w', encoding='utf8', errors='ignore')

        # Execute the provided script
        exec(CodeAreaData)

        sys.stdout.close()
        sys.stdout = original_stdout

        output = open(filename, 'r').read()
    except Exception as e:
        sys.stdout = original_stdout
        output = e

    return str(output)

def request_input(prompt):
    global user_input

    # Start the InputActivity with the given prompt
    AndroidActivity = Python.getPlatform().getActivity()
    Intent = AndroidActivity.getIntent()
    Intent.setClassName(AndroidActivity.getPackageName(), "com.nickdieda.pythonlearn.ui")
    Intent.putExtra("prompt", prompt)

    AndroidActivity.startActivityForResult(Intent, 1)

    # Wait for the user input (this is a simple approach, you may need a more sophisticated mechanism)
    while user_input is None:
        time.sleep(0.1)

    # Return the received input
    input_value = user_input
    user_input = None  # Reset for next use
    return input_value

def handle_input(input_value):
    global user_input
    user_input = input_value