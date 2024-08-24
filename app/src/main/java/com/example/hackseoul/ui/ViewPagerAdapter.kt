package com.example.hackseoul.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hackseoul.ui.dashboard.DashboardFragment
import com.example.hackseoul.ui.home.HomeFragment
import com.example.hackseoul.ui.notifications.NotificationsFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3 // 홈, 대시보드, 알림에 해당하는 탭 수
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> DashboardFragment()
            2 -> NotificationsFragment()
            else -> HomeFragment()
        }
    }
}
