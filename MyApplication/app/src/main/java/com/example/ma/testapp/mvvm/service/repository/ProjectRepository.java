package com.example.ma.testapp.mvvm.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.ma.testapp.mvvm.service.model.Project;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shumengma on 2018/10/31.
 */

public class ProjectRepository {
    private GithubService githubService;
    private static ProjectRepository projectRepository;

    private ProjectRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubService = retrofit.create(GithubService.class);
    }

    public synchronized static ProjectRepository getInstance(){
        if (projectRepository == null){
            if (projectRepository == null){
                projectRepository = new ProjectRepository();
            }
        }
        return projectRepository;
    }

//    public ProjectRepository(GithubService service){
//        this.githubService = service;
//    }

    public LiveData<List<Project>> getProjectList(String userId){
        //将数据包装到livedata中
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();
        //调用接口，返回数据
        githubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
    public LiveData<Project> getProjectDetails(String userID, String projectName) {
        final MutableLiveData<Project> data = new MutableLiveData<>();
        githubService.getProjectDetails(userID, projectName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                simulateDelay();
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
