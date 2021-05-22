package common;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ValidationEditText {

    public ValidationEditText(){

    };

    public void validateEditText(EditText idEditText , TextView Error){
        if (idEditText.length() == 0)
        {
            Error.setVisibility(View.VISIBLE);
        }
        else
        {
            Error.setVisibility(View.INVISIBLE);
        }
    }

    public void validateTextView(TextView idTextView , TextView Error){
        if(idTextView.length() == 0)
        {
            Error.setVisibility(View.VISIBLE);
        }
        else   {
            Error.setVisibility(View.INVISIBLE);
        }
    }
}
