package com.example.movies.moviefragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.adapters.MovieGridAdapter
import com.example.movies.databinding.FragmentProductsBinding

class MoviesFragment : Fragment() {

    private val viewModel : MoviesFragmentViewModel by lazy {
        ViewModelProvider(this).get(MoviesFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProductsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel



        binding.photosGrid.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollVertically(1)){
//                    Toast.makeText(context,"END",Toast.LENGTH_SHORT).show()
                    viewModel.getMovies1()
                    val size = binding.photosGrid.adapter?.itemCount
                    binding.photosGrid.adapter?.notifyItemRangeChanged(size!!,20)
                }
            }
        })

        binding.photosGrid.adapter = MovieGridAdapter(MovieGridAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
            Toast.makeText(context,it.original_title,Toast.LENGTH_SHORT).show()
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(MoviesFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })


        return binding.root
    }

}