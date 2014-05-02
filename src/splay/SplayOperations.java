/**
 * 
 */
package splay;

import java.io.File;
import java.util.Scanner;

/**
 * @author Dany
 *
 */
public class SplayOperations {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long inTime=System.currentTimeMillis();
		Long result=new SplayOperations().operateOnBalancedTrees("/users/dany/downloads/sskew.txt");
		System.out.println("Total Result : "+result);
		long pTime=System.currentTimeMillis();
		System.out.println("Time in Milli Secs "+(pTime-inTime));
		
	}
	
	public Long operateOnBalancedTrees(String inFile)
	{
		File infile=new File(inFile);
		Long nTotalOperationResult= 0L;
		String sOperation="";
        SplayTree<Long, Long> sTree = new SplayTree<Long, Long>();

		try{
			Scanner fileScanner=new Scanner(infile);
			while(fileScanner.hasNext())
			{
				if(!(Character.isDigit((sOperation=fileScanner.next()).charAt(0))))
				{
				//System.out.println(sOperation);
				if(sOperation.compareTo("Insert")==0)
				sTree.insert(fileScanner.nextLong(), fileScanner.nextLong());
				else if(sOperation.compareTo("Find")==0)
				sTree.find(fileScanner.nextLong());
				else if(sOperation.compareTo("FindMin")==0)
				nTotalOperationResult+=sTree.findMin();
				else if(sOperation.compareTo("FindMax")==0)
				nTotalOperationResult+=sTree.findMax();
				else if(sOperation.compareTo("Remove")==0)
				sTree.remove(fileScanner.nextLong());
				else if(sOperation.compareTo("RemoveValue")==0)
				nTotalOperationResult+=sTree.removeValue(fileScanner.nextLong());
				else if(sOperation.compareTo("Size")==0)
				nTotalOperationResult+=sTree.size();
				else if(sOperation.compareTo("IsEmpty")==0)
				sTree.isEmpty();
				}
			}
			
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
		
		

		return nTotalOperationResult;
	}
	
	public void testSplayTree()
	{
		SplayTree<Long, Long> splayTree=new SplayTree<Long, Long>();
		
		splayTree.insert(10L, 25L);
		splayTree.insert(12L, 35L);
		splayTree.insert(13L, 45L);
		splayTree.insert(14L, 55L);
		System.out.println(splayTree.find(10L));
		System.out.println(splayTree.findMin());
		System.out.println(splayTree.findMax());
		splayTree.remove(13L);
		System.out.println(splayTree.size());


		
	}

}
