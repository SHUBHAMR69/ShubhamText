/*
 * The given source code is for learning purposes only.
 * You're not allowed to compile and publish this telling that you've made the extension.
 * Copyright - SHUBHAMR69 (Shubham Ray)
 */

/*
 * This file is not same as the original extension source code.
 * The reason is, I can't directly Copy-Paste every content from Niotron IDE at the present moment. 
 * As I'm using an Android device to write the extension.
 * So I had to decompile my own extension here. 😅
 * But that doesn't matter.
 * As this file is for learning purposes only, it contains everything you need to "learn" from this.
 */

package com.shubhamr69.text;

import android.app.Activity;

import android.content.Context;

import android.graphics.Color;
import android.graphics.Typeface;

import android.text.SpannableStringBuilder;
import android.text.Spanned;

import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;

import android.widget.Button;
import android.widget.TextView;

import com.google.appinventor.components.annotations.*;

import com.google.appinventor.components.common.ComponentCategory;

import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ButtonBase;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.TextBox;


@DesignerComponent(category = ComponentCategory.EXTENSION, description = "An extension to change the style of text in some components. Made by SHUBHAMR69.\nSupported Components -> Labels, TextBoxes, CheckBoxes and Button-Based Components", iconName = "https://firebasestorage.googleapis.com/v0/b/shubhamr69firebase.appspot.com/o/Shubham_RDP_18%C3%9718.png?alt=media&token=586caaa9-9161-4404-b442-209740cafff7", nonVisible = true, version = 21, versionName = "1.0.0 release")
@UsesLibraries(libraries = "")
@SimpleObject(external = true)
@UsesPermissions(permissionNames = "")
public class ShubhamText extends AndroidNonvisibleComponent {
    private Activity activity;
    private Context context;
    private Object obj;
    private SpannableStringBuilder ssb = null;
    private CharSequence text;

    public ShubhamText(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        this.activity = componentContainer.$context();
        this.context = componentContainer.$context();
    }

    private void invalid() {
        InvalidComponent((AndroidViewComponent) this.obj);
    }

    private String objectToString(SpannableStringBuilder spannableStringBuilder) {
        String str = "";
        for (int i = 0; i < spannableStringBuilder.length(); i++) str += spannableStringBuilder.charAt(i);
        return str;
    }

    private String objectToString(CharSequence charSequence) {
        String str = "";
        for (int i = 0; i < charSequence.length(); i++) str += charSequence.charAt(i);
        return str;
    }

    private boolean resetObject(AndroidViewComponent androidViewComponent) {
        if (androidViewComponent instanceof Label) {
            TextView textView = (TextView) ((Label) androidViewComponent).getView();
            textView.setText(objectToString(textView.getText()));
        }
        else if (androidViewComponent instanceof ButtonBase) {
            Button button = (Button) ((ButtonBase) androidViewComponent).getView();
            button.setText(objectToString(button.getText()));
        }
        else if (androidViewComponent instanceof CheckBox) {
            android.widget.CheckBox checkBox = (android.widget.CheckBox) ((CheckBox) androidViewComponent).getView();
            checkBox.setText(objectToString(checkBox.getText()));
        }
        else if (androidViewComponent instanceof TextBox) {
            TextView textview = (TextView) ((TextBox)androidViewComponent).getView();
            textview.setText(objectToString(textview.getText()));
        }
        else return false;
        return true;
    }

    private boolean setObject() {
        if (this.obj instanceof Label) {
            TextView textView = (TextView) ((Label) this.obj).getView();
            this.text = textView.getText();
            this.obj = textView;
            return true;
        }
        else if (this.obj instanceof ButtonBase) {
            Button button = (Button) ((ButtonBase) this.obj).getView();
            this.text = button.getText();
            this.obj = button;
            return true;
        }
        else if (this.obj instanceof CheckBox) {
            android.widget.CheckBox checkBox = (android.widget.CheckBox) ((CheckBox) this.obj).getView();
            this.text = checkBox.getText();
            this.obj = checkBox;
            return true;
        }
        else if (this.obj instanceof TextBox) {
            TextView textView = (TextView) ((TextBox) this.obj).getView();
            this.text = textView.getText();
            this.obj = textView;
            return true;
        }
        else return false;
    }

