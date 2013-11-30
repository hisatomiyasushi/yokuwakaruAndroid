package jp.android.booksample.firstapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * ビューをタップしたときに表示されるダイアログを表すフラグメントです。
 */
public class TouchDialog extends DialogFragment {
    
    /**
     * ダイアログを生成します
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // AlertDialogのビルダーを使用
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.wording_clickcircle);
        builder.setPositiveButton(android.R.string.ok, null);
        return builder.create();
    }

}
