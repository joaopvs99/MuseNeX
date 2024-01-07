package Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ipca_project.musenex.R

class FragmentVisit: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragmento 1
        var view = inflater.inflate(R.layout.visit_view, container, false)

        // Exibir um Toast
        Toast.makeText(requireContext(), "Fragment 2 criado", Toast.LENGTH_SHORT).show()

        return view
    }
}