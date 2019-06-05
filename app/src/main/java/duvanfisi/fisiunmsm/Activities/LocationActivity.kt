package duvanfisi.fisiunmsm.Activities

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions

import com.google.android.gms.maps.model.Marker
import duvanfisi.fisiunmsm.R


class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val PERTH = LatLng(-31.952854, 115.857342)
    private val SYDNEY = LatLng(-33.87365, 151.20689)
    private val BRISBANE = LatLng(-27.47093, 153.0235)


    private var mPerth: Marker? = null
    private val mSydney: Marker? = null
    private val mBrisbane: Marker? = null


    private val MELBOURNE = LatLng(-37.813, 144.962)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        styleMap()

        // Add a marker in Sydney and move the camera
        val unmsmmap = LatLng(-12.057184, -77.083344)
       // mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unmsmmap, 16f));
        /*mMap.addMarker(MarkerOptions()
                .position(PERTH)
                .title("Perth"))


        mMap.addMarker(MarkerOptions()
                .position(SYDNEY)
                .title("Sydney"))


        mMap.addMarker(MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane"))*/

        // Set a listener for marker click.
        //mMap.setOnMarkerClickListener();
    }


    fun styleMap(){
        try {
            val success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyle))

            if(!success) {
                Toast.makeText(this, "Style parsing failed.", Toast.LENGTH_SHORT).show();
            }
        } catch (e: Resources.NotFoundException) {
            Toast.makeText(this,  "Can't find style. Error: ", Toast.LENGTH_SHORT).show();
        }
    }
}
