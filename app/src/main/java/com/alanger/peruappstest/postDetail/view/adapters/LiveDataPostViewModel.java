package com.alanger.peruappstest.postDetail.view.adapters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alanger.peruappstest.models.VO.ComentVO;
import com.alanger.peruappstest.models.VO.PostVO;

public class LiveDataPostViewModel extends  ViewModel {

    private MutableLiveData<PostVO>  postLiveData = new MutableLiveData<PostVO>();
    private PostVO postVO;

    public LiveData<PostVO> getPost() {
        if(postLiveData==null){
            postLiveData = new MutableLiveData<PostVO>();
            postVO = new PostVO();
        }
        return this.postLiveData;
    }

    public void addComend(ComentVO comentVO) {
        this.postVO.getComentVOList().add(comentVO);
        postLiveData.setValue(this.postVO);
    }

    public void removeComent(ComentVO comentVO) {
        this.postVO.getComentVOList().remove(comentVO);
        postLiveData.setValue(this.postVO);
    }

    public void setPost(PostVO post) {
        this.postVO = post;
        postLiveData.setValue(this.postVO);
    }


    public void addComent(ComentVO comentVO) {
        this.postVO.getComentVOList().add(comentVO);
        postLiveData.setValue(this.postVO);
    }


    public PostVO getPostVO() {
        return postVO;

    }
}

