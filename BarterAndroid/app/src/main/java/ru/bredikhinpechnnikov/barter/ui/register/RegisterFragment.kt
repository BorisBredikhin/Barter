package ru.bredikhinpechnnikov.barter.ui.register

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.bredikhinpechnnikov.barter.R
import ru.bredikhinpechnnikov.barter.ui.login.LoginViewModel
import ru.bredikhinpechnnikov.barter.ui.login.LoginViewModelFactory
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uploadBtn = view.findViewById<Button>(R.id.photo_uploader_btn)
        val photoUploader = view.findViewById<ImageView>(R.id.photo_uploader)
        val firstNameField = view.findViewById<EditText>(R.id.first_name)
        val lastNameField = view.findViewById<EditText>(R.id.last_name)
        val usernameField = view.findViewById<EditText>(R.id.username)
        val birthdayField = view.findViewById<EditText>(R.id.birthday)
        val primaryActivityField = view.findViewById<EditText>(R.id.primary_activity)
        val phoneNumberField = view.findViewById<EditText>(R.id.phone_number)
        val passwordField = view.findViewById<EditText>(R.id.password)
        val passwordRepeatField = view.findViewById<EditText>(R.id.password_repeat)
        val registerBtn = view.findViewById<Button>(R.id.register_btn)

        uploadBtn.setOnClickListener {
            val permissionStatus = ContextCompat.checkSelfPermission(view.context, Manifest.permission.READ_EXTERNAL_STORAGE)

            if (permissionStatus == PackageManager.PERMISSION_DENIED)
                ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)


            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Выберите фото"), 1)
        }

        registerBtn.setOnClickListener {
            loginViewModel.register(
                firstNameField.text.toString(),
                lastNameField.text.toString(),
                usernameField.text.toString(),
                birthdayField.text.toString(),
                primaryActivityField.text.toString(),
                phoneNumberField.text.toString(),
                passwordField.text.toString(),
                passwordRepeatField.text.toString()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null)
            return

        val photoFile = createImageFile()

        val inputStream = activity!!.contentResolver.openInputStream(data.data!!)
        val decodeStream = BitmapFactory.decodeStream(inputStream)
        view!!.findViewById<ImageView>(R.id.photo_uploader).setImageBitmap(decodeStream)
        inputStream!!.close()
    }


    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",  /* suffix */
                storageDir /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        return image
    }

    @Throws(IOException::class)
    fun copyStream(input: InputStream, output: OutputStream) {
        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (input.read(buffer).also { bytesRead = it } != -1) {
            output.write(buffer, 0, bytesRead)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RegisterFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}