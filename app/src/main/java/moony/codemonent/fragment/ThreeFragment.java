package moony.codemonent.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moony.codemonent.R;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<新闻frag>
 */
public class ThreeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        return view;
    }
}
