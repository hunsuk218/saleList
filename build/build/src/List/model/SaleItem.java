package List.model;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SaleItem {

	private StringProperty complex;
	private StringProperty dong;
	private StringProperty ho;
	private StringProperty phoneMale;
	private StringProperty phoneFemale;
	private StringProperty phone2Male;
	private StringProperty phone2Female;
	private StringProperty price;
	private StringProperty rmks;
	
	private String key;


	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
		this.dong.set(dong);
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
	public String getPhoneMale(){
		return phoneMale.get();
	}	
	public StringProperty phoneMaleProperty(){
		return phoneMale;
	}
	public void setPhoneMale(String phoneMale){
		this.phoneMale.set(phoneMale);
	}
	public String getPhoneFemale(){
		return phoneFemale.get();
	}	
	public StringProperty phoneFemaleProperty(){
		return phoneFemale;
	}
	public void setPhoneFemale(String phoneFemale){
		this.phoneFemale.set(phoneFemale);
	}	
	public String getPhone2Male(){
		return phone2Male.get();
	}	
	public StringProperty phone2MaleProperty(){
		return phone2Male;
	}
	public void setPhone2Male(String phone2Male){
		this.phone2Male.set(phone2Male);
	}
	public String getPhone2Female(){
		return phone2Female.get();
	}	
	public StringProperty phone2FemaleProperty(){
		return phone2Female;
	}
	public void setPhone2Female(String phone2Female){
		this.phone2Female.set(phone2Female);
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


	
	public SaleItem() {
		this(null,null,null,null,null,null,null,null,null);
	}
	public SaleItem(String complex,String dong,String ho,String phoneMale,String phoneFemale, String phone2Male,String phone2Female,String price,String rmks){
		this.dong = new SimpleStringProperty(dong);
		this.ho = new SimpleStringProperty(ho);
		this.complex = new SimpleStringProperty(complex);
		this.phoneMale = new SimpleStringProperty(phoneMale);
		this.phoneFemale = new SimpleStringProperty(phoneFemale);
		this.phone2Male = new SimpleStringProperty(phone2Male);
		this.phone2Female = new SimpleStringProperty(phone2Female);
		this.rmks = new SimpleStringProperty(rmks);
		this.price = new SimpleStringProperty(price);
		this.key = null;
	}

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("complex",complex.get());
        result.put("dong",dong.get());
        result.put("ho",ho.get());
        result.put("phoneMale",phoneMale.get());
        result.put("phoneFemale",phoneFemale.get());
        result.put("phone2Male",phone2Male.get());
        result.put("phone2Female",phone2Female.get());
        result.put("price",price.get());
        result.put("rmks",rmks.get());
        return result;
    }
    

    public boolean equals(Object object) {
    	
    	boolean check = false;
    	
    	if(object != null && object instanceof SaleItem) {
    		check = this.key == ((SaleItem)object).getKey();
    	}
		return check;
    }

}
