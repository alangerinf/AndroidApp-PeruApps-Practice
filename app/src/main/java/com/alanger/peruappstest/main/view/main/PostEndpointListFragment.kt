package com.alanger.peruappstest.main.view.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import com.alanger.peruappstest.main.presenter.PostEndpointPresenter
import com.alanger.peruappstest.main.presenter.PostEndpointPresenterImpl
import com.alanger.peruappstest.main.view.adapters.RViewAdapterPostList
import com.alanger.peruappstest.R
import com.alanger.peruappstest.models.VO.PostVO
import com.alanger.peruappstest.postDetail.view.PostDetailActivity


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostEndpointListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostEndpointListFragment : Fragment() {

    private val TAG = PostEndpointListFragment::class.java.simpleName

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null


    private var presenter: PostEndpointPresenter? = null


    private var mListener: OnFragmentInteractionListener? = null
    internal lateinit var rViewPostList: RecyclerView
    internal lateinit var clModalPrograssBar: ConstraintLayout

    internal lateinit var POSTS: List<PostVO>


    private var rViewAdapterPostList: RViewAdapterPostList? = null

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
        return inflater.inflate(R.layout.fragment_post_endpoint_list, container, false)
    }


    fun onButtonPressed(uri: String) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction_upd_eTextDNI(uri)
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


    override fun onStart() {
        super.onStart()


    }


    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    fun goToPostDetail(postVO: PostVO) {
        handler.post {
            val i = Intent(activity, PostDetailActivity::class.java)
            i.putExtra(PostDetailActivity.EXTRA_POST, postVO)
            startActivity(i)

        }

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
        fun onFragmentInteraction_upd_eTextDNI(mensaje: String)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Toast.makeText(getContext(),"onViewCreated",Toast.LENGTH_SHORT).show();
        handler.post {
            declaration()
            presenter!!.tryGetAllPost()
        }

    }

    private fun declaration() {
        presenter = PostEndpointPresenterImpl(this)
        clModalPrograssBar = view!!.findViewById(R.id.clModalProgress)
        rViewPostList = view!!.findViewById(R.id.rViewPostList)


    }

    fun enableInputs() {
        handler.post { clModalPrograssBar.visibility = View.GONE }


    }

    fun disableInputs() {
        handler.post { clModalPrograssBar.visibility = View.VISIBLE }


    }

    fun showError(error: String) {
        handler.post { Toast.makeText(context, error, Toast.LENGTH_LONG).show() }


    }

    fun showListPostData(data: List<PostVO>) {

        handler.post {
            POSTS = data

            rViewAdapterPostList = RViewAdapterPostList(data)

            rViewPostList.adapter = rViewAdapterPostList

            rViewAdapterPostList!!.setOnClicListener(View.OnClickListener { v ->
                val pos = rViewPostList.getChildAdapterPosition(v)
                presenter!!.tryGetPost(POSTS[pos].id)
            })
        }

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
         * @return A new instance of fragment PostEndpointListFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): PostEndpointListFragment {
            val fragment = PostEndpointListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }

        private val handler = Handler()
    }
}// Required empty public constructor
