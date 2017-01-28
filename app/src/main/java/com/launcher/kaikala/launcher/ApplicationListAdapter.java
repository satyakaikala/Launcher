package com.launcher.kaikala.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by skai0001 on 1/27/17.
 */

public class ApplicationListAdapter extends RecyclerView.Adapter<ApplicationListAdapter.ApplicationListHolder> {
    private Context context;
    PackageManager packageManager;
    private final List<ResolveInfo> applications;

    public ApplicationListAdapter(Context ctx, List<ResolveInfo> applications) {
        this.applications = applications;
        this.context = ctx;
        packageManager = context.getPackageManager();
    }

    @Override
    public ApplicationListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_view, parent, false);
        return new ApplicationListHolder(view, packageManager);
    }

    @Override
    public void onBindViewHolder(ApplicationListHolder holder, int position) {
        holder.bindActivity(applications.get(position));
    }


    @Override
    public int getItemCount() {
        return applications.size();
    }


    class ApplicationListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ResolveInfo resolveInfo;
        private TextView appName;
        private TextView packageName;
        private ImageView icon;
        private PackageManager packageManager;

        public ApplicationListHolder(View itemView, PackageManager pm) {
            super(itemView);
            this.packageManager = pm;
            appName = (TextView) itemView.findViewById(R.id.name);
            packageName = (TextView) itemView.findViewById(R.id.pName);
            icon = (ImageView) itemView.findViewById(R.id.icon);

            packageName.setOnClickListener(this);
            icon.setOnClickListener(this);
            appName.setOnClickListener(this);
        }

        public void bindActivity(ResolveInfo info) {
            this.resolveInfo = info;
            String appName = resolveInfo.loadLabel(packageManager).toString();
            String packageName = resolveInfo.activityInfo.packageName;
            Drawable icon = resolveInfo.loadIcon(packageManager);
            this.packageName.setText(packageName);
            this.icon.setImageDrawable(icon);
            this.appName.setText(appName);
        }

        @Override
        public void onClick(View v) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            Intent i = new Intent(Intent.ACTION_MAIN).setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);

        }
    }
}