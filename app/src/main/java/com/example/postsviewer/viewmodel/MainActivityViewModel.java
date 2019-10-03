package com.example.postsviewer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.util.Log;

import com.example.postsviewer.model.abstracts.Post;
import com.example.postsviewer.repository.PostRepository;
import com.example.postsviewer.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private PostRepository repository;
    private MutableLiveData<List<Post>>fullLiveData;
    private MutableLiveData<List<Post>>filteredLiveData;
    private CompositeDisposable compositeDisposable;
    private Observable<Post>filterObservable;
    private List<Post>fullList;
    private List<Post>filteredList;

    public MainActivityViewModel() {

        repository = new PostRepository();
        fullList = new ArrayList<>();
        filteredList = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<List<Post>>getPosts(){

        fullLiveData = repository.getPostLiveData();
        return fullLiveData;
    }


    public MutableLiveData<List<Post>>getFilteredPosts(){

        filteredLiveData = new MutableLiveData<>();
        return filteredLiveData;
    }

    public void setFullList(List<Post>posts){

        this.fullList = Utils.getCopyList(posts);
    }


    public void filterContacts(final String pattern){

        if (!TextUtils.isEmpty(pattern)) {

            filteredList.clear();
            filterObservable = Observable.fromIterable(Utils.getCopyList(fullList));
            compositeDisposable.add(filterObservable
                    .filter(new Predicate<Post>() {
                        @Override
                        public boolean test(Post post) throws Exception {

                            return post.apply(pattern);
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()
                    ).subscribeWith(new DisposableObserver<Post>() {

                        @Override
                        public void onNext(Post post) {

                            filteredList.add(post);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                            filteredLiveData.postValue(Utils.getCopyList(filteredList));
                        }
                    }));
        } else {



            fullLiveData.postValue(Utils.getCopyList(fullList));
        }

    }



    public void dispose(){

        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