    private void setSpanText(Object obj, int fromIndex, int toIndex, int flag) {
        this.ssb = new SpannableStringBuilder(this.text);
        this.ssb.setSpan(obj, fromIndex - 1, toIndex, flag);
        setText();
    }

    private void setText() {
        if (this.obj instanceof TextView) ((TextView) this.obj).setText(this.ssb);
        else if (this.obj instanceof Button) ((Button) this.obj).setText(this.ssb);
        else if (this.obj instanceof android.widget.CheckBox) ((android.widget.CheckBox) this.obj).setText(this.ssb);
    }

    @SimpleEvent(description = "Event raised when the given component is not supported.")
    public void InvalidComponent(AndroidViewComponent androidViewComponent) {
        EventDispatcher.dispatchEvent(this, "InvalidComponent", androidViewComponent);
    }

    @SimpleFunction(description = "Resets the given component's text.")
    public void Reset(AndroidViewComponent androidViewComponent) {
        if (!resetObject(androidViewComponent)) {
            InvalidComponent(androidViewComponent);
        }
    }

    @SimpleFunction(description = "Resets the given part from Bold and/or Italic to default.")
    public void ResetBoldItalic(Object component, int fromIndex, int toIndex) {
        this.obj = component;
        if (setObject()) setSpanText(new StyleSpan(Typeface.NORMAL), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets the html background color for the given component's mentioned text segment.")
    public void SetBackgroundHtmlColor(Object component, int fromIndex, int toIndex, String htmlColor) {
        this.obj = component;
        if (setObject()) setSpanText(new BackgroundColorSpan(Color.parseColor(htmlColor)), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets font-bold for the given component's mentioned text segment.")
    public void SetBold(Object component, int fromIndex, int toIndex) {
        this.obj = component;
        if (setObject()) setSpanText(new StyleSpan(Typeface.BOLD), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets font-bold-italic for the given component's mentioned text segment.")
    public void SetBoldItalic(Object component, int fromIndex, int toIndex) {
        this.obj = component;
        if (setObject()) setSpanText(new StyleSpan(Typeface.BOLD_ITALIC), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets font-italic for the given component's mentioned text segment.")
    public void SetItalic(Object component, int fromIndex, int toIndex) {
        this.obj = component;
        if (setObject()) setSpanText(new StyleSpan(Typeface.ITALIC), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets the text style with Absolute Size. Other features same as the <mark>SetStyleRelative</mark> method.")
    public void SetSizeAbsolute(Object component, int fromIndex, int toIndex, int absoluteSize) {
        this.obj = component;
        if (setObject()) setSpanText(new AbsoluteSizeSpan(absoluteSize), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets the relative text size for the given part of the text.")
    public void SetSizeRelative(Object component, int fromIndex, int toIndex, float relativeSize) {
        this.obj = component;
        if (setObject()) setSpanText(new RelativeSizeSpan(relativeSize), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets the html text color of the given component's mentioned text segment.")
    public void SetTextHtmlColor(Object component, int fromIndex, int toIndex, String htmlColor) {
        this.obj = component;
        if (setObject()) setSpanText(new ForegroundColorSpan(Color.parseColor(htmlColor)), fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }

    @SimpleFunction(description = "Sets the font typeface for the given component's mentioned text segment.")
    public void SetTypeface(Object component, int fromIndex, int toIndex, String typeface) {
        this.obj = component;
        if (setObject()) setSpanText(new TypefaceSpan(typeface),fromIndex, toIndex, Spanned.SPAN_INTERMEDIATE);
        else invalid();
    }
}
