package com.alanger.peruappstest.postDetail.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.alanger.peruappstest.R;
import com.alanger.peruappstest.main.view.adapters.LiveDataFavoritesListViewModel;
import com.alanger.peruappstest.models.VO.ComentVO;
import com.alanger.peruappstest.models.VO.PostVO;
import com.alanger.peruappstest.postDetail.presenter.PostDetailPresenter;
import com.alanger.peruappstest.postDetail.presenter.PostDetailPresenterImpl;
import com.alanger.peruappstest.postDetail.view.adapters.LiveDataPostViewModel;
import com.alanger.peruappstest.postDetail.view.adapters.RViewAdapterComentsList;

public class PostDetailActivity extends AppCompatActivity {

    public static final String EXTRA_POST="EXTRA_POST";

    private static final String TAG= PostDetailActivity.class.getSimpleName();


    private LiveDataPostViewModel liveDataPostViewModel;
    private LiveDataFavoritesListViewModel liveDataFavoritesListViewModel;


    private PostDetailPresenter presenter;

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        declaration();

    }


    TextView tViewTittle;
    TextView tViewBody;
    TextView tViewWoComents;
    FloatingActionButton fabSave;
    FloatingActionButton fabAddComent;
    ConstraintLayout clContentComents, clModalProgress;
    RecyclerView rViewComents;
    RViewAdapterComentsList rViewAdapterComentsList;

    private void declaration() {
        ctx = this;
        setTitle("Detalle de Post");
        presenter = new PostDetailPresenterImpl(this);
        liveDataPostViewModel = ViewModelProviders.of(this).get(LiveDataPostViewModel.class);
        liveDataFavoritesListViewModel  = ViewModelProviders.of(this).get(LiveDataFavoritesListViewModel.class);
        //set livedata
        Bundle b = getIntent().getExtras();
    //    Log.d(TAG,((PostVO) b.getSerializable(EXTRA_POST)).getTitle());
        liveDataPostViewModel.setPost((PostVO) b.getSerializable(EXTRA_POST));

        clModalProgress = findViewById(R.id.clModalProgress);
        tViewTittle = findViewById(R.id.tViewTittle);
        tViewBody = findViewById(R.id.tViewBody);
        tViewWoComents = findViewById(R.id.tViewWoComents);

        fabSave = findViewById(R.id.fabSave);
        fabAddComent = findViewById(R.id.fabComent);
        clContentComents = findViewById(R.id.clContentComents);

        rViewComents = findViewById(R.id.rViewComents);

        final Observer<PostVO> observer = new Observer<PostVO>() {

            @Override
            public void onChanged(PostVO postVO) {
                tViewTittle.setText(postVO.getTitle());
                tViewBody.setText(postVO.getBody());
                if(postVO.getComentVOList().size()>0){
                    tViewWoComents.setVisibility(View.GONE);
                    rViewAdapterComentsList = new RViewAdapterComentsList(liveDataPostViewModel.getPost().getValue().getComentVOList());
                    rViewComents.setAdapter(rViewAdapterComentsList);
                    rViewComents.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG,"asd");
                        }
                    });

                }else {

                    rViewComents.setAdapter(null);
                    tViewWoComents.setVisibility(View.VISIBLE);
                }
                if(postVO.isSaved()){
                        fabAddComent.show();
                        fabSave.setImageResource(R.drawable.ic_star_black_24dp);

                        fabAddComent.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ComentVO comentVO = new ComentVO();
                                comentVO.setIdPost(postVO.getId());
                                showDialogComent(comentVO);

                            }
                        });

                    clContentComents.setAlpha(1);

                }else {
                    fabSave.setImageResource(R.drawable.ic_star_border_black_24dp);
                    clContentComents.setAlpha(0.5f);
                    fabAddComent.hide();
                }

                fabSave.setOnClickListener(v->{

                    if(postVO.isSaved()){
                        presenter.tryNotSave(postVO);

                    }else {
                        presenter.trySave(postVO);
                    }
                });

                //if detect change on status saved
                if(postVO.isSaved()){
                    liveDataFavoritesListViewModel.addPost(postVO);
                }else {
                    liveDataFavoritesListViewModel.removePost(postVO);
                }


            }


        };

        liveDataPostViewModel.getPost().observe(this,observer);

    }



    public void enableInputs(){
        clModalProgress.setVisibility(View.GONE);
    }
    public void disableInputs(){

        clModalProgress.setVisibility(View.VISIBLE);
    }
    public void showDataChanged(PostVO post){
        liveDataPostViewModel.setPost(post);
        /*
        if(post.isSaved()){
            liveDataFavoritesListViewModel.addPost(post);
        }else {
            liveDataFavoritesListViewModel.removePost(post);
        }
        */

    }
    public void showError(String error){

        Toast.makeText(ctx,error,Toast.LENGTH_LONG).show();
        Log.d(TAG,error);
    }

    Dialog comentDialog;
    public void showDialogComent(ComentVO comentVO) {
        comentDialog = new Dialog(ctx);
        comentDialog.setContentView(R.layout.dialog_coment);
        Button btnAccept = (Button) comentDialog.findViewById(R.id.btnAccept);
        Button btnCancel = (Button) comentDialog.findViewById(R.id.btnCancel);
        final EditText eTextcoment = (EditText) comentDialog.findViewById(R.id.eTextComent);

        eTextcoment.setText(comentVO.getBody());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (isEditable) {
                    new MuestraDAO(getBaseContext()).editComentarioById(MUESTRA.getId(), eTextcoment.getText().toString());
                }*/
                comentDialog.dismiss();
            }
        });
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //try alert coment

                if(comentVO.getId()==0){ // is new
                    comentVO.setBody(eTextcoment.getText().toString());
                    presenter.tryAddComent(comentVO);
                }else {


                    presenter.tryAlterComent(comentVO);

                }

                comentDialog.dismiss();
            }
        });

        comentDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        comentDialog.show();
    }



}
