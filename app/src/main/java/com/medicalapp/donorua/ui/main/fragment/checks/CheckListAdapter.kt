package com.medicalapp.donorua.ui.main.fragment.checks

import android.content.ContentResolver
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medicalapp.donorua.R
import com.medicalapp.donorua.model.check.Check

class CheckListAdapter : RecyclerView.Adapter<CheckViewHolder>()
{

    private lateinit var listOfChecks: List<Check>
    private lateinit var onCLickedListener: (Check) -> Unit

    fun set(listOfChecks: List<Check>, onCLickedListener: (onClickedCheck: Check) -> Unit) {
        this.listOfChecks = listOfChecks
        this.onCLickedListener = onCLickedListener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckViewHolder {
        return CheckViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_check, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CheckViewHolder, position: Int) {
        holder.bind(listOfChecks[position], onCLickedListener)

    }

    override fun getItemCount() = listOfChecks.size
}