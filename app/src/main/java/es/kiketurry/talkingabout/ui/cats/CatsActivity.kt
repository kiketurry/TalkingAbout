package es.kiketurry.talkingabout.ui.cats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import es.kiketurry.talkingabout.R
import es.kiketurry.talkingabout.databinding.ActivityCatsBinding
import es.kiketurry.talkingabout.injection.InjectionSingleton
import es.kiketurry.talkingabout.ui.base.BaseActivity
import es.kiketurry.talkingabout.ui.cats.detail.DetailCatFragmentMotionLayout
import es.kiketurry.talkingabout.ui.cats.detail.simplyversion.DetailCatFragment
import es.kiketurry.talkingabout.ui.cats.list.BreedListFragment

class CatsActivity : BaseActivity<ActivityCatsBinding>() {
    override val TAG: String? get() = CatsActivity::class.qualifiedName

    lateinit var catsViewModel: CatsViewModel

    override fun inflateBinding() {
        binding = ActivityCatsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
        catsViewModel = ViewModelProvider(this, InjectionSingleton.provideViewModelFactory(this)).get(CatsViewModel::class.java)
    }

    override fun observeViewModel() {
        catsViewModel.loadingMutableLiveData.observe(this, this::showLoading)
        catsViewModel.errorMutableLiveData.observe(this, this::showError)
    }

    override fun createAfterInflateBindingSetupObserverViewModel(savedInstanceState: Bundle?) {
        goToBreedsListFragment()

        binding.clToolbar.ibToolbarClose.setOnClickListener { Log.i(TAG, "Pulsamos Cerrar") }

        catsViewModel.getBreeds(resources.getInteger(R.integer.limit_breeds))
//        mainViewModel.getBoardGamesByUser("kiketurrydeveloper")
//        catsViewModel.getThingsBoardGamesGeek("40834")
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
        catsViewModel.setBreedModelSelected(position)
//        goToDetailBreedMotionLayout()
        goToDetailBreedSimply()
    }
}