package udea.edu.co.gasolfind.Views;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import org.w3c.dom.Text;

import udea.edu.co.gasolfind.BDClass.Gas_Station;
import udea.edu.co.gasolfind.Firebasegasolfind.FirebaseGasolfind;
import udea.edu.co.gasolfind.Interfaces.DBListener;
import udea.edu.co.gasolfind.R;

/**
 * Created by Juan Felipe Zuluaga on 22/04/2016.
 */
public class Content_View  implements GoogleMap.InfoWindowAdapter{
    private final Content_View self;
    private Marker marker;
	private View myContentsView = null;
    private Gas_Station gas_station;
    private TextView tittleTextView, infoTextView, priceGasR, priceGas, priceAcpm, priceGasP;
    private RatingBar ratingBar;
    private FirebaseGasolfind firebaseGasolfind;
    private int action = 0;

	public Content_View(Context contex){
        self = this;
        myContentsView = View.inflate(contex, R.layout.view_content, null);
        gas_station = new Gas_Station(contex);


	}

    @Override
    public View getInfoWindow(Marker marker) {

        Cursor cursor;
        LatLng location = marker.getPosition();
        cursor = gas_station.load_For_Location(location.latitude,location.longitude);
        System.out.println();

        ImageView image = (ImageView)myContentsView.findViewById(R.id.imageContent);
        image.setImageResource(R.drawable.gas_station_black);

        tittleTextView = ((TextView)myContentsView.findViewById(R.id.tittle));
        infoTextView = ((TextView)myContentsView.findViewById(R.id.info));
        priceGasR = (TextView)myContentsView.findViewById(R.id.priceRG);
        priceGas = (TextView)myContentsView.findViewById(R.id.priceGas);
        priceAcpm = (TextView)myContentsView.findViewById(R.id.priceAcpm);
        priceGasP = (TextView)myContentsView.findViewById(R.id.priceP);
        ratingBar = (RatingBar)myContentsView.findViewById(R.id.rating);

        if(cursor.getCount() != 0){
            System.out.println("-----Vística");
            System.out.println("------- cantidad: " + cursor.getCount());
            cursor.moveToFirst();
            System.out.println("Que es esto" + cursor.getString(1));
            tittleTextView.setText(cursor.getString(1));
            infoTextView.setText(cursor.getString(4));
            ratingBar.setNumStars(3);
            priceGas.setText(cursor.getString(7));
            priceAcpm.setText(cursor.getString(8));
            priceGasP.setText(cursor.getString(5));
            priceGasR.setText(cursor.getString(6));
        }else{
            System.out.println("++++++++Vistica else");
            priceGas.setText("precio del gas");
            priceAcpm.setText("Precio del ACPM");
            priceGasP.setText("Precio de Gasolina Premium");
            priceGasR.setText("Precio de Gasolina Regular");

            tittleTextView.setText(marker.getTitle());
            infoTextView.setText(marker.getSnippet());
        }




        return myContentsView;
    }


    @Override
    public View getInfoContents(Marker marker) {
        if (this.marker != null&& this.marker.isInfoWindowShown()) {
            this.marker.hideInfoWindow();
            this.marker.showInfoWindow();
        }
        return null;
    }

    public void actualizarBD(){

        //Buscar conexión con el API

    }

}
