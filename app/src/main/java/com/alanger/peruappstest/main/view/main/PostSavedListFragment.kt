package com.alanger.peruappstest.main.view.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView


import com.alanger.peruappstest.R
import com.alanger.peruappstest.main.presenter.PostSavedListPresenterImpl
import com.alanger.peruappstest.main.view.adapters.LiveDataFavoritesListViewModel
import com.alanger.peruappstest.main.view.adapters.RViewAdapterPostList
import com.alanger.peruappstest.models.VO.PostVO
import com.alanger.peruappstest.postDetail.view.PostDetailActivity

class PostSavedListFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null


    private var rViewAdapterPostList: RViewAdapterPostList? = null
    private var rViewFavorites: RecyclerView? = null
    internal var clModalPrograssBar: ConstraintLayout? = null
    internal lateinit var presenter: PostSavedListPresenterImpl

    private var liveDataFavoritesListViewModel: LiveDataFavoritesListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_saved_list, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun declaration() {

        presenter = PostSavedListPresenterImpl(this)
        rViewFavorites = view!!.findViewById(R.id.rViewFavorites)

        liveDataFavoritesListViewModel = ViewModelProviders.of(this).get(LiveDataFavoritesListViewModel::class.java)

        val observer = Observer<List<PostVO>> { postVOList ->
            rViewAdapterPostList = RViewAdapterPostList(postVOList)
            rViewFavorites!!.adapter = rViewAdapterPostList
            rViewAdapterPostList!!.setOnClicListener(View.OnClickListener { v ->
                val pos = rViewFavorites!!.getChildAdapterPosition(v)
                goToPostDetail(postVOList[pos])
            })
        }

        liveDataFavoritesListViewModel!!.favorites.observe(this, observer)

        //    clModalPrograssBar = getView().findViewById(R.id.clModalProgres


    }

    fun goToPostDetail(postVO: PostVO) {
        handler.post {
            val i = Intent(activity, PostDetailActivity::class.java)
            i.putExtra(PostDetailActivity.EXTRA_POST, postVO)
            startActivity(i)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Toast.makeText(getContext(),"onViewCreated",Toast.LENGTH_SHORT).show();

        declaration()
        presenter.tryGetListFavorites()


    }

    fun showFavoriteList(listAll: MutableList<PostVO>) {

        liveDataFavoritesListViewModel!!.setFavoritesList(listAll)

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)

    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostSavedListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PostSavedListFragment {
            val fragment = PostSavedListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }


        private val handler = Handler()
    }
}// Required empty public constructor
