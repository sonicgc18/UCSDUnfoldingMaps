package GUImodule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.*;


public class LifeExpectancy extends PApplet{

	public LifeExpectancy() {
		// TODO Auto-generated constructor stub
	}

	UnfoldingMap map;
	
	Map<String, Float> lifeExpectancy;
	
	List<Feature> countries;
	List<Marker> markers;
	
	public void setup(){
		size(800,600,OPENGL);

		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		// Load lifeExpectancy data
		lifeExpectancy = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
		
		// Load country polygons and adds them as markers
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		markers = MapUtils.createSimpleMarkers(countries);
		
		map.addMarkers(markers);
		
		//helper method:Country markers are shaded according to life expectancy (only once)
		shadeCountries();
	}
	
	public void draw(){
		// Draw map tiles and country markers
		map.draw();
	}
	//Helper method to color each country based on life expectancy
	//Red-orange indicates low (near 40)
	//Blue indicates high (near 100)
	private void shadeCountries(){
		// Find data for country of the current marker
		for(Marker marker: markers){
			
			if(lifeExpectancy.containsKey(marker.getId())){
				float lifexp = lifeExpectancy.get(marker.getId());
				// Encode value as brightness (values range: 40-90)
				int colorlevel = (int) map(lifexp, 40,90,10,255);
				
				marker.setColor(color(255-colorlevel, 100, colorlevel));
				
			}else{
				marker.setColor(color(150, 150, 150));

			}
		}
		
	}
	//Helper method to load life expectancy data from file
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName){
		
		Map<String, Float> lifeExpMap = new HashMap<String, Float>(); 
		
		String[] rows = loadStrings(fileName);
				
		for (String row : rows){
			String[] columns = row.split(",");
			//System.out.println(columns[0] +" " + columns[4]+ " " +columns[5]);
			if(columns.length==6 && !columns[5].equals("..")){
				float value = Float.parseFloat(columns[5]);
				lifeExpMap.put(columns[4], value);
			}
		}
		return lifeExpMap;
	}
}
