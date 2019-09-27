package com.alanger.peruappstest.main.repository

import com.alanger.peruappstest.retrofit.RetrofitClient
import com.alanger.peruappstest.retrofit.models.GetPost
import com.alanger.peruappstest.retrofit.models.RetroPost
import com.alanger.peruappstest.main.interactor.PostEndpointInteractor

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostEndpointRepositoryImpl(private val interactor: PostEndpointInteractor) : PostEndpointRepository {

    internal var TAG = PostEndpointRepositoryImpl::class.java.simpleName
    /*
    @Override
    public void signIn(String user, String password) {
        Log.d(TAG, "sign in " + user + " " + password);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("usuario", user);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                ConectionConfig.POST_LOGIN, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            int codigoRespuesta = response.getInt("codigoRespuesta");
                            if(codigoRespuesta==HTTP_OK) {
                                JSONObject datos = new JSONObject(String.valueOf(response.getJSONObject("datos")));
                                String token = datos.getString("token");
                                JSONObject usuario = datos.getJSONObject("usuario");

                                User userTemp = new User();
                                userTemp.setId(Integer.parseInt(usuario.getString("ID")));
                                userTemp.setUser(user);
                                userTemp.setPassword(password);
                                userTemp.setName(usuario.getString("NOMBRE"));
                                userTemp.setToken(token);

                                interactor.showListPostSuccess(userTemp);
                            }else {
                                interactor.showError("Codigo "+codigoRespuesta+": "+response.getString("mensajeRespuesta"));
                            }

                        } catch (JSONException e) {
                            interactor.showError(e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        interactor.showError(error.toString());
                        error.printStackTrace();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }
*/
    override fun queryPostList() {
        val service = RetrofitClient.getRetrofitInstance().create(GetPost::class.java)
        val call = service.allPosts
        call.enqueue(object : Callback<List<RetroPost>> {

            override fun onResponse(call: Call<List<RetroPost>>, response: Response<List<RetroPost>>) {
                response.body()?.let { interactor.showListPostSuccess(it) }
            }

            override fun onFailure(call: Call<List<RetroPost>>, throwable: Throwable) {
                interactor.showError("ERROR: " + throwable.message)
            }
        })


    }

    override fun queryPost(id: Int) {

        val service = RetrofitClient.getRetrofitInstance().create(GetPost::class.java)
        val call = service.getPost(id)
        call.enqueue(object : Callback<RetroPost> {
            override fun onResponse(call: Call<RetroPost>, response: Response<RetroPost>) {
                response.body()?.let { interactor.showPostSuccess(it) }
            }

            override fun onFailure(call: Call<RetroPost>, t: Throwable) {
                t.message?.let { interactor.showError(it) }
            }
        })

    }
}
