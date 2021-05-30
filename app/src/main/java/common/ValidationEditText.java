package common;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

public class ValidationEditText {
    private View view;
    private  TextView textView;
    private boolean check;
    public ValidationEditText(){
    };
    public boolean validateEditText(EditText idEditText , TextView Error){
        if (idEditText.getText().toString().isEmpty())
        {
            Error.setVisibility(View.VISIBLE);
            return false;
        }
        else   {
            Error.setVisibility(View.GONE);
        }

        return true;
    }

    public boolean validateTextView(TextView idTextView , TextView Error){
        if(idTextView.length() == 0)
        {
            Error.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            Error.setVisibility(View.GONE);
        }
        return true;
    }
}
