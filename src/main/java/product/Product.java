package product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Product{
    private int quantity;
    private String description;
    /*private enum Category{
    	FOOD(1),
    	BOOK(2),
    	MEDICAL(3),
    	ALL(4);
    };*/
    private BigDecimal price;
    private BigDecimal finalPrice;
    private double inflaction;
    private boolean imported = false;
    
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat format = new DecimalFormat("0.00", symbols);
    
    public Product(int quantity, String description, BigDecimal price, boolean imported) {
    	this.quantity = quantity;
    	this.description = description;
    	this.price = price;
    	this.imported = imported;
    	this.inflaction = 0;
    	
    	this.finalPrice = price.add (price.multiply(new BigDecimal(this.inflaction)));
	}    

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getInflaction() {
        return inflaction;
    }

    public void setInflaction(double inflaction) {
        this.inflaction = this.getInflaction()+inflaction;
    }
    
    public String toStringInput() {  	
    	return this.quantity + " " + this.description + " at " + this.price;
    }
    
    public String toStringOutput() {  	       
        
        /*this.finalPrice = new BigDecimal(
                Math.ceil(this.finalPrice.doubleValue() * 100.0) / 100.0)
                .setScale(2, RoundingMode.HALF_UP);

        //Hard Coding rounding 
        double tmp=(this.finalPrice.doubleValue() - this.finalPrice.intValue())*10;
        tmp=tmp-(int)tmp;
        if(tmp>0.0 && tmp<0.5) {
        	tmp=(0.5-tmp);       
        	tmp= Math.round(100.00 * tmp) / 1000.00;	       
        	this.finalPrice = this.finalPrice.add(new BigDecimal(Double.toString(tmp)));
       }*/
       return this.quantity + " " + this.description + ": " + this.finalPrice.multiply(new BigDecimal(this.quantity));
    }

    public BigDecimal getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice() {
		//this.finalPrice = (double) Math.ceil((this.price +  this.price * this.inflaction)*20.00)/20.00;
		//Add tax
		this.finalPrice = this.price.add (price.multiply(new BigDecimal(Double.toString(this.inflaction*100/100.0))));
		
		//Hard rounding 
		this.finalPrice = new BigDecimal(
                Math.ceil(this.finalPrice.doubleValue() * 100.0) / 100.0)
                .setScale(2, RoundingMode.HALF_UP);

		//System.out.println(finalPrice);
        double tmp=(this.finalPrice.doubleValue() - this.finalPrice.intValue())*10;
        tmp=tmp-(int)tmp;
        if(tmp>0.0 && tmp<0.5) {
        	tmp=(0.5-tmp);    
        	tmp= Math.round(100.00 * tmp) / 1000.00;
        	this.finalPrice = this.finalPrice.add(new BigDecimal(Double.toString(tmp)));
       }
       //return this.quantity + " " + this.description + ": " + this.finalPrice.multiply(new BigDecimal(this.quantity));
	}

	public boolean isImported() {
		return imported;
	}
    
}
