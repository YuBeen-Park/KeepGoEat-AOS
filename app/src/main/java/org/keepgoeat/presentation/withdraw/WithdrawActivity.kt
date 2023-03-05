package org.keepgoeat.presentation.withdraw

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.keepgoeat.R
import org.keepgoeat.databinding.ActivityWithdrawBinding
import org.keepgoeat.presentation.my.MyViewModel
import org.keepgoeat.presentation.type.WithdrawCheckType
import org.keepgoeat.util.binding.BindingActivity
import org.keepgoeat.util.extension.showKeyboard

@AndroidEntryPoint
class WithdrawActivity : BindingActivity<ActivityWithdrawBinding>(R.layout.activity_withdraw) {
    private val viewModel: MyViewModel by viewModels()

    // TODO 키보드 자판에서 키보드 내리는 경우 recyclerView visibility 조정해주기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initLayout()
        addListeners()
    }

    private fun initLayout() {
        binding.rvWithdraw.apply {
            itemAnimator = null
            adapter = WithdrawReasonAdapter(context)
        }
        binding.clickType = WithdrawCheckType.DEFAULT
    }

    private fun addListeners() {
        binding.layoutOtherReason.setOnClickListener {
            when (binding.clickType) {
                WithdrawCheckType.CLICKED -> {
                    clearFocus()
                    binding.clickType = WithdrawCheckType.DEFAULT
                    //binding.tvOtherReasonErrorMsg.setVisibility(false)
                }
                WithdrawCheckType.DEFAULT -> {
                    requestFocus()
                    binding.clickType = WithdrawCheckType.CLICKED
                }
                else -> {}
            }
        }
        binding.etOtherReason.setOnFocusChangeListener { _, focused ->
            if (focused) { // '직접 입력' editText가 focus 상태일 때
                binding.rvWithdraw.visibility = View.GONE
                if (binding.clickType == WithdrawCheckType.DEFAULT)
                    binding.clickType = WithdrawCheckType.CLICKED
            }
        }
        binding.layoutWithdraw.setOnClickListener { // 외부 영역 클릭 했을 때
            clearFocus()
        }
        binding.btnWithdraw.setOnClickListener {
            if (binding.etOtherReason.text.isNullOrBlank() && binding.clickType == WithdrawCheckType.CLICKED)
                binding.tvOtherReasonErrorMsg.visibility = View.VISIBLE
            else {
                binding.tvOtherReasonErrorMsg.visibility = View.GONE
                WithdrawDialogFragment().show(supportFragmentManager, "withDrawDialog")
            }
        }
        binding.viewWithdrawToolbar.ivBack.setOnClickListener{
            finish()
        }
    }

    private fun clearFocus() {
        binding.etOtherReason.clearFocus()
        binding.rvWithdraw.visibility = View.VISIBLE
        showKeyboard(binding.etOtherReason, false)
    }

    private fun requestFocus() {
        binding.etOtherReason.requestFocus()
        binding.rvWithdraw.visibility = View.GONE
        showKeyboard(binding.etOtherReason, true)
    }
}
