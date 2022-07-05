package com.shubhamr69.text;

import android.app.Activity;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Typeface;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.*;

import android.widget.Button;
import android.widget.TextView;

import com.google.appinventor.components.annotations.*;

import com.google.appinventor.components.common.ComponentCategory;

import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.ButtonBase;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TextBoxBase;

import java.io.File;

@DesignerComponent(
        version = 24,
        description  =  "An extension to change the style of text in some components. Made by @SHUBHAMR69.\n"+
                        "Supported Components -> Labels, TextBoxes, CheckBoxes and Button-Based Components.",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName =  "https://firebasestorage.googleapis.com/v0/b/shubhamr69firebase.appspot.com/o"+
                    "/Shubham_RDP_18%C3%9718.png?alt=media&token=586caaa9-9161-4404-b442-209740cafff7",
        versionName = "2.0.0 release"
)

@SimpleObject(external = true)
@UsesLibraries(libraries = "")
@UsesPermissions(permissionNames = "")

public class ShubhamText extends AndroidNonvisibleComponent {

    private Context context;
    private Activity activity;


    private CharSequence text;
    private Object obj;
    private SpannableStringBuilder ssb = null;


    public ShubhamText(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleFunction (description="Converts a color to html format.")
    public String ToHtmlColor(int color){
        return ("#"+Integer.toHexString(color).substring(2));
    }

    @SimpleFunction (description  =  "Sets the relative text size for the given part of the text.")
    public void SetSizeRelative(Object component, int fromIndex, int toIndex, float relativeSize){
        if (setObject(component)) setSpanText(new RelativeSizeSpan(relativeSize), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets the text style with Absolute Size. Other features same as the <mark>SetStyleRelative</mark> method.")
    public void SetSizeAbsolute(Object component, int fromIndex, int toIndex, int absoluteSize){
        if (setObject(component)) setSpanText(new AbsoluteSizeSpan(absoluteSize), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets the html text color of the given component's mentioned text segment.")
    public void SetTextHtmlColor(Object component, int fromIndex, int toIndex, String htmlColor){
        if (setObject(component)) setSpanText(new ForegroundColorSpan(Color.parseColor(htmlColor)), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets the html background color for the given component's mentioned text segment.")
    public void SetBackgroundHtmlColor(Object component, int fromIndex, int toIndex, String htmlColor){
        if (setObject(component)) setSpanText(new BackgroundColorSpan(Color.parseColor(htmlColor)), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Resets the given part from Bold and/or Italic to default.")
    public void ResetBoldItalic(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new StyleSpan(0), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets font-bold for the given component's mentioned text segment.")
    public void SetBold(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new StyleSpan(1), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets font-italic for the given component's mentioned text segment.")
    public void SetItalic(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new StyleSpan(2), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets font-bold-italic for the given component's mentioned text segment.")
    public void SetBoldItalic(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new StyleSpan(3), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets the font typeface for the given component's mentioned text segment.")
    public void SetTypeface(Object component, int fromIndex, int toIndex, String typeface){
        if (setObject(component)) setSpanText(new TypefaceSpan(typeface), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets subscript text for the given component's mentioned text segment.")
    public void SetSubscript(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new SubscriptSpan(), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets subscript text for the given component's mentioned text segment.")
    public void SetSuperscript(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new SuperscriptSpan(), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets underline style for the given component's mentioned text segment.")
    public void SetUnderline(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new UnderlineSpan(), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description="Sets strikethrough style for the given component's mentioned text segment.")
    public void SetStrikethrough(Object component, int fromIndex, int toIndex){
        if (setObject(component)) setSpanText(new StrikethroughSpan(), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction (description =  "Sets font from the specified font file.\n\n"+
                                    "Raises the FileNotFound event with the value of @param note = \"Font\" if the mentioned file is not found.")
    public void SetFont(Object component, int fromIndex, int toIndex, String filePath){
        if (setObject(component))
            if (new File(filePath).exists()) setSpanText(new TypefaceSpan(Typeface.createFromFile(filePath)), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
            else FileNotFound((AndroidViewComponent)component, filePath, "Font");
        else invalid();
    }

    @SimpleFunction (description =  "Sets font from the specified font file from Asset.")
    public void SetFontFromAsset(Object component, int fromIndex, int toIndex, String fileName){
        if (setObject(component)) setSpanText(new TypefaceSpan(Typeface.createFromAsset(context.getAssets(), fileName)), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    private void setSpanText(Object span, int fromIndex, int toIndex, int flag){
        fromIndex--;
        ssb = new SpannableStringBuilder(text);
        ssb.setSpan(span, fromIndex, toIndex, flag);
        setText();
    }



    private void invalid(){
        InvalidComponent((AndroidViewComponent)obj);
    }

    private boolean setObject(Object obj){
        this.obj = obj;
        if (obj instanceof Label){
            TextView textview = (TextView) ((Label)obj).getView();
            this.text = textview.getText();
            this.obj = textview;
        }
        else if (obj instanceof ButtonBase){
            Button button = (Button) ((ButtonBase)obj).getView();
            this.text = button.getText();
            this.obj = button;
        }
        else if (obj instanceof CheckBox){
            android.widget.CheckBox checkbox = (android.widget.CheckBox) ((CheckBox)obj).getView();
            this.text = checkbox.getText();
            this.obj = checkbox;
        }
        else if (obj instanceof TextBoxBase){
            TextView textview = (TextView) ((TextBoxBase)obj).getView();
            this.text = textview.getText();
            this.obj = textview;
        }
        else return false;
        return true;
    }

    private boolean resetObject(AndroidViewComponent component){
        boolean acceptable = true;
        if (component instanceof Label){
            TextView textview = (TextView) ((Label)component).getView();
            textview.setText((CharSequence)objectToString(textview.getText()));
        }
        else if (component instanceof ButtonBase){
            Button button = (Button) ((ButtonBase)component).getView();
            button.setText((CharSequence)objectToString(button.getText()));
        }
        else if (component instanceof CheckBox){
            android.widget.CheckBox checkbox = (android.widget.CheckBox) ((CheckBox)component).getView();
            checkbox.setText((CharSequence)objectToString(checkbox.getText()));
        }
        else if (component instanceof TextBoxBase){
            TextView textview = (TextView) ((TextBoxBase)component).getView();
            textview.setText((CharSequence)objectToString(textview.getText()));
        }
        else acceptable = false;
        return acceptable;
    }

    private void setText(){
        if (obj instanceof TextView) ((TextView)obj).setText(ssb);
        else if (obj instanceof Button) ((Button)obj).setText(ssb);
        else if (obj instanceof android.widget.CheckBox) ((android.widget.CheckBox)obj).setText(ssb);
        else return;
    }


    @SimpleEvent (description = "Event raised when the given component is not supported.")
    public void InvalidComponent(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this, "InvalidComponent", component);
    }

    @SimpleEvent (description="Event raised when the mentioned file doesn't exist.")
    public void FileNotFound(AndroidViewComponent component, String filePath, String note){
        EventDispatcher.dispatchEvent(this, "FileNotFound", component, filePath, note);
    }

    @SimpleFunction (description = "Resets the given component's text.")
    public void Reset(AndroidViewComponent component){
        if (!resetObject(component)) InvalidComponent(component);
    }

    private String objectToString(SpannableStringBuilder ssb){
        String ans = "";
        for (int index = 0; index < ssb.length(); index++) ans += ssb.charAt(index);
        return ans;
    }

    private String objectToString(CharSequence string){
        String ans = "";
        for (int index = 0; index < string.length(); index++) ans += string.charAt(index);
        return ans;
    }


}
