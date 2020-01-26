package io.procrastination.atlas.view.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import io.procrastination.atlas.R
import io.procrastination.atlas.di.AtlasViewModelFactory
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.view.list.AtlasListViewModel.Response
import io.procrastination.atlas.view.list.AtlasListViewModel.Request
import javax.inject.Inject

class ListFragment : Fragment() {

    @Inject
    lateinit var factory: AtlasViewModelFactory

    private val viewModel: AtlasListViewModel by activityViewModels { factory }

    private lateinit var rvCountries: RecyclerView
    private lateinit var adapterCountries: Adapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterCountries = Adapter()

        rvCountries = view.findViewById(R.id.list_countries)
        rvCountries.setHasFixedSize(true)
        rvCountries.layoutManager = LinearLayoutManager(requireContext())
        rvCountries.adapter = adapterCountries

        viewModel.response.observe(this, Observer { response ->
            when (response) {
                is Response.CountriesLoaded -> onCountriesLoaded(response.countries)

                is Response.Error -> onError(response.message)
            }
        })

        viewModel.counties.observe(this, Observer {
            viewModel.handleRequest(Request.LoadCountriesAlphabetically)
        })
    }

    private fun onCountriesLoaded(countries: List<Country>) {
        adapterCountries.swapDataSet(countries)
    }

    private fun onError(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.design_default_color_error))
            .show()
    }

    /*
     **********************************************************************************************/
    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        private var dataset = emptyList<Country>()

        fun swapDataSet(countries: List<Country>) {
            val result = DiffUtil.calculateDiff(CountryCalculater(dataset, countries))
            result.dispatchUpdatesTo(this)
            this.dataset = countries
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_country, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = dataset.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(dataset[position])
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val btnOpenDetails = view.findViewById<Button>(R.id.btn_open_country)

        fun bind(country: Country) {
            btnOpenDetails.text = country.name

            btnOpenDetails.setOnClickListener {
                findNavController().navigate(ListFragmentDirections.actionNavListToNavDetails(country.name))
            }
        }
    }

    inner class CountryCalculater(
        private val oldList: List<Country>,
        private val newList: List<Country>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}