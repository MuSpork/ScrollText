package com.open.santosg.scrolltext

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.Window
import android.widget.*
import kotlinx.android.synthetic.main.activity_scroll.*
import org.w3c.dom.Text
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable
import android.widget.SeekBar
import android.widget.Toast






class ScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        supportActionBar!!.hide()

        //Create a TextView
        val scrollTextView = findViewById<TextView>(R.id.scrollTextView)
        System.out.println("Dingo")
        scrollTextView.text = "Welcome to ScrollText"

        scrollTextView.setOnClickListener {
            createDialogPrompt()
        }

        //Create Change Colour Button for Background
        val backgroundColourButton: Button = findViewById<Button>(R.id.backgroundColourButton)
        backgroundColourButton.setText("Background Colour")
        backgroundColourButton.setOnClickListener(){
            createColourPicker()
        }


    }

    //Function to create a ColourPicker
    fun createColourPicker(){

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Colour")

        val seekBarCollection: View = View(this)

        val seekBarRed = SeekBar(this)
        val seekBarGreen = SeekBar(this)
        val seekBarBlue = SeekBar(this)
        seekBarRed.max = 255
        seekBarGreen.max = 255
        seekBarBlue.max = 255

        /*seekBar.isIndeterminate = true
        seekBar.minimumWidth = 200

        val thumb = ShapeDrawable(OvalShape())

        thumb.paint.color = 0x00FF00
        thumb.intrinsicHeight = 80
        thumb.intrinsicWidth = 30

        seekBar.thumb = thumb
        seekBar.progress = 1*/

        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Write code to perform some action when progress is changed.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.

            }
        })

        builder.setView(seekBarRed)

        builder.setPositiveButton("DONE") { dialog, which ->
            Toast.makeText(this, "Thanks for picking the colour", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("CANCEL") {dialog, which ->

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Function to create a dialog Box
    fun createDialogPrompt(){

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please Enter Message:")
        val input = EditText(this)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        input.setLayoutParams(lp);

        builder.setView(input)

        builder.setPositiveButton("DONE") { dialog, which ->
            Toast.makeText(this, "Thanks for entering the message", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("CANCEL") {dialog, which ->
            Toast.makeText(this, "Canceled",Toast.LENGTH_LONG).show()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


}
