/**
 * @author Harsh Patel
 * @version 1.0 
 */

package Product;

import sort.Insertion;
import sort.Merge;
import sort.Quick;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import sort.Heap;

//Product class 
public class Product implements Comparable<Product> {
	private String productId;
	private int salesAmount;

	// Product constructor with ID and sales amount
	/**
	 * creates a product
	 * 
	 * @param productId - Unique Id of the product
	 * @param salesAmount - Amount of the product sold in sales
	 */
	public Product(String productId, int salesAmount) {
		this.productId = productId;
		this.salesAmount = salesAmount;
	}

	// Get Id of the product
	/**
	 * gets Id of the product
	 * @return product ID
	 */
	public String getProductId() {
		return productId;
	}

	// Set or change the sales amount of a product
	/**
	 * set or change sales amount of a product
	 * 
	 * @param salesAmount - Amount of the product sold in sales
	 */
	public void setSalesAmount(int salesAmount) {
		this.salesAmount = salesAmount;
	}

	// Get the sales amount of the product
	/**
	 * gets sales amount of the product
	 * @return sales amount
	 */
	public int getSalesAmount() {
		return salesAmount;
	}
	
	/**
	 * presents the product Id and sales amount as string format
	 * @return string format of product
	 */
	public String toString() {
		return String.format("productId: %s, salesAmount: %d", productId, salesAmount);
	}

	// Compare two sales amount of different products
	/**
	 * compares two different products
	 * @param that product to be compared to
	 * @return an integer corresponding to comparisons made
	 */
	public int compareTo(Product that) {
		if (this.salesAmount > that.salesAmount) {
			return 1;
		} else if (that.salesAmount > this.salesAmount) {
			return -1;
		} else {
			return this.productId.compareTo(that.productId);
		}
	}
}
