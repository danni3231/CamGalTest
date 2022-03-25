package edu.co.icesi.camgaltest

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import edu.co.icesi.camgaltest.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var file: File
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requestPermissions(arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ),11)

        val galLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),::onGalleryResult
        )

        val camLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),::onCamaraResult
        )


        binding.galBtn.setOnClickListener {
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.type = "image/"
            galLauncher.launch(i)
        }

        binding.camBtn.setOnClickListener{
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            file = File("${getExternalFilesDir(null)}/photo.png")
            val uri = FileProvider.getUriForFile(this,"edu.co.icesi.camgaltest", file)
            i.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            camLauncher.launch(i)
        }

        binding.addBtn.setOnClickListener {
            //AddContactFragment.newInstance().show(supportFragmentManager, "addContact")
        }


    }

    private fun onCamaraResult(activityResult: ActivityResult) {
        //val bitmap = activityResult.data?.extras?.get("data") as Bitmap
        //binding.imageView.setImageBitmap(bitmap)

        val bitmap = BitmapFactory.decodeFile(file.path)

        //procesamiento
        val scaledBitmap = Bitmap.createScaledBitmap(
            bitmap,
            bitmap.width/10,
            bitmap.height/10,
            true
        )

        binding.imageView.setImageBitmap(scaledBitmap)


    }

    private fun onGalleryResult(activityResult: ActivityResult) {

        val uri = activityResult.data?.data
        val path = UtilDomi.getPath(this, uri!!)
        val bitmap = BitmapFactory.decodeFile(path)

        binding.imageView.setImageBitmap(bitmap)

    }
}