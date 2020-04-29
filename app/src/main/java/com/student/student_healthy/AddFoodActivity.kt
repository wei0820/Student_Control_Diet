package com.student.student_healthy

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore

import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.util.*
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.jackpan.libs.mfirebaselib.MfiebaselibsClass
import com.jackpan.libs.mfirebaselib.MfirebaeCallback
import net.bither.util.CompressTools
import net.bither.util.FileUtil.getReadableFileSize
import java.io.File
import com.student.student_searchmap.R

class AddFoodActivity : AppCompatActivity() , View.OnClickListener, MfirebaeCallback {
    override fun onClick(p0: View?) {
    }

    override fun getUserLogoutState(p0: Boolean) {
    }

    override fun resetPassWordState(p0: Boolean) {
    }

    override fun getsSndPasswordResetEmailState(p0: Boolean) {
    }

    override fun getFirebaseStorageType(p0: String?, p1: String?) {
    }

    override fun getUpdateUserName(p0: Boolean) {
    }

    override fun getDatabaseData(p0: Any?) {
    }

    override fun getuserLoginEmail(p0: String?) {
    }

    override fun getDeleteState(p0: Boolean, p1: String?, p2: Any?) {
    }

    override fun getFireBaseDBState(p0: Boolean, p1: String?) {
    }

    override fun getuseLoginId(p0: String?) {
    }

    override fun createUserState(p0: Boolean) {
    }

    override fun useLognState(p0: Boolean) {
    }

    override fun getFirebaseStorageState(p0: Boolean) {
    }

    lateinit var mUploadPhoto :Button
    private val CAMERA = 66
    private val PHOTO = 99
    private val REQUEST_EXTERNAL_STORAGE = 200
    private val PICKER = 100
    var img: String = ""
    lateinit var mFirebselibClass: MfiebaselibsClass
    var oldFile: File? = null
    lateinit var mImageView: ImageView
    lateinit var mOkButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFirebselibClass = MfiebaselibsClass(this, this)

        setContentView(R.layout.activity_add_food)
        mUploadPhoto = findViewById(R.id.upload)
        mImageView = findViewById(R.id.img)
        mOkButton = findViewById(R.id.okbtn)
        mOkButton.setOnClickListener {
            
        }
        mUploadPhoto.setOnClickListener {
            selectPic()
        }

    }
    private fun selectPic() {
        val permission = ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            android.Manifest.permission.CAMERA
                    )
            ) {
//                android.support.v7.app.AlertDialog.Builder(this)
//                        .setMessage("我真的沒有要做壞事, 給我權限吧?")
//                        .setPositiveButton("OK") { dialog, which ->
//                            ActivityCompat.requestPermissions(
//                                    this,
//                                    arrayOf(android.Manifest.permission.CAMERA),
//                                    REQUEST_EXTERNAL_STORAGE
//                            )
//                        }
//                        .setNegativeButton("No") { dialog, which -> finish() }
//                        .show()
            } else {
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        REQUEST_EXTERNAL_STORAGE)
            }

        } else {
            //開啟相簿相片集，須由startActivityForResult且帶入requestCode進行呼叫，原因
            //為點選相片後返回程式呼叫onActivityResult
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, PHOTO)


        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    finish()
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request
    }

    //拍照完畢或選取圖片後呼叫此函式
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICKER) {
            if (resultCode == Activity.RESULT_OK) {


            }
        }

        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if ((requestCode == CAMERA || requestCode == PHOTO) && data != null) {
            //取得照片路徑uri
            val datauri = data.data
            oldFile = PhotoManager.getTempFile(this,datauri);
            CompressTools.getInstance(this).compressToFile(oldFile, object : CompressTools.OnCompressListener {
                override fun onStart() {

                }

                override fun onFail(error: String) {

                }

                override fun onSuccess(file: File) {

                    img = encode(PhotoManager.getFilePath(file.path.toString()))
                    if(!img.isEmpty()){
                        Toast.makeText(this@AddFoodActivity,"照片取得成功",Toast.LENGTH_SHORT).show()

                        decode(img)

                    }else{
                        Toast.makeText(this@AddFoodActivity,"照片取得失敗",Toast.LENGTH_SHORT).show()

                    }
                }
            })//

            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun uploadFromPic(datauri: Uri?) {
        val after44 = Build.VERSION.SDK_INT >= 19
        var filePath = ""

        if (after44) {
            val wholeID = DocumentsContract.getDocumentId(datauri)

            // Split at colon, use second item in the array
            val id = wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]

            val column = arrayOf(MediaStore.Images.Media.DATA)

            // where id is equal to
            val sel = MediaStore.Images.Media._ID + "=?"

            val cursor = contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    column, sel, arrayOf(id), null
            )


            val columnIndex = cursor!!.getColumnIndex(column[0])

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex)

            }

            cursor.close()
        } else {

            try {
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = contentResolver.query(
                        datauri!!,
                        filePathColumn, null, null, null)
                cursor!!.moveToFirst()

                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                filePath = cursor.getString(columnIndex)
                cursor.close()

            } catch (e: Exception) {
                // TODO: handle exception
                e.printStackTrace()
            }

        }

    }

    private fun ScalePic(bitmap: Bitmap, phone: Int) {
        Log.d(javaClass.simpleName, "//ScalePic")

        //縮放比例預設為1
        var mScale = 1f

        //如果圖片寬度大於手機寬度則進行縮放，否則直接將圖片放入ImageView內
        if (bitmap.width > phone) {
            //判斷縮放比例
            mScale = phone.toFloat() / bitmap.width.toFloat()

            val mMat = Matrix()
            mMat.setScale(mScale, mScale)

            val mScaleBitmap = Bitmap.createBitmap(
                    bitmap,
                    0,
                    0,
                    bitmap.width,
                    bitmap.height,
                    mMat,
                    false
            )
            Log.d(javaClass.simpleName, mScaleBitmap.toString())

        } else {

        }
    }

    fun encode(imageUri: Uri): String {
        val input = getContentResolver().openInputStream(imageUri)
        val image = BitmapFactory.decodeStream(input, null, null)
        //encode image to base64 string
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var imageBytes = baos.toByteArray()
        val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
        return imageString
    }

    fun decode(imageString: String) {

        //decode base64 string to image
        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        mImageView.setImageBitmap(decodedImage)
    }
}
