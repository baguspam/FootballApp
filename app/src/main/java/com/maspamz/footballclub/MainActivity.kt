package com.maspamz.footballclub

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderTypes.BaseSliderView
import com.glide.slider.library.Tricks.ViewPagerEx
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.Animations.DescriptionAnimation
import com.glide.slider.library.SliderTypes.TextSliderView
import android.widget.Toast







class MainActivity : AppCompatActivity(), View.OnClickListener, BaseSliderView.OnSliderClickListener,
ViewPagerEx.OnPageChangeListener{

    private lateinit var mDemoSlider: SliderLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        l_menu_match.setOnClickListener(this)
        l_menu_club.setOnClickListener(this)
        l_menu_favorite.setOnClickListener(this)
        l_menu_about.setOnClickListener(this)

        mDemoSlider = slider

        val listUrl = ArrayList<String>()
        val listName = ArrayList<String>()

        listUrl.add("https://static.vecteezy.com/system/resources/previews/000/133/768/non_2x/beach-soccer-sport-vector.jpg")
        listName.add("Football Club - Bagus Pambudi")

        listUrl.add("https://static.vecteezy.com/system/resources/previews/000/140/574/non_2x/lionel-messi-vector-wpap-portrait.jpg")
        listName.add("Lionel Messi")

        listUrl.add("https://static.vecteezy.com/system/resources/previews/000/142/290/non_2x/cristiano-ronaldo-vector-wpap.jpg")
        listName.add("Cristian Ronaldo")

        val requestOptions = RequestOptions()
        requestOptions.centerCrop()

        for (i in 0 until listUrl.size) {
            val sliderView = TextSliderView(this)
            // if you want show image only / without description text use DefaultSliderView instead

            // initialize SliderLayout
            sliderView
                    .image(listUrl.get(index = i))
                    .description(listName.get(index = i))
                    .setRequestOption(requestOptions)
                    .setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(this)

            //add your extra information
            sliderView.bundle(Bundle())
            sliderView.bundle.putString("extra", listName.get(i))
            mDemoSlider.addSlider(sliderView)
        }

        // set Slider Transition Animation
        // mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion)

        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        mDemoSlider.setCustomAnimation(DescriptionAnimation())
        mDemoSlider.setDuration(4000)
        mDemoSlider.addOnPageChangeListener(this)


    }
    

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.l_menu_match ->{
                Log.d("click : ", "match")
                startActivity<MainMatch>()
            }
            R.id.l_menu_club ->{
                Log.d("click : ", "club")
                startActivity<MainClub>()
            }
            R.id.l_menu_favorite ->{

                Log.d("click : ", "favorite")
                startActivity<MainFavorite>()
            }
            R.id.l_menu_about ->{

                Log.d("click : ", "about")
                startActivity<MainMyAbout>()
            }
        }
    }

    override fun onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle()
        super.onStop()
    }

    override fun onSliderClick(slider: BaseSliderView) {
        Toast.makeText(this, slider.bundle.get("extra")!!.toString() + "", Toast.LENGTH_SHORT).show()
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //null
    }

    override fun onPageSelected(position: Int) {
        //null
    }

    override fun onPageScrollStateChanged(state: Int) {
        //null
    }


}
