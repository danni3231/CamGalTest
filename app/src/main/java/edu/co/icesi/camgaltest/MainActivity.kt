package edu.co.icesi.camgaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.co.icesi.camgaltest.databinding.ActivityMainBinding
import edu.co.icesi.camgaltest.modal.AddContactFragment

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addBtn.setOnClickListener {
            AddContactFragment.newInstance().show(supportFragmentManager, "addContact")
        }
    }
}