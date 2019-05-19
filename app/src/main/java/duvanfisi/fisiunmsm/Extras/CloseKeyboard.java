package duvanfisi.fisiunmsm.Extras;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CloseKeyboard {
    public static InputMethodManager closeKeyboard;
    public static void closeKeyboardStart(Context context, EditText editText){
        closeKeyboard = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        closeKeyboard.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
