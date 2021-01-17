package es.kiketurry.talkingabout.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.ActivityMainBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.detail.DetailCatFragmentMotionLayout
import es.kiketurry.talkingabout.ui.detail.simplyversion.DetailCatFragment
import es.kiketurry.talkingabout.ui.list.BreedListFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val TAG: String? get() = MainActivity::class.qualifiedName

    lateinit var mainViewModel: MainViewModel

    override fun inflateBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        mainViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(MainViewModel::class.java)
    }

    override fun observeViewModel() {
        mainViewModel.loadingMutableLiveData.observe(this, this::showLoading)
        mainViewModel.errorMutableLiveData.observe(this, this::showError)
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        goToBreedsListFragment()

        binding.clToolbar.ibToolbarClose.setOnClickListener { Log.i(TAG, "Pulsamos Cerrar") }

//        mainViewModel.getBreeds(resources.getInteger(R.integer.limit_breeds))
//        mainViewModel.getBoardGamesByUser("kiketurrydeveloper")
        mainViewModel.getThingsBoardGamesGeek("40834")
//        mainViewModel.getThingsBoardGamesGeek("318079") //mision marte
    }

    override fun configureToolbar() {
        showTitleToolbar("Actividad principal")
        showBackToolbar(false)
        showCloseToolbar(true)
    }

    private fun goToBreedsListFragment() {
        val fragment: Fragment = BreedListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, BreedListFragment::class.qualifiedName)
            .addToBackStack(BreedListFragment::class.qualifiedName)
            .commit()
    }

    private fun goToDetailBreedSimply() {
        val fragment: Fragment = DetailCatFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, DetailCatFragment::class.qualifiedName)
            .addToBackStack(DetailCatFragment::class.qualifiedName)
            .commit()
    }

    private fun goToDetailBreedMotionLayout() {
        val fragment: Fragment = DetailCatFragmentMotionLayout()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flMainFragmentHost, fragment, DetailCatFragmentMotionLayout::class.qualifiedName)
            .addToBackStack(DetailCatFragmentMotionLayout::class.qualifiedName)
            .commit()
    }

    fun setPositionBreedSelected(position: Int) {
        mainViewModel.setBreedModelSelected(position)
//        goToDetailBreedMotionLayout()
        goToDetailBreedSimply()
    }
}