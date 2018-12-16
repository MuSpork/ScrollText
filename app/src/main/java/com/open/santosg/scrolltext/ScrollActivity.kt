package com.open.santosg.scrolltext

import android.graphics.Color
import android.graphics.PorterDuff
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
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import android.widget.Toast

var redValue1: Int = 0


class ScrollActivity : AppCompatActivity() {


    var redValue2: Int = 0
    var redValue3: Int = 0
    var greenValue1: Int = 0
    var greenValue2: Int = 0
    var greenValue3: Int = 0
    var message: String = "Default Text"
    var hideGUI: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        supportActionBar!!.hide()

        //Create a TextView and print Dingo to System when it's created
        val scrollTextView = findViewById<TextView>(R.id.scrollTextView)
        System.out.println("Dingo")
        scrollTextView.text = "Welcome to ScrollText"

        //Scroll the TextView
        scrollTextView.textSize = 50f
        scrollTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
        scrollTextView.setHorizontallyScrolling(true)
        scrollTextView.setLines(1)
        scrollTextView.marqueeRepeatLimit = -1
        scrollTextView.setSingleLine(true)
        scrollTextView.isSelected = true


        scrollTextView.setOnClickListener {
            createDialogPrompt()
            //Test Code to scroll the textView

            /*
            scrollTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
            scrollTextView.setHorizontallyScrolling(true)
            scrollTextView.setLines(1)
            scrollTextView.marqueeRepeatLimit = -1
            scrollTextView.setSingleLine(true)
            scrollTextView.setSelected(true)*/

            //scrollTextView.isSelected = true
            //scrollTextView.ellipsize = TextUtils.TruncateAt.START

        }


        //Create the hide interface buttons
        val hideButton: Button = findViewById(R.id.hideGUIButton)
        hideButton.setOnClickListener {
            if (hideGUI == false) {
                hideGUI == true
            } else if (hideGUI == true) {
                hideGUI == false
            }
        }

        if (hideGUI == false) {
            //Create Change Colour Button for Background
            val backgroundColourButton: Button = findViewById(R.id.backgroundColourButton)
            backgroundColourButton.text = "Background"
            backgroundColourButton.setOnClickListener {
                createColourPicker()

            }
        }else{
            backgroundColourButton.visibility = View.INVISIBLE
            hideButton.visibility = View.INVISIBLE
            //this.findViewById(R.layout.activity_scroll).getRootView().invalidate();
            //this.findViewById(android.R.id.content).getRootView().requestLayout();

        }

    }

    fun callToast() {
        Toast.makeText(this, "Value is: $redValue1", Toast.LENGTH_LONG).show()
    }

    //Function to create a ColourPicker
    fun createColourPicker() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Colour")

        val seekBarCollection: View = View(this)

        val seekBarRed = SeekBar(this)
        val seekBarGreen = SeekBar(this)
        val seekBarBlue = SeekBar(this)
        seekBarRed.max = 255
        seekBarGreen.max = 255
        seekBarBlue.max = 255

        //Set seekbar colours
        //seekBarGreen.getProgressDrawable().setColorFilter(getResources().getColor(R.color.material_blue_grey_800), PorterDuff.Mode.MULTIPLY);
        //seekBarRed.getProgressDrawable().setColorFilter(getResources().getColor(R.color.material_blue_grey_800), PorterDuff.Mode.MULTIPLY);
        //seekBarRed.setBackgroundColor(Color.RED)
        //seekBarBlue.setBackgroundColor(Color.BLUE)


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
                redValue1 = progress
                //System.out.println("Value of Red is: $redValue1 ")
                callToast()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
            }
        })



        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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

        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
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
        //builder.setView(seekBarGreen)
        builder.setView(seekBarRed)

        builder.setPositiveButton("DONE") { dialog, which ->
            Toast.makeText(this, "Thanks for picking the colour", Toast.LENGTH_LONG).show()


        }
        builder.setNegativeButton("CANCEL") { dialog, which ->

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Function to create a dialog Box for entering message
    fun createDialogPrompt() {
        //Create the AlertDialog with a prompt to enter text and the EditText field to be edited.
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please Enter Message:")
        val input = EditText(this)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        input.layoutParams = lp

        builder.setView(input)

        //Setting Positive button interactions, also error handling for if text is null or empty/
        builder.setPositiveButton("DONE") { dialog, which ->
            Toast.makeText(this, "Thanks for entering the message", Toast.LENGTH_LONG).show()
            if (input.text == null) {
                Toast.makeText(this, "Invalid Text", Toast.LENGTH_SHORT).show()
            } else if (input.text.isEmpty()) {
                Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                var message = input.text
                System.out.println(message)
                scrollTextView.text = message
                scrollTextView.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left))
            }

        }
        //Set Negative button. pretty simple.
        builder.setNegativeButton("CANCEL") { dialog, which ->
            Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()


    }


}
