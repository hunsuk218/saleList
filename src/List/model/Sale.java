package List.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sale {

	private final StringProperty dong;
	private final StringProperty ho;
	private final StringProperty phone;
	private final StringProperty phone2;
	private final StringProperty complex;
	private final StringProperty price;
	private final StringProperty rmks;
	
	public Sale(){
		this(null,null,null);
	}
	
	public Sale(String complex,String dong,String ho){
		this.dong = new SimpleStringProperty(dong);
		this.ho = new SimpleStringProperty(ho);
		this.complex = new SimpleStringProperty(complex);
		
		//더미데이터
		this.phone = new SimpleStringProperty("phone");
		this.phone2 = new SimpleStringProperty("phone2");
		this.rmks = new SimpleStringProperty("rmks");
		this.price = new SimpleStringProperty("price");
		
	}
	
	public String getcomplex(){
		return complex.get();
	}
	
	public StringProperty complexProperty(){
		return complex;
	}
	public void setComplex(String complex){
		this.complex.set(complex);
	}
	public String getDong(){
		return dong.get();
	}
	
	public StringProperty dongProperty(){
		return dong;
	}
	public void setDong(String dong){
		this.complex.set(dong);
	}
	
	public String getHo(){
		return ho.get();
	}
	
	public StringProperty hoProperty(){
		return ho;
	}
	public void setHo(String ho){
		this.ho.set(ho);
	}
	public String getPhone(){
		return phone.get();
	}
	
	public StringProperty phoneProperty(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone.set(phone);
	}
	public String getPhone2(){
		return phone2.get();
	}
	
	public StringProperty phone2Property(){
		return phone2;
	}
	public void setPhone2(String phone2){
		this.phone2.set(phone2);
	}
	
	
	
	
	public String getPrice(){
		return price.get();
	}
	
	public StringProperty priceProperty(){
		return price;
	}
	public void setPrice(String price){
		this.price.set(price);
	}
	
	
	
	public String getRmks(){
		return rmks.get();
	}
	
	public StringProperty rmksProperty(){
		return rmks;
	}
	public void setRmks(String rmks){
		this.rmks.set(rmks);
	}
	
}
