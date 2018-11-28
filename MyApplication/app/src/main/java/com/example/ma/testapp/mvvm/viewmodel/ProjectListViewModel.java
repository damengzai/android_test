package com.example.ma.testapp.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.ma.testapp.mvvm.service.model.Project;
import com.example.ma.testapp.mvvm.service.repository.ProjectRepository;

import java.util.List;

/**
 * Created by shumengma on 2018/10/31.
 */

public class ProjectListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> projectListObservable;
    public ProjectListViewModel(@NonNull Application application) {
        super(application);
        projectListObservable = ProjectRepository.getInstance().getProjectList("Google");
    }
    public LiveData<List<Project>> getProjectListObservable(){
        return projectListObservable;
    }
}
