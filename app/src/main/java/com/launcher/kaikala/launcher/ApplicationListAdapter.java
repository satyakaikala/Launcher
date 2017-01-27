package com.launcher.kaikala.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by skai0001 on 1/27/17.
 */

public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListHolder> {
    private Context context;
    private final List<ResolveInfo> applications;

    public ApplicationListAdapter(Context ctx, List<ResolveInfo> applications) {
        this.applications = applications;
        this.context = ctx;
    }

    @Override
    public ApplicationListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ApplicationListHolder(view);
    }

    @Override
    public void onBindViewHolder(ApplicationListHolder holder, int position) {
        holder.bindActivity(applications.get(position));
    }


    @Override
    public int getItemCount() {
        return applications.size();
    }

}

class ApplicationListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ResolveInfo resolveInfo;
    private TextView textView;
    private PackageManager pm;

    public ApplicationListHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
        textView.setOnClickListener(this);
    }

    public void bindActivity(ResolveInfo info) {
        this.resolveInfo = info;
        String appName = resolveInfo.loadLabel(pm).toString();
        textView.setText(appName);
    }

    @Override
    public void onClick(View v) {
        ActivityInfo activityInfo = resolveInfo.activityInfo;

        Intent i = new Intent(Intent.ACTION_MAIN).setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);

    }
}
