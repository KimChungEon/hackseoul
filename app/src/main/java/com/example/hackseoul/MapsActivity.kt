package com.example.hackseoul

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.hackseoul.databinding.ActivityMapsBinding
import com.example.hackseoul.databinding.FragmentDashboardBinding

import java.io.IOException
import java.util.Locale

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: FragmentDashboardBinding

    private lateinit var mMap: GoogleMap
    private lateinit var actionButton: Button
    private var selectedLatLng: LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionButton = binding.buttonAction


        // SupportMapFragment에서 지도를 비동기적으로 로드합니다.
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)

        // 버튼 클릭 이벤트 처리
        actionButton.setOnClickListener {
            selectedLatLng?.let { latLng ->
                Toast.makeText(this, "버튼이 클릭되었습니다!", Toast.LENGTH_SHORT).show()

                // Geocoder를 사용하여 주소를 가져옴
                val geocoder = Geocoder(this, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                    if (addresses != null) {
                        if (addresses.isNotEmpty()) {
                            val address = addresses[0]
                            val addressText = address.getAddressLine(0)

                            // 다른 화면으로 넘어가며 주소 전달
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("ADDRESS", addressText)
                            startActivity(intent)

                            Toast.makeText(this, addressText, Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "주소를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "주소를 가져오는 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // 기본 위치로 이동
        val seoul = LatLng(37.56, 126.97)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15f))

        // 지도 클릭 이벤트 리스너 설정
        mMap.setOnMapClickListener { latLng ->
            // 마커 추가
            mMap.clear()  // 이전 마커 제거
            mMap.addMarker(MarkerOptions().position(latLng).title("클릭한 위치"))

            // 클릭한 위치 저장
            selectedLatLng = latLng

        }


    }
}