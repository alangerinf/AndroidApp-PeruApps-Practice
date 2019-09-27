package com.alanger.peruappstest.postDetail.presenter;

import com.alanger.peruappstest.models.VO.ComentVO;
import com.alanger.peruappstest.models.VO.PostVO;

public interface PostDetailPresenter {
    void trySave(PostVO postVO);//Interactor
    void tryNotSave(PostVO postVO);//Interactor
    void tryAddComent(ComentVO coment);
    void tryAlterComent(ComentVO coment);
    void tryDeleteComent(int idComent);
    void showDataChanged(PostVO data);
    void showError(String error);

}
