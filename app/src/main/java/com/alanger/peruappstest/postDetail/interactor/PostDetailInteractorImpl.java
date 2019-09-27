package com.alanger.peruappstest.postDetail.interactor;


import android.content.Context;
import android.util.Log;

import com.alanger.peruappstest.models.DAO.ComentDAO;
import com.alanger.peruappstest.models.DAO.PostDAO;
import com.alanger.peruappstest.models.VO.ComentVO;
import com.alanger.peruappstest.models.VO.PostVO;
import com.alanger.peruappstest.postDetail.presenter.PostDetailPresenter;


public class PostDetailInteractorImpl implements PostDetailInteractor {

    private Context ctx;
    private PostDetailPresenter presenter;

    String TAG = PostDetailInteractorImpl.class.getSimpleName();

    public PostDetailInteractorImpl(Context ctx, PostDetailPresenter presenter) {
        this.presenter = presenter;
        this.ctx = ctx;

    }



    @Override
    public void tryNotSave(PostVO postVO) {
        Log.d(TAG,"tryNotSave");
        PostDAO postDAO = new PostDAO(ctx);
        PostVO temp = postDAO.selectById(postVO.getId());
        if(temp==null){

            Log.d(TAG,"Error: Post no guardado");
            presenter.showError("Error: Post no guardado");

        }else {
            Log.d(TAG,"borrado");
            postDAO.delete(temp);
            temp.setSaved(false);
            presenter.showDataChanged(temp);
        }


    }


    @Override
    public void trySave(PostVO postVO) {
        Log.d(TAG,"trySave");
        PostDAO postDAO = new PostDAO(ctx);
        PostVO temp = postDAO.selectById(postVO.getId());
        if(temp==null){
            long id = postDAO.insert(postVO);
            if(id>0){
                temp = postDAO.selectById(postVO.getId());
                presenter.showDataChanged(temp);
                Log.d(TAG,"id>0");


            }else {

                Log.d(TAG,"No se pudo guardar correctamente");
                presenter.showError("No se pudo guardar correctamente");
            }
        }else {

            temp = postDAO.selectById(postVO.getId());
            presenter.showDataChanged(temp);
        }
    }


    @Override
    public void tryAlterComent(ComentVO comentVO) {
        ComentDAO comentDAO = new ComentDAO(ctx);
        if(comentVO.getIdPost()==0){
            Log.d(TAG,"tryAlterComent:"+"idPost = 0, se requiere parametro para actualizar la vista");
            presenter.showError("idPost = 0, se requiere parametro para actualizar la vista");
            return;
        }
        if(comentDAO.update(comentVO)>0){

            presenter.showDataChanged(new PostDAO(ctx).selectById(comentVO.getIdPost()));
        }else{
            presenter.showError("No se pudo insertar");
        }
    }

    @Override
    public void tryAddComent(ComentVO coment) {
        if(coment.getIdPost()==0){
            Log.d(TAG,"tryAlterComent:"+"idPost = 0, se requiere parametro para actualizar la vista");
            presenter.showError("idPost = 0, se requiere parametro para actualizar la vista");
            return;
        }
        ComentDAO comentDAO = new ComentDAO(ctx);
        if(comentDAO.insert(coment)>0){
            presenter.showDataChanged(new PostDAO(ctx).selectById(coment.getIdPost()));
        }else{
            presenter.showError("No se pudo insertar");
        }

    }


}
