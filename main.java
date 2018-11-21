package btree;

public class main
{
	public static void main(String[] args)
	{
		int[][] arr = {{30,20,62,110,140,15,65,136,150,120},
				{40,132,19,128,138,100,16,145,70,42},
				{69,43,26,60,130,50,18,7,36,58},
				{22,41,59,57,54,33,75,124,122,123}};
		BTree bt1 = new BTree(3);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<10;j++)
			{	
				bt1.insertBT(arr[i][j]);
				bt1.printInorder();
			}
		}
	
		BTree bt2 = new BTree(4);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<10;j++)
			{
				bt2.insertBT(arr[i][j]);
				bt2.printInorder();
			}
		}
	}
}
