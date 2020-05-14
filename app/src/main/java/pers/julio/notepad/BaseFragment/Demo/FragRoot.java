package pers.julio.notepad.BaseFragment.Demo;

import android.os.Bundle;
import android.view.View;

import pers.julio.notepad.BaseFragment.Base.BaseFragment;

/**
 * ClassName:  FragRoot
 * Description: TODO
 * Author;  julio_chan  2020/5/6 16:59
 */
public class FragRoot extends BaseFragment {

    @Override
    protected Object setLayout() {
        return R.layout.frag_root;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }
}
