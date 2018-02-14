package com.yichiuan.moviedb

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.yichiuan.moviedb.data.tmdb.TMDbRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    val tmDbRepository = TMDbRepository()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_main_movies -> {
                message.setText(R.string.navigation_title_movies)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_main_tv_shows -> {
                message.setText(R.string.navigation_title_tv_shows)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_main_people -> {
                message.setText(R.string.navigation_title_people)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        message.setOnClickListener {
            tmDbRepository.getPopularMovies(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { movies ->
                        Timber.d(movies.toString())
                    }
        }
    }
}
