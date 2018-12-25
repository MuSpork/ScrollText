package com.open.santosg.scrolltext

import android.annotation.TargetApi
import android.app.ActionBar
import android.graphics.Color
import android.graphics.Color.*
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*
import kotlinx.android.synthetic.main.activity_scroll.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.GestureDetectorCompat
import android.text.InputType
import android.text.TextUtils
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.LinearLayout.LayoutParams
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.dialog.*
import org.w3c.dom.Text


class ScrollActivity : AppCompatActivity(),GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    //Implement methods for GestureDetecton
    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        //System.out.println("Double-Tap")
        hideGUI = true
        if (hideGUI == true) {
            hideGUI = false
            println("Visible")
            backgroundColourButton.visibility = View.VISIBLE
            changeTextButton.visibility = View.VISIBLE
            hideGUIButton.visibility = View.VISIBLE
        }
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        //println(e)
        //println("Double-Tap")
        return true
    }

    var initialBackgroundColour: Int = 0;

    var redValue1: Int = 0
    var redValue2: Int = 0
    var redValue3: Int = 0
    var greenValue1: Int = 0
    var greenValue2: Int = 0
    var greenValue3: Int = 0
    var blueValue1: Int = 0
    var blueValue2: Int = 0
    var blueValue3: Int = 0

    var colourMod: String = "Default Text"

    var message: String = "Default Text"
    var hideGUI: Boolean = false
    var gDetector: GestureDetectorCompat? = null
   // var gestureDetectorCompat: GestureDetectorCompat? = null
    var gd = GradientDrawable();

    @TargetApi(Build.VERSION_CODES.O)
    //@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        supportActionBar!!.hide()

        //Set Background Colour to Black by default.
        val view: View = findViewById(R.id.layoutScroll)
        val root: View = view.rootView
        root.setBackgroundColor(Color.rgb(0,0,0))

        //Set the Text Colour, and button colours to contrast the background
        scrollTextView.setTextColor(Color.rgb(255,255,255))
        backgroundColourButton.setTextColor(Color.rgb(255,255,255))
        changeTextButton.setTextColor(Color.rgb(255,255,255))
        hideGUIButton.setTextColor(Color.rgb(255,255,255))

        //Change Colours of the buttons and add customised corners
        gd.shape = GradientDrawable.RECTANGLE
        gd.setColor(TRANSPARENT)
        //gd.setColor(Color.rgb(255,255,255))
        gd.setStroke(5, WHITE);
        gd.cornerRadius = 100.0f
        hideGUIButton.setBackground(gd)
        changeTextButton.setBackground(gd)
        gd.setSize(320,50)
        backgroundColourButton.width = 320
        backgroundColourButton.setBackground(gd)

        this.gDetector = GestureDetectorCompat(this,this)
        gDetector?.setOnDoubleTapListener(this)

        //Create a TextView and print Dingo to System when it's created
        val scrollTextView = findViewById<TextView>(R.id.scrollTextView)
        System.out.println("Dingo")
        scrollTextView.text = "Welcome to ScrollText"



        //Scroll the TextView
        scrollTextView.textSize = 150f
        scrollTextView.ellipsize = TextUtils.TruncateAt.MARQUEE
        scrollTextView.setHorizontallyScrolling(true)
        scrollTextView.setLines(1)
        scrollTextView.marqueeRepeatLimit = -1
        scrollTextView.setSingleLine(true)
        scrollTextView.isSelected = true
        scrollTextView.paintFlags = (0)
        //


        //Button Listener to change the message of the scrolling text
        scrollTextView.setOnClickListener {
            createDialogPrompt()

        }

        //println("Background")
        val backgroundColourButton: Button = findViewById(R.id.backgroundColourButton)
        backgroundColourButton.text = "Background"
        backgroundColourButton.setOnClickListener {
            colourMod = "Background"
            createColourPicker()

        }
        val changeTextButton: Button = findViewById(R.id.changeTextButton)
        changeTextButton.text = "Text"
        changeTextButton.setOnClickListener {
            colourMod = "Text"
            createColourPicker()

        }


        //Create the hide interface buttons
        val hideButton: Button = findViewById(R.id.hideGUIButton)
        hideButton.setOnClickListener {
            if (hideGUI == false) {
                hideGUI = true

                Toast.makeText(this,"Double-Tap to reveal buttons",Toast.LENGTH_SHORT).show()
                backgroundColourButton.visibility = View.INVISIBLE
                hideGUIButton.visibility = View.INVISIBLE
                changeTextButton.visibility = View.INVISIBLE

                System.out.print("false")

            } else if (hideGUI == true) {
                hideGUI = false

                backgroundColourButton.visibility = View.VISIBLE
                hideButton.visibility = View.VISIBLE
                changeTextButton.visibility = View.VISIBLE

            }
        }

    }

    fun callToast() {
        Toast.makeText(this, "Value is: $redValue1", Toast.LENGTH_LONG).show()
    }

    //Function to create a ColourPicker
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun createColourPicker() {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Colour")

        val seekBarCollection: View = View(this)
        //val vg: ViewGroup = ViewGroup()//ViewGroup(this)
        //vg.addView(seekBarCollection)

        val seekBarRed = SeekBar(this)
        val seekBarGreen = SeekBar(this)
        val seekBarBlue = SeekBar(this)
        seekBarRed.max = 255
        seekBarGreen.max = 255
        seekBarBlue.max = 255

        //val seekBarView: View = findViewById(R.id.dialogLayout)
        //seekBarView.layoutParams = LayoutParams(LayoutParams().WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        //seekBarCollection
        /*
        val lpd = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        //dialogLayout.layoutParams = lpd
        dialogLayout.addView(seekBarRed)
        dialogLayout.addView(seekBarGreen)
        dialogLayout.addView(seekBarBlue)
        */

        val seekBarTextRed: TextView = TextView(this)
        val seekBarTextGreen: TextView = TextView(this)
        val seekBarTextBlue: TextView = TextView(this)
        val colourPreview: TextView = TextView(this)

        seekBarTextGreen.text = ""
        seekBarTextBlue.text = ""




        val linear: LinearLayout = LinearLayout(this)

        /*linear.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT - 250,
            LayoutParams.MATCH_PARENT + 50)
        */

        linear.orientation = LinearLayout.VERTICAL

        linear.addView(seekBarRed)
        linear.addView(seekBarTextRed)
        linear.addView(seekBarGreen)
        linear.addView(seekBarTextGreen)
        linear.addView(seekBarBlue)
        linear.addView(seekBarTextBlue)
        linear.addView(colourPreview)


        builder.setView(linear)

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
                redValue2 = redValue1
                seekBarTextRed.text = "Red Value: $redValue1"
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
                greenValue1 = progress
                seekBarTextGreen.text = "Green Value: $greenValue1"
                greenValue2 = greenValue1
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



                blueValue1 = progress
                blueValue2 = blueValue1
                seekBarTextBlue.text = "Blue Value: $blueValue1"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {


            }
        })

        initialBackgroundColour = Color.rgb(redValue1,greenValue1,blueValue1)
        println(initialBackgroundColour)



        colourPreview.setBackgroundColor(Color.rgb(redValue2,greenValue2,blueValue2))

        //builder.setView(seekBarRed)
        builder.setView(linear)

        builder.setPositiveButton("DONE") { dialog, which ->

            if(colourMod == "Text"){
                scrollTextView.setTextColor(Color.rgb(redValue1,greenValue1,blueValue1))
            }else if(colourMod == "Background") {
                if(redValue1<=240&&greenValue1<=240&&blueValue1<=240){
                    val view: View = findViewById(R.id.layoutScroll)
                    val root: View = view.rootView
                    root.setBackgroundColor(Color.rgb(redValue1, greenValue1, blueValue1))
                    callInvalidate()
                    println("bright colour")

                }else {
                    val view: View = findViewById(R.id.layoutScroll)
                    val root: View = view.rootView
                    root.setBackgroundColor(Color.rgb(redValue1, greenValue1, blueValue1))
                }
            }

        }
        builder.setNegativeButton("CANCEL") { dialog, which ->

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Function to create a dialog Box for entering message
    private fun createDialogPrompt() {
        //Create the AlertDialog with a prompt to enter text and the EditText field to be edited.
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please Enter Message:")
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        val lp = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        input.layoutParams = lp

        builder.setView(input)

        //Setting Positive button interactions, also error handling for if text is null or empty/
        builder.setPositiveButton("DONE") { dialog, which ->
            Toast.makeText(this, "Thanks for entering the message", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        this.gDetector?.onTouchEvent(event)
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event)
    }

    fun callInvalidate(){

        val view: View = findViewById(R.id.layoutScroll)
        val root: View = view.rootView
        gd.setColor(Color.BLACK)
        root.invalidate()
    }
}
