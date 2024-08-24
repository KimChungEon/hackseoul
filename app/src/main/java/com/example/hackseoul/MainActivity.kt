package com.example.hackseoul

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hackseoul.databinding.ActivityMainBinding
import com.example.hackseoul.ui.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val viewPager: ViewPager2 = binding.viewPager

        // ViewPager와 어댑터 설정
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        // 네비게이션 메뉴 아이템 클릭 시 뷰페이저 페이지 전환
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewPager.setCurrentItem(0, false)
                R.id.navigation_dashboard -> viewPager.setCurrentItem(1, false)
                R.id.navigation_notifications -> viewPager.setCurrentItem(2, false)
            }
            true
        }

        // 뷰페이저 페이지 변경 시 네비게이션 메뉴도 변경
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navView.selectedItemId = R.id.navigation_home
                    1 -> navView.selectedItemId = R.id.navigation_dashboard
                    2 -> navView.selectedItemId = R.id.navigation_notifications
                }
            }
        })
    }
}
