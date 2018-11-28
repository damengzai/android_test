package com.example.ma.testapp.mvvm.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ma.testapp.R;
import com.example.ma.testapp.databinding.FragmentProjectListBinding;
import com.example.ma.testapp.mvvm.service.model.Project;
import com.example.ma.testapp.mvvm.view.adapter.ProjectAdapter;
import com.example.ma.testapp.mvvm.view.callback.ProjectClickCallback;
import com.example.ma.testapp.mvvm.viewmodel.ProjectListViewModel;

import java.util.List;

/**
 * Created by shumengma on 2018/11/1.
 */

public class ProjectListFragment extends Fragment {
    public static final String TAG = "ProjectListFragment";
    private ProjectAdapter projectAdapter;
    private FragmentProjectListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);
        projectAdapter = new ProjectAdapter(projectClickCallback);
        binding.projectList.setAdapter(projectAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProjectListViewModel viewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        observeViewModel(viewModel);
    }
    private void observeViewModel(ProjectListViewModel viewModel){
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null){
                    binding.setIsLoading(false);
                    projectAdapter.setProjectList(projects);
                }
            }
        });
    }

    private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MVVMActivity)getActivity()).show(project);
            }
        }
    };
}
