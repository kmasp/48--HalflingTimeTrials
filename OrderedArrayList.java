//Team Ghost Saturns - Kyle Moon, Lucy Tang, Jordan Louie
//APCS1 - pd5
//HW48--Halfling Time Trials
//2015-12-14

/*============================================
  class OrderedArrayList
  Wrapper class for ArrayList.
  Imposes the restriction that stored items 
  must remain sorted in ascending order
  ============================================*/

//ArrayList's implementation is in the java.util package
import java.util.ArrayList;


public class OrderedArrayList {

    // instance of class ArrayList, holding objects of type Comparable 
    // (ie, instances of a class that implements interface Comparable)
    private ArrayList<Comparable> _data;


    // default constructor initializes instance variable _data
    public OrderedArrayList() {
	_data = new ArrayList<Comparable>();
    }


    public String toString() { 
	return _data.toString(); 
    }


    public Comparable remove( int index ) { 
	return _data.remove(index); 
    }


    public int size() { 
	return _data.size();
    }

    
    public Comparable get( int index ) { 
	return _data.get(index); 
    }


    // addLinear takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a linear search to find appropriate index
    public void addLinear( Comparable newVal ) 
    { 
	for( int p = 0; p < _data.size(); p++ ) {
	    if ( newVal.compareTo( _data.get(p) ) < 0 ) { //newVal < oal[p]
		_data.add( p, newVal );
		return; //Q:why not break?
	    }
	}
	_data.add( newVal ); //newVal > every item in oal, so add to end
    }


    // addBinary takes as input any comparable object 
    // (i.e., any object of a class implementing interface Comparable)
    // inserts newVal at the appropriate index
    // maintains ascending order of elements
    // uses a binary search to find appropriate index
    public void addBinary( Comparable newVal ) { 
	//initialize upperbound, lowerbound and median
	int lo = 0;
	int med = 0;
	int hi = _data.size()-1;

	while ( lo <= hi ) { //running until target is found or bounds cross

	    med = (lo + hi) / 2;
	    int x = _data.get(med).compareTo( newVal );
	        
	    if ( x == 0 ) { //equal value found, insert here
		_data.add( med, newVal );
		return;
	    }
	    else if ( x > 0 ) //newVal < med, so look at lower half of data
		hi = med - 1;
	    else //newVal > med, so look at upper half of data
		lo = med + 1;
	}
	// If you make it this far, newVal was not in the ArrayList.
	// So insert at lo. Q: How do you know lo is correct insertion index?
	_data.add( lo, newVal );
    }	


    // determine whether element present in data structure using linear search
    // return index of occurrence or -1 if not found
    public int findLin( Comparable target )
    {
	int ret = -1;
	for (int i = 0; i< _data.size(); i++)
	{
	    if (_data.get(i).compareTo(target) == 0)
	    {
		return i;
	    }
	}
	return ret;	
    }


    // determine whether element present in data structure using binary search
    // return index of occurrence or -1 if not found
    public int findBin( Comparable target ) 
    { 
	int low = 0;
	int high = this.size() -1;
	int med = (high + low) / 2;

	while ( (this.get(med).compareTo(target) != 0) &&
		(low <= high) )
	{
	    if (this.get(med).compareTo(target) < 0)
	    {
		low = med + 1;
	    }
	    else if (this.get(med).compareTo(target) > 0)
	    {
		high = med - 1;
	    }
	    med = (high + low) / 2;
	}
	if (this.get(med).compareTo(target) == 0)
	    {
		return med;
	    }
	return -1; 
    }
    
    public static long testAddLinear()
    {
	OrderedArrayList Franz = new OrderedArrayList();
	long linearTimeBefore = System.currentTimeMillis();
	
	for( int i = 0; i < 10000; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    Franz.addLinear( valToAdd );
	}
	
	long linearTimeAfter = System.currentTimeMillis();
	long linearTimeDifference = linearTimeAfter - linearTimeBefore;

	return linearTimeDifference;
    }

    public static long testAddBinary()
    {

	OrderedArrayList Ghost = new OrderedArrayList();

	long binaryTimeBefore = System.currentTimeMillis();
	
	for( int i = 0; i < 10000; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    Ghost.addBinary( valToAdd );
	}
	
	long binaryTimeAfter = System.currentTimeMillis();
	long binaryTimeDifference = binaryTimeAfter - binaryTimeBefore;

	return binaryTimeDifference;
    }

