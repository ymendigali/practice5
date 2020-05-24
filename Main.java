package kz.aitu.oop.practice.practice5;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main{
	//variables
	public static ArrayList<Stones> necklace;
	public static double totalWeight = 0;
	public static int totalCost = 0;
	
	//main function
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		//connection to database jdbc:mysql://localhost:3306/stones where username is root and no password
		Connect link = new Connect("localhost", "3306", "root", "", "stones");
		necklace = new ArrayList<>();
		//query to get all stones from table stones
		ResultSet query = link.getStmt().executeQuery("SELECT * FROM stones");
		
		while(query.next())  
		{
			//presenting all stones 
			String type = (query.getInt(2)==1) ? "precious" : "semi-precious";
			System.out.println(query.getInt(1)+"  "+type+"  "+query.getString(3)+"  "+query.getDouble(4)+"  "+query.getInt(5)); 
			
			//sorting stones to precious and semi-precious
			if(type.equals("precious")) 
			{
				PreciousStones p = new PreciousStones(query.getString(3), query.getDouble(4), query.getInt(5));
				necklace.add(p);
			}
				
			if(type.equals("semi-precious"))
			{
				SemiPreciousStones sp = new SemiPreciousStones(query.getString(3), query.getDouble(4), query.getInt(5));
				necklace.add(sp);
			}
		}
		//giving to variables its values
		totalWeight = calcTotalW(necklace);
		totalCost = calcTotalC(necklace);
		//presenting totals
		System.out.println(toString(query));
		//closing the connection
		link.closeCon();
	}
	
	//method for calculation of total weight
	public static double calcTotalW (ArrayList<Stones> al){
		double total = 0;
		for(int i = 0; i < al.size(); i++) {
			total += al.get(i).getWeight();
		}
		return total;
	}
	
	//method for calculation of total cost
	public static int calcTotalC (ArrayList<Stones> al){
		int total = 0;
		for(int i = 0; i < al.size(); i++) {
			total += al.get(i).getCost();
		}
		return total;
	}
	
	//method toString
	public static String toString(ResultSet rs) {	
		return "Total weight is: " + totalWeight + " carats and total cost is: " + totalCost + " KZT. " + necklace.size() + " stones.";
	}
}
