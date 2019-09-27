package com.alanger.peruappstest.postDetail.interactor;


import com.alanger.peruappstest.models.VO.ComentVO;
import com.alanger.peruappstest.models.VO.PostVO;

public interface PostDetailInteractor {

    void tryNotSave(PostVO postVO);

    void trySave(PostVO postVO);

    void tryAlterComent(ComentVO comentVO);

    void tryAddComent(ComentVO coment);
}
