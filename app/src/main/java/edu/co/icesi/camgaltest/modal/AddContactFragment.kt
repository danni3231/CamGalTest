package edu.co.icesi.camgaltest.modal

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import edu.co.icesi.camgaltest.R
import edu.co.icesi.camgaltest.UtilDomi
import edu.co.icesi.camgaltest.databinding.FragmentAddContactBinding

class AddContactFragment : DialogFragment() {

   private lateinit var binding:FragmentAddContactBinding



    override fun onStart() {
        super.onStart()
        //make that the modal adapt to the dimensions of app
        dialog?.window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::onGalleryResult
        )
        binding.newContactGalBtn.setOnClickListener{
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/*"
            launcher.launch(i)
        }
        return binding.root
    }

    private fun onGalleryResult(activityResult: ActivityResult) {
        val uri = activityResult.data?.data
        //val path = UtilDomi.getPath(activity.this, uri!!)
       // val bitmap = BitmapFactory.decodeFile(path)

       // binding.newContactImg.setImageBitmap(bitmap)
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddContactFragment()
    }
}