package com.example.ma.testapp.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.ma.testapp.mvvm.service.model.Project;
import com.example.ma.testapp.mvvm.service.repository.ProjectRepository;

public class ProjectViewModel extends AndroidViewModel {
    private final LiveData<Project> projectObservable;
    private final String projectID;

    public ObservableField<Project> project = new ObservableField<>();

    public ProjectViewModel(@NonNull Application application, String projectID) {
        super(application);
        this.projectID = projectID;
        projectObservable = ProjectRepository.getInstance().getProjectDetails("Google", projectID);
    }

    public LiveData<Project> getProjectObservable() {
        return projectObservable;
    }
    public void setProject(Project project){
        this.project.set(project);
    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;
        private final String projectID;
        public Factory(@NonNull Application application, String projectID){
            this.application = application;
            this.projectID = projectID;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProjectViewModel(application, projectID);
        }
    }
}
