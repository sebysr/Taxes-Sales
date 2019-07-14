package test;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import product.Product;

import java.math.BigDecimal;


public class UnitTests extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UnitTests(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite() {
        return new TestSuite(UnitTests.class);
    }

    /**
     * Tests FOOD product Not Imported
     */
    public void testFoodNotImported() {
    	Product product1 = new Product( 1, "chocolate", new BigDecimal(Double.toString(0.85)), false);
        assertTrue("chocolate pretax", product1.getPrice().equals(new BigDecimal(Double.toString(0.85))));
        assertTrue("chocolate postax", product1.getFinalPrice().equals(new BigDecimal(Double.toString(0.85))));
    }

    /**
     * Tests FOOD product Imported
     */
    public void testFoodImported() {
    	Product product1 = new Product( 1, "chocolate", new BigDecimal(Double.toString(11.25)), true);
    	if(product1.isImported()) {
    		product1.setInflaction(0.05); //Apply 5%
    		product1.setFinalPrice();
    	}
        assertTrue("chocolate pretax", product1.getPrice().equals(new BigDecimal(Double.toString(11.25))));
        assertTrue("chocolate postax", product1.getFinalPrice().equals(new BigDecimal(Double.toString(11.85))));
    }
    
    /**
     * Tests Product NO Tax
     *
     * @throws Exception
     */
    public void testAnotherProductNoTax() throws Exception {
    	Product product1 = new Product( 1, "book", new BigDecimal(Double.toString(12.49)), false);

        assertTrue("book pretax", product1.getPrice().equals(new BigDecimal("12.49")));
        assertTrue("book postax", product1.getFinalPrice().equals(new BigDecimal("12.49")));
    }

    /**
     * Tests Product WITH Tax
     */
    public void testProducts() {
    	Product product1 = new Product( 1, "music CD", new BigDecimal(Double.toString(14.99)), false);
    	Product product2 = new Product( 1, "Abox", new BigDecimal(Double.toString(0.03)), false);
    	Product product3 = new Product( 1, "Bbox", new BigDecimal(Double.toString(0.033232)), false);
    	Product product4 = new Product( 1, "Cbox", new BigDecimal(Double.toString(1.249)), false);
    	Product product5 = new Product( 1, "Dbox", new BigDecimal(Double.toString(1.499)), false);
    	Product product6 = new Product( 1, "Ebox", new BigDecimal(Double.toString(1.8999)), false);

    	product1.setInflaction(0.10);product1.setFinalPrice();
        assertTrue("cd pretax", product1.getPrice().equals(new BigDecimal("14.99")));
        assertTrue("cd postax", product1.getFinalPrice().equals(new BigDecimal("16.49")));
        
        product2.setInflaction(0.10);product2.setFinalPrice();
        assertTrue("Abox pretax", product2.getPrice().equals(new BigDecimal("0.03")));
        assertTrue("Abox postax", product2.getFinalPrice().equals(new BigDecimal("0.05")));
        
        product3.setInflaction(0.10);product3.setFinalPrice();
        assertTrue("Bbox pretax", product3.getPrice().equals(new BigDecimal("0.033232")));
        assertTrue("Bbox postax", product3.getFinalPrice().equals(new BigDecimal("0.05")));
        
        product4.setInflaction(0.10);product4.setFinalPrice();
        assertTrue("Cbox pretax", product4.getPrice().equals(new BigDecimal("1.249")));
        assertTrue("Cbox postax", product4.getFinalPrice().equals(new BigDecimal("1.38")));
        
        product5.setInflaction(0.10);product5.setFinalPrice();
        assertTrue("Dbox pretax", product5.getPrice().equals(new BigDecimal("1.499")));
        assertTrue("Dbox postax", product5.getFinalPrice().equals(new BigDecimal("1.65")));
        
        product6.setInflaction(0.10);product6.setFinalPrice();
        assertTrue("Ebox pretax", product6.getPrice().equals(new BigDecimal("1.8999")));
        assertTrue("Ebox postax", product6.getFinalPrice().equals(new BigDecimal("2.09")));

    }   
    
}