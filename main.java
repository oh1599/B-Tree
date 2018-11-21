package btree;

public class main
{
	public static void main(String[] args)
	{
		int[] arr1 = {30,20,62,110,140,15,65,136,150,120};
		int[] arr2 = {40,132,19,128,138,100,16,145,70,42};
		int[] arr3 = {69,43,26,60,130,50,18,7,36,58};
		int[] arr4 = {22,41,59,57,54,33,75,124,122,123};
		
		BTree bt1 = new BTree(3);
		for(int i=0;i<10;i++)
		{
			bt1.insertBT(arr1[i]);
			bt1.printInorder();
		}
		
		for(int i=0;i<10;i++)
		{
			bt1.insertBT(arr2[i]);
			bt1.printInorder();
		}
		
		for(int i=0;i<10;i++)
		{
			bt1.insertBT(arr3[i]);
			bt1.printInorder();
		}
		
		for(int i=0;i<10;i++)
		{
			bt1.insertBT(arr4[i]);
			bt1.printInorder();
		}
		
		BTree bt2 = new BTree(4);
		for(int i=0;i<10;i++)
		{
			bt2.insertBT(arr1[i]);
			bt2.printInorder();
		}
		
		for(int i=0;i<10;i++)
		{
			bt2.insertBT(arr2[i]);
			bt2.printInorder();
		}
		
		for(int i=0;i<10;i++)
		{
			bt2.insertBT(arr3[i]);
			bt2.printInorder();
		}
		
		for(int i=0;i<10;i++)
		{
			bt2.insertBT(arr4[i]);
			bt2.printInorder();
		}
	}
}
