import sys
from os.path import dirname, join
from com.chaquo.python import Python
import time

# A global variable to store input from the user
user_input = None

def main(CodeAreaData):
    global user_input

    file_dir = str(Python.getPlatform().getApplication().getFilesDir())
    filename = join(dirname(file_dir), 'file.txt')

    try:
        original_stdout = sys.stdout
        sys.stdout = open(filename, 'w', encoding='utf8', errors='ignore')

        # Replace 'input' in the script with 'request_input'
        exec(CodeAreaData.replace('input', 'request_input'))

        sys.stdout.close()
        sys.stdout = original_stdout

        output = open(filename, 'r').read()
    except Exception as e:
        sys.stdout = original_stdout
        output = e

    return str(output)
def request_input(prompt):
    global user_input

    # Get the current context (which is usually the activity context)
    context = Python.getPlatform().getContext()

    # Prepare the Intent for starting the InputActivity
    Intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName())
    Intent.setClassName(context.getPackageName(), "com.nickdieda.pythonlearn.ui.InputActivity")
    Intent.putExtra("prompt", prompt)

    # Start the activity
    context.startActivity(Intent)

    # Wait for the user input
    while user_input is None:
        time.sleep(0.1)

    # Return the received input
    input_value = user_input
    user_input = None  # Reset for next use
    return input_value


def handle_input(input_value):
    global user_input
    user_input = input_value