    public static long testFindLinear()
    {
	OrderedArrayList Franz = new OrderedArrayList();
	
	for( int i = 0; i < 10000; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    Franz.addLinear( valToAdd );
	}

	int valToFind = (int)(50*Math.random());
	long linearTimeBefore = System.currentTimeMillis();
	Franz.findLin(valToFind);
	long linearTimeAfter = System.currentTimeMillis();
	long linearTimeDifference = linearTimeAfter - linearTimeBefore;

	return linearTimeDifference;
    }

    public static long testFindBinary()
    {

	OrderedArrayList Ghost = new OrderedArrayList();

	for( int i = 0; i < 10000; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    Ghost.addBinary( valToAdd );
	}

	int valToFind = (int)(50*Math.random());
	long binaryTimeBefore = System.currentTimeMillis();
	Ghost.findBin(valToFind);
	long binaryTimeAfter = System.currentTimeMillis();
	long binaryTimeDifference = binaryTimeAfter - binaryTimeBefore;

	return binaryTimeDifference;
    }
    

    // main method solely for testing purposes
    public static void main( String[] args ) 
    {
	/*
	OrderedArrayList Franz = new OrderedArrayList();

	System.out.println("\nPopulating Francz with 10,000 elements via addLinear...");

	// testing linear search
	long linearTimeBefore = System.currentTimeMillis();
	for( int i = 0; i < 10000; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    //System.out.println( valToAdd );
	    Franz.addLinear( valToAdd );
	}
	long linearTimeAfter = System.currentTimeMillis();

	long linearTimeDifference = linearTimeAfter - linearTimeBefore;

	System.out.println("after population via addLinear(), Franz.size() = " + Franz.size());
	//System.out.println( Franz );
	System.out.println("Time allotted: " + linearTimeDifference + "ms");
	System.out.println();

	Franz = new OrderedArrayList();

	System.out.println("Populating Franz with 10,000 elements via addBinary...");
				
	// testing binary search
	long binaryTimeBefore = System.currentTimeMillis();
	for( int i = 0; i < 10; i++ ) {
	    int valToAdd = (int)( 50 * Math.random() );
	    //System.out.println( valToAdd );
	    Franz.addBinary( valToAdd );
	}
	long binaryTimeAfter = System.currentTimeMillis();

	long binaryTimeDifference = binaryTimeAfter - binaryTimeBefore;

	System.out.println("after population via addBinary(), Francz.size() = " + Franz.size());
	//System.out.println( Franz );
	System.out.println("Time allotted: " + binaryTimeDifference + "ms");
	System.out.println();

	//TESTING FINDLIN AND FINDBIN
        Franz.addBinary(5);
	System.out.println(Franz);
	System.out.println("findLin 5 " + Franz.findLin(5));
	System.out.println("findBin 5 " + Franz.findBin(5));
	*/
	

	/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	   INSERT WELL-COMMENT TIMING APPARATUS HERE
	   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	//for testing ADD
	OrderedArrayList binArray = new OrderedArrayList();
        OrderedArrayList linArray = new OrderedArrayList();
	
	for (int i = 0; i < 30; i++)
	{
	    long binVal = testAddBinary();
	    long linVal = testAddLinear();
	    binArray.addBinary(binVal);
	    linArray.addBinary(linVal);
		;
	}
	System.out.println("\nTimings of 30 trials of addBinary:");
	System.out.println(binArray);
	System.out.println("\nTimings of 30 trials of addLinear:");
	System.out.println(linArray);

	//for testing SEARCH
	OrderedArrayList binArray2 = new OrderedArrayList();
        OrderedArrayList linArray2 = new OrderedArrayList();
	
	for (int i = 0; i < 30; i++)
	{
	    long binVal = testFindBinary();
	    long linVal = testFindLinear();
	    binArray2.addBinary(binVal);
	    linArray2.addBinary(linVal);
		;
	}
	System.out.println("\nTimings of 30 trials of findBinary:");
	System.out.println(binArray2);
	System.out.println("\nTimings of 30 trials of findLinear:");
	System.out.println(linArray2);
		
    }

}//end class OrderedArrayList
 
