package com.alanger.peruappstest.postDetail.presenter;


import android.content.Context;
import android.util.Log;

import com.alanger.peruappstest.models.VO.ComentVO;
import com.alanger.peruappstest.models.VO.PostVO;
import com.alanger.peruappstest.postDetail.interactor.PostDetailInteractor;
import com.alanger.peruappstest.postDetail.interactor.PostDetailInteractorImpl;
import com.alanger.peruappstest.postDetail.view.PostDetailActivity;


public class PostDetailPresenterImpl implements PostDetailPresenter {

    private static String TAG = PostDetailPresenterImpl.class.getSimpleName();

    private PostDetailActivity view;
    private PostDetailInteractor interactor;
    private Context ctx;
    public PostDetailPresenterImpl(PostDetailActivity view) {
        this.view = view;
        this.ctx = (Context) view;
        interactor = new PostDetailInteractorImpl(ctx,this);
    }


    @Override
    public void trySave(PostVO postVO) {

        Log.d(TAG,"trySave");
        view.disableInputs();
        interactor.trySave(postVO);
    }

    @Override
    public void tryNotSave(PostVO postVO) {
        view.disableInputs();
        interactor.tryNotSave(postVO);
    }



    @Override
    public void tryAddComent(ComentVO coment) {
        view.disableInputs();
        interactor.tryAddComent(coment);
    }

    @Override
    public void tryAlterComent(ComentVO coment) {
        view.disableInputs();
        interactor.tryAlterComent(coment);
    }

    @Override
    public void tryDeleteComent(int idComent) {

    }

    @Override
    public void showDataChanged(PostVO data) {
        Log.d(TAG,""+data.toString());
        view.enableInputs();
        view.showDataChanged(data);
    }

    @Override
    public void showError(String error) {
        view.enableInputs();
        view.showError(error);
    }
}
