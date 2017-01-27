package com.launcher.kaikala.launcher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LauncherFragment extends Fragment {

    private static final String LOG_TAG = LauncherFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private static PackageManager packageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static LauncherFragment getInstance() {
        return new LauncherFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.launcher_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_launcher)
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setUpAdapter();
        return view;
    }

    private void setUpAdapter() {
        Intent launcher = new Intent(Intent.ACTION_MAIN);
        launcher.addCategory(Intent.CATEGORY_LAUNCHER);
        packageManager = getActivity().getPackageManager();

        List<ResolveInfo> applications = packageManager.queryIntentActivities(launcher, 0);

        Log.d(LOG_TAG, "Applications found :" + applications.size());

        Comparator<ResolveInfo> comparator = new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo lhs, ResolveInfo rhs) {
                return String.CASE_INSENSITIVE_ORDER.compare(lhs.loadLabel(packageManager).toString(), rhs.loadLabel(packageManager).toString());
            }
        };

        Collections.sort(applications, comparator);

        recyclerView.setAdapter(new ApplicationListAdapter(getActivity(), applications));
    }
}
