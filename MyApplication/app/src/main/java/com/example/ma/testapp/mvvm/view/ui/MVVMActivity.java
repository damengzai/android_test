package com.example.ma.testapp.mvvm.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ma.testapp.R;
import com.example.ma.testapp.mvvm.service.model.Project;

import butterknife.ButterKnife;

/**
 * Created by shumengma on 2018/10/31.
 */

public class MVVMActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_layout);
        if (savedInstanceState == null){
            ProjectListFragment fragment = new ProjectListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit();
        }
    }
    public void show(Project project){
        ProjectFragment projectFragment = ProjectFragment.forProject(project.name);

        getSupportFragmentManager().beginTransaction().addToBackStack("project").replace(R.id.fragment_container, projectFragment, null).commit();
    }
}
