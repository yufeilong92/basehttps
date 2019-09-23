package com.backpacker.yflLibrary.java;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextUtil {
    public interface SearchInterface {
        void searchOnClick(String com);
    }

    public static void etActionSearch(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public static void etActionDONE(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public static void etActionSend(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
    public static void etActionGo(final EditText et, final SearchInterface anInterface) {
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    if (anInterface != null) {
                        String com = JavaStringUtil.getObjectToStr(et);
                        anInterface.searchOnClick(com);
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
