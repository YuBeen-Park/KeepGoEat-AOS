package org.keepgoeat.presentation.onboarding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.keepgoeat.R
import org.keepgoeat.databinding.ActivityOnboardingBinding
import org.keepgoeat.util.binding.BindingActivity

@AndroidEntryPoint
class OnboardingActivity : BindingActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        val adapter = OnboardingAdapter(this)
        viewModel.setPosition(1)
        with(binding) {
            vpViewPager.adapter = adapter
            vpViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    this@OnboardingActivity.viewModel.setPosition(position)
                }
            })
            TabLayoutMediator(binding.indicator, vpViewPager) { _, _ -> }.attach()
        }
    }

    private fun addListeners() {
        binding.btnNext.setOnClickListener {
            binding.vpViewPager.currentItem++
        }
    }
}