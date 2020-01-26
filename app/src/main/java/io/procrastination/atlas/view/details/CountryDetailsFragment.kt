package io.procrastination.atlas.view.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import io.procrastination.atlas.R
import io.procrastination.atlas.di.AtlasViewModelFactory
import io.procrastination.atlas.model.entities.Country
import io.procrastination.atlas.view.details.CountryDetailsViewModel.Response
import javax.inject.Inject

class CountryDetailsFragment : Fragment() {

    @Inject
    lateinit var factory: AtlasViewModelFactory

    private val viewModel: CountryDetailsViewModel by viewModels { factory }

    private lateinit var txtName : TextView
    private lateinit var txtCapital : TextView
    private lateinit var txtPopulation : TextView
    private lateinit var txtCoordinates : TextView

    private val countryName by lazy {
        CountryDetailsFragmentArgs.fromBundle(arguments ?: Bundle.EMPTY).country
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtName = view.findViewById(R.id.txt_name)
        txtCapital = view.findViewById(R.id.txt_capital)
        txtPopulation = view.findViewById(R.id.txt_population)
        txtCoordinates = view.findViewById(R.id.txt_location)

        viewModel.result.observe(this, Observer { r ->
            when (r) {
                is Response.Error -> onError(r.message)
                is Response.FatalError -> findNavController().popBackStack()
                is Response.CountryLoaded -> onCountryLoaded(r.country)
            }
        })

        viewModel.loadCountry(countryName)
    }

    private fun onError(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.design_default_color_error))
            .show()
    }

    private fun onCountryLoaded(country : Country){
        txtName.text = country.name
        txtCapital.text = country.capital
        txtPopulation.text = country.population.toString()
        txtCoordinates.text = String.format("%.2f, %.2f", country.latitude, country.longitude)
    }
}